package cbdt.control.analysispage;

import java.io.IOException;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.AbstractPageController;
import cbdt.control.persistence.results.IResultsPersistenceManager;
import cbdt.control.persistence.results.ResultsPersistenceManager;
import cbdt.model.config.SimulationConfig;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.result.Result;
import cbdt.view.analysispage.AnalysisPageWrapper;

/**
 * The controller for the analysis page. It handles all the requests, which are
 * fired by user interaction with the GUI elements in this page.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class AnalysisPageController extends AbstractPageController {

	/* view references */
	/**
	 * The wrapper of the associated analysis page.
	 */
	private AnalysisPageWrapper analysisPageWrapper;

	/* Model references */
	/**
	 * The {@link AbstractEngineConfig} used for the simulation computation.
	 */
	private AbstractEngineConfig usedEngineConfig;

	/**
	 * The {@link CommonConfig} used for the simulation computation.
	 */
	private CommonConfig usedCommonConfig;

	/**
	 * The {@link Result}, which is to be displayed by the view elements this
	 * {@link AnalysisPageController} manages.
	 */
	private Result simulationResult;

	/**
	 * The {@link IResultsPersistenceManager} for exporting simulation results.
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
	 * Fills the analysis page with data from {@link Result} and
	 * {@link SimulationConfig}, and puts it in foreground.
	 * 
	 * @param simulationResult
	 *            The result of a simulation.
	 * @param simulationConfig
	 *            The engine configuration used for the simulation.
	 */
	public void setSimulationResult(Result simulationResult,
			SimulationConfig simulationConfig) {
		this.simulationResult = simulationResult;
		analysisPageWrapper.getAnalysisPage().setResult(simulationConfig,
				simulationResult);
		usedEngineConfig = simulationConfig.getCurrentlyChosenEngineConfig();
		usedCommonConfig = simulationConfig.getCommonConfig();
		getMainController().setToForeground(this);
	}

	/**
	 * Exports the currently hold simulation {@link Result}s, as well as the
	 * used {@link CommonConfig} and the {@link AbstractEngineConfig}.
	 * 
	 * @param filepath
	 *            The file path under which to export the data.
	 */
	public void exportResults(String filepath) {
		try {
			resultPersistenceManager.saveResultToFile(filepath,
					simulationResult, usedCommonConfig, usedEngineConfig);
		} catch (IOException e) {
			getMainController().getMessageBoxManager().showErrorMessage(
					e.getMessage());
			e.printStackTrace();
		}
	}

}
