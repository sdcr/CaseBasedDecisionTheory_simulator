package cbdt.control;

import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.algorithm.EngineContext;
import cbdt.control.pages.AbstractPageController;
import cbdt.control.pages.AnalysisController;
import cbdt.control.pages.ParametersController;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.view.MessageBoxManager;

public class MainController {

	private EngineContext simulationEngine;

	private ParametersController parametersController;
	private AnalysisController analysisController;

	private MessageBoxManager messageBoxManager;

	private IForegroundManager foregroundManager;

	public MainController(AnalysisController analysisController,
			ParametersController parametersController,
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
