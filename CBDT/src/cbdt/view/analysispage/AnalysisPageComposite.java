package cbdt.view.analysispage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.analysispage.AnalysisPageController;
import cbdt.model.config.SimulationConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.view.LabelFactory;
import cbdt.view.analysispage.listeners.ExportResultSelectionListener;
import cbdt.view.analysispage.listeners.ShowTreeSelectionListener;

public class AnalysisPageComposite extends Composite {

	/**
	 * The {@link AbstractEngineConfig} used for the computation.
	 */
	private AbstractEngineConfig engineConfig;

	/**
	 * The simulation {@link Result} which is to be displayed by this
	 * {@link AnalysisPageComposite}.
	 */
	private Result simulationResult;

	/**
	 * An {@link AnalysisTableViewer} to show the results in a table.
	 */
	private AnalysisTableViewer tableViewer;

	/**
	 * A {@link Button} with which to display the tree of the {@link Result}s.
	 */
	private Button showTreeButton;

	/**
	 * A {@link Button} with which to start exporting the {@link Result}s.
	 */
	private Button exportResultButton;

	/**
	 * A {@link ShowTreeSelectionListener}, which is used to act upon the user's
	 * click on the button to show the tree.
	 */
	private ShowTreeSelectionListener showTreeSelectionListener;

	/**
	 * The constructor. It creates a {@link RowLayout}, a title {@link Label}
	 * and initializes the {@link AnalysisTableViewer}, the {@link Button}s.
	 * Only the export button is initialized with a {@link SelectionListener}.
	 * 
	 * @param parent
	 * @param style
	 * @param controller
	 */
	public AnalysisPageComposite(Composite parent, int style,
			AnalysisPageController controller) {
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
		exportResultButton
				.addSelectionListener(new ExportResultSelectionListener(this
						.getShell(), controller));
		showTreeButton = new Button(this, SWT.PUSH);
		showTreeButton.setText("Show tree structure");
		showTreeButton.setEnabled(false);
	}

	/**
	 * Sets the {@link Result} to be shown in this page. It updates the
	 * {@link AnalysisTableViewer} and the show tree {@link Button}.
	 * 
	 * @param configChoice
	 * @param simulationResult
	 */
	public void setResult(SimulationConfig configChoice, Result simulationResult) {
		this.engineConfig = configChoice.getCurrentlyChosenEngineConfig();
		this.simulationResult = simulationResult;
		tableViewer.createOccuranceColumns(configChoice.getCommonConfig(),
				simulationResult);
		tableViewer.createLowestAspirationLevelColumn(
				configChoice.getCommonConfig(), simulationResult);
		tableViewer.setInput(simulationResult.getStageResults());
		tableViewer.resizeTable();
		updateShowTreeButton();
		this.getParent().pack();
	}

	/**
	 * Updates the show tree {@link Button}, according to the currently set
	 * {@link Result} and {@link AbstractEngineConfig}. It enables the
	 * {@link Button} if a tree can be displayed, and sets a
	 * {@link ShowTreeSelectionListener}.
	 */
	private void updateShowTreeButton() {
		if (engineConfig != null && simulationResult != null) {
			if (engineConfig instanceof DFSkeepTreeEngineConfig
					&& ((DFSkeepTreeEngineConfig) engineConfig)
							.isSaveTreeStructure()) {
				showTreeButton.setEnabled(true);

				if (showTreeSelectionListener != null)
					showTreeButton
							.removeSelectionListener(showTreeSelectionListener);
				showTreeSelectionListener = new ShowTreeSelectionListener(
						simulationResult,
						(DFSkeepTreeEngineConfig) engineConfig, this);
				showTreeButton.addSelectionListener(showTreeSelectionListener);
			} else
				showTreeButton.setEnabled(false);
			exportResultButton.setEnabled(true);
		}
	}
}
