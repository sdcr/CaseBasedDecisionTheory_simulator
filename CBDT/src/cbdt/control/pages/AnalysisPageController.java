package cbdt.control.pages;

import java.io.IOException;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.persistence.results.IResultsPersistenceManager;
import cbdt.control.persistence.results.ResultsPersistenceManager;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;
import cbdt.view.analysis.AnalysisPageReference;

public class AnalysisPageController extends AbstractPageController {

	private Result simulationResult;
	private AbstractEngineConfiguration config;
	private AnalysisPageReference analysisPageReference;
	private IResultsPersistenceManager resultPersistenceManager;
	
	public AnalysisPageController() {
		analysisPageReference = new AnalysisPageReference(this);
		resultPersistenceManager = new ResultsPersistenceManager();
	}
	
	@Override
	public AbstractPluginPageWrapper getPageWrapper() {
		return analysisPageReference;
	}

	public void setConfig(AbstractEngineConfiguration config) {
		this.config = config;
	}

	public void setSimulationResult(Result simulationResult) {
		this.simulationResult = simulationResult;
		analysisPageReference.getAnalysisPage().setResultModel(config, simulationResult);
	}

	public void exportResults(String filepathFromDialog) {
		try {
			resultPersistenceManager.saveResultToFile(filepathFromDialog, simulationResult);
		} catch (IOException e) {
			getMessageBoxManager().showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

}
