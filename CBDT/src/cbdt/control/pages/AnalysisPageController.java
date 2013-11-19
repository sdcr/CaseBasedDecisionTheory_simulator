package cbdt.control.pages;

import java.io.IOException;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.persistence.results.IResultsPersistenceManager;
import cbdt.control.persistence.results.ResultsPersistenceManager;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
import cbdt.model.result.Result;
import cbdt.view.analysis.AnalysisPageReference;

public class AnalysisPageController extends AbstractPageController {

	private Result simulationResult;
	private AnalysisPageReference analysisPageReference;
	private IResultsPersistenceManager resultPersistenceManager;
	private AbstractEngineConfiguration usedEngineConfig;
	private CommonEngineConfiguration commonConfig;
	
	public AnalysisPageController() {
		analysisPageReference = new AnalysisPageReference(this);
		resultPersistenceManager = new ResultsPersistenceManager();
	}
	
	@Override
	public AbstractPluginPageWrapper getPageWrapper() {
		return analysisPageReference;
	}

	public void setSimulationResult(Result simulationResult, EngineConfigChoice configChoice) {
		this.simulationResult = simulationResult;
		analysisPageReference.getAnalysisPage().setResultModel(configChoice, simulationResult);
		usedEngineConfig = configChoice.getCurrentlyChoosenConfig();
		commonConfig = configChoice.getCommonConfig();
		getMainController().setToForeground(this);
	}

	public void exportResults(String filepathFromDialog) {
		try {
			resultPersistenceManager.saveResultToFile(filepathFromDialog, simulationResult, commonConfig, usedEngineConfig);
		} catch (IOException e) {
			getMessageBoxManager().showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

}
