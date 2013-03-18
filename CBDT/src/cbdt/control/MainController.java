package cbdt.control;

import java.lang.reflect.InvocationTargetException;

import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.pages.AbstractPageController;
import cbdt.control.pages.AnalysisPageController;
import cbdt.control.pages.ParametersPageController;
import cbdt.control.simulation.EngineContext;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.view.MessageBoxManager;

public class MainController {

	private EngineContext simulationEngine;

	@SuppressWarnings("unused")
	private ParametersPageController parametersController;
	@SuppressWarnings("unused")
	private AnalysisPageController analysisController;

	private MessageBoxManager messageBoxManager;
	private IForegroundManager foregroundManager;

	public MainController(AnalysisPageController analysisController,
			ParametersPageController parametersController,
			IForegroundManager foregroundManager) {
		this.analysisController = analysisController;
		this.parametersController = parametersController;
		this.foregroundManager = foregroundManager;

		messageBoxManager = new MessageBoxManager(foregroundManager.getShell());
		simulationEngine = new EngineContext(foregroundManager.getShell());
	}

	public void computeCDBTSimulation(Parameters parameters, AbstractEngineConfiguration engineConfig) {
		simulationEngine.setEngineConfig(engineConfig);
		try {
			simulationEngine.performSimulation(parameters);
		} catch (InterruptedException e) {
		} catch (InvocationTargetException e) {
			if(e.getCause() instanceof OutOfMemoryError){
				messageBoxManager.showErrorMessage("Out of memory error.");
			} else {
				messageBoxManager.showErrorMessage("An unknown error occured error.");
			}
			e.printStackTrace();
		}
	}

	public void setToForeground(AbstractPageController pageController) {
		foregroundManager.setToForeground(pageController.getPageWrapper());
	}

	public MessageBoxManager getMessageBoxManager() {
		return messageBoxManager;
	}
}
