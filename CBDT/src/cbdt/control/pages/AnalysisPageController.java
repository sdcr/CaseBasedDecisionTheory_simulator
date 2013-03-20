package cbdt.control.pages;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;
import cbdt.view.analysis.AnalysisPageReference;

public class AnalysisPageController extends AbstractPageController {

	private Result simulationResult;
	private AbstractEngineConfiguration config;
	private AnalysisPageReference analysisPageReference;
	
	public AnalysisPageController() {
		analysisPageReference = new AnalysisPageReference();
	}
	
	@Override
	public AbstractPluginPageWrapper getPageWrapper() {
		return analysisPageReference;
	}

	public void setConfig(AbstractEngineConfiguration config) {
		this.config = config;
		analysisPageReference.getAnalysisPage().setEngineConfigModel(config);
	}

	public void setSimulationResult(Result simulationResult) {
		this.simulationResult = simulationResult;
		analysisPageReference.getAnalysisPage().setResultModel(simulationResult);
	}

}
