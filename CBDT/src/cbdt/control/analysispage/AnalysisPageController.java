package cbdt.control.analysispage;

import java.io.IOException;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.AbstractPageController;
import cbdt.control.persistence.results.IResultsPersistenceManager;
import cbdt.control.persistence.results.ResultsPersistenceManager;
import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.CommonConfig;
import cbdt.model.config.SimulationConfig;
import cbdt.model.result.Result;
import cbdt.view.analysispage.AnalysisPageWrapper;

public class AnalysisPageController extends AbstractPageController {

	/* view references */
	/**
	 * The wrapper of the associated analysis page.
	 */
	private AnalysisPageWrapper analysisPageWrapper;

	/* Model references */
	private AbstractEngineConfig usedEngineConfig;
	private CommonConfig usedCommonConfig;
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
	public void setSimulationResult(Result simulationResult, SimulationConfig configChoice) {
		this.simulationResult = simulationResult;
		analysisPageWrapper.getAnalysisPage().setResultModel(configChoice, simulationResult);
		usedEngineConfig = configChoice.getCurrentlyChosenEngineConfig();
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
