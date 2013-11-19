package cbdt.control.pages;

import java.io.IOException;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.persistence.results.IResultsPersistenceManager;
import cbdt.control.persistence.results.ResultsPersistenceManager;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
import cbdt.model.result.Result;
import cbdt.view.analysis.AnalysisPageWrapper;

public class AnalysisPageController extends AbstractPageController {

	/* view references */
	/**
	 * The wrapper of the associated analysis page.
	 */
	private AnalysisPageWrapper analysisPageWrapper;

	/* Model references */
	private AbstractEngineConfiguration usedEngineConfig;
	private CommonEngineConfiguration usedCommonConfig;
	private Result simulationResult;

	/**
	 * The persistence manager for simulation results.
	 */
	private IResultsPersistenceManager resultPersistenceManager;
	
	/**
	 * Constructor.
	 */
	public AnalysisPageController() {
		analysisPageWrapper = new AnalysisPageWrapper(this);
		resultPersistenceManager = new ResultsPersistenceManager();
	}
	
	@Override
	public AbstractPluginPageWrapper getPageWrapper() {
		return analysisPageWrapper;
	}

	/**
	 * Fills the analysis page with simulation results and the engine config choice data,
	 * and puts it in foreground.
	 * @param simulationResult The result of a simulation.
	 * @param configChoice The engine configuration used for the simulation.
	 */
	public void setSimulationResult(Result simulationResult, EngineConfigChoice configChoice) {
		this.simulationResult = simulationResult;
		analysisPageWrapper.getAnalysisPage().setResultModel(configChoice, simulationResult);
		usedEngineConfig = configChoice.getCurrentlyChoosenConfig();
		usedCommonConfig = configChoice.getCommonConfig();
		getMainController().setToForeground(this);
	}

	/**
	 * Exports the currently hold simulation results, as well as the used common 
	 * simulation configuration, and the engine configuration. The filepath under which to export 
	 * these data.
	 * @param filepath
	 */
	public void exportResults(String filepath) {
		try {
			resultPersistenceManager.saveResultToFile(filepath, simulationResult, usedCommonConfig, usedEngineConfig);
		} catch (IOException e) {
			getMessageBoxManager().showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

}
