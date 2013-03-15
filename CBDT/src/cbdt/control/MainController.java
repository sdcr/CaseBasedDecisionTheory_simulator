package cbdt.control;

import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.algorithm.EngineContext;
import cbdt.control.pages.AbstractPageController;
import cbdt.control.pages.AnalysisPageController;
import cbdt.control.pages.ParametersPageController;
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

		simulationEngine = new EngineContext();
	}

	public void computeCDBTSimulation(Parameters parameters, AbstractEngineConfiguration engineConfig) {
		simulationEngine.setEngineConfig(engineConfig);
		simulationEngine.performSimulation(parameters);
	}

	public void setToForeground(AbstractPageController pageController) {
		foregroundManager.setToForeground(pageController.getPageWrapper());
	}

	public MessageBoxManager getMessageBoxManager() {
		return messageBoxManager;
	}
}
