package cbdt.control;

import java.lang.reflect.InvocationTargetException;

import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.analysispage.AnalysisPageController;
import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.control.simulation.EngineContext;
import cbdt.control.validators.InvalidActorActionException;
import cbdt.model.config.SimulationConfig;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.Result;
import cbdt.view.MessageBoxManager;

//YELLOW
/**
 * The controller of the CBDT plugin, within the MVC architecture of the plugin.
 * It is responsible of instantiating the controllers for the individual view pages,
 * the MessageBoxManager, and the EngineContext.
 * It accepts requests which connect the major components of the plugin. For example, 
 * requests to start the simulation computation.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class MainController {

	private EngineContext simulationEngine;

	/**
	 * The page controller for the parameters-config page.
	 */
	private ParametersConfigPageController parametersConfigPageController;

	/**
	 * The page controller for the analysis page.
	 */
	private AnalysisPageController analysisPageController;

	/**
	 * The manager for messages to the user via dialogs.
	 */
	private MessageBoxManager messageBoxManager;

	/**
	 * The manager in charge of putting a page into the foreground.
	 */
	private IForegroundManager foregroundManager;

	/**
	 * The constructor. It initializes the controllers of the pages, the
	 * MessageBoxManager and the Engine Context.
	 * 
	 * @param foregroundManager
	 *            The IForegroundManager as given by the simulation frame.
	 */
	public MainController(IForegroundManager foregroundManager) {
		this.foregroundManager = foregroundManager;

		this.parametersConfigPageController = new ParametersConfigPageController();
		parametersConfigPageController.setMainController(this);
		this.analysisPageController = new AnalysisPageController();
		analysisPageController.setMainController(this);

		messageBoxManager = new MessageBoxManager(foregroundManager.getShell());
		simulationEngine = new EngineContext(foregroundManager.getShell());
	}

	// TODO write javadoc
	public void computeCDBTSimulation(Parameters parameters,
			SimulationConfig configChoice) {
		simulationEngine.setEngineConfig(
				configChoice.getCurrentlyChosenEngineConfig(),
				configChoice.getCommonConfig());
		try {
			Result result = simulationEngine.performSimulation(parameters);
			setToForeground(analysisPageController);
			analysisPageController.setSimulationResult(result, configChoice);
		} catch (InterruptedException e) {
			messageBoxManager.showInfoMessage("Computation aborted.");
			System.gc();
		} catch (InvocationTargetException e) {
			if (e.getCause() instanceof OutOfMemoryError) {
				messageBoxManager.showErrorMessage("OutOfMemoryError: "
						+ e.getCause().getMessage());
			} else {
				messageBoxManager
						.showErrorMessage("An unknown error occured error.");
			}
			e.printStackTrace();
		} catch (InvalidActorActionException e) {
			messageBoxManager.showErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (RuntimeException e) {
			messageBoxManager.showErrorMessage(e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * Put the page managed by the pageController in foreground.
	 * 
	 * @param pageController
	 */
	public void setToForeground(AbstractPageController pageController) {
		foregroundManager.setToForeground(pageController.getPageWrapper());
	}

	/**
	 * @return the MessageBoxManager
	 */
	public MessageBoxManager getMessageBoxManager() {
		return messageBoxManager;
	}

	/**
	 * @return the ParametersConfigPageController
	 */
	public ParametersConfigPageController getParametersConfigPageController() {
		return parametersConfigPageController;
	}

	/**
	 * @return the AnalysisPageController
	 */
	public AnalysisPageController getAnalysisPageController() {
		return analysisPageController;
	}

}
