package cbdt.view.analysispage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import cbdt.control.analysispage.AnalysisPageController;
import cbdt.model.config.SimulationConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.view.LabelFactory;

public class AnalysisPage extends Composite {

	private AbstractEngineConfig config;
	private Result simulationResult;
	private AnalysisTableViewer tableViewer;
	private Button showTreeButton;
	private Button exportResultButton;
	private ShowTreeSelectionListener showTreeSelectionListener;

	public AnalysisPage(Composite parent, int style, AnalysisPageController controller) {
		super(parent, style);

		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.spacing = 20;
		this.setLayout(rowLayout);
		
		LabelFactory labelfactory = new LabelFactory();
		labelfactory.createTitleLabel(this, "Result analysis:");
		
		tableViewer = new AnalysisTableViewer(this);
		
		exportResultButton = new Button(this, SWT.PUSH);
		exportResultButton.setText("Export as CSV");
		exportResultButton.setEnabled(false);
		exportResultButton.addSelectionListener(new ExportResultSelectionListener(this.getShell(), controller));
		showTreeButton = new Button(this, SWT.PUSH);
		showTreeButton.setText("Show tree structure");
		showTreeButton.setEnabled(false);
	}

	private void updateButtons() {
		if(config!=null && simulationResult!=null){
			if(config instanceof DFSkeepTreeEngineConfig && ((DFSkeepTreeEngineConfig) config).isSaveTreeStructure()){
				showTreeButton.setEnabled(true);
				if(showTreeSelectionListener!=null)
					showTreeButton.removeSelectionListener(showTreeSelectionListener);
				showTreeSelectionListener = new ShowTreeSelectionListener(simulationResult, (DFSkeepTreeEngineConfig) config, this);
				showTreeButton.addSelectionListener(showTreeSelectionListener);
			} else
				showTreeButton.setEnabled(false);
			exportResultButton.setEnabled(true);
		}
	}

	public void setResultModel(SimulationConfig configChoice, Result simulationResult){
		this.config = configChoice.getCurrentlyChosenEngineConfig();
		this.simulationResult = simulationResult;
		tableViewer.createOccuranceColumns(configChoice.getCommonConfig(), simulationResult);
		tableViewer.createLowestAspirationLevelColumn(configChoice.getCommonConfig(), simulationResult);
		tableViewer.setInput(simulationResult.getStageResults());
		tableViewer.resizeTable();
		updateButtons();
		this.getParent().pack();
	}

}
