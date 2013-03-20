package cbdt.view.analysis;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;
import cbdt.view.LabelFactory;

public class AnalysisPage extends Composite {

	private AbstractEngineConfiguration config;
	private Result simulationResult;
	private AnalysisTableViewer tableViewer;

	public AnalysisPage(Composite parent, int style) {
		super(parent, style);

		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.spacing = 20;
		this.setLayout(rowLayout);
		
		LabelFactory labelfactory = new LabelFactory();
		labelfactory.createTitleLabel(this, "Result analysis:");
		
		tableViewer = new AnalysisTableViewer(this);
		
		StageResult[] sr = new StageResult[3];
		
//		sr[0] = new StageResult();
//		sr[0].setExpectedUtility(1);
//		
//		sr[1] = new StageResult();
//		sr[1].setExpectedUtility(2);
//		
//		sr[2] = new StageResult();
//		sr[2].setExpectedUtility(2);
//		tableViewer.setInput(sr);
	}

	public void setEngineConfigModel(AbstractEngineConfiguration config){
		this.config = config;
	}

	public void setResultModel(Result simulationResult){
		this.simulationResult = simulationResult;
		tableViewer.createOccuranceColumns(config, simulationResult);
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		simulationResult.getStageResults().add(new StageResult());
		tableViewer.setInput(simulationResult.getStageResults());
		tableViewer.resizeTable();
		this.pack();
	}

}
