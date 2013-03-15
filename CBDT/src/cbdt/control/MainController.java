package cbdt.control;

import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.model.Result;
import cbdt.model.engine.ISimulationEngine;
import cbdt.model.engine.TreeStyleSimulationEngine;
import cbdt.model.parameters.Parameters;
import cbdt.view.MessageBoxManager;

public class MainController {

	private ISimulationEngine simulationEngine;

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

		simulationEngine = new TreeStyleSimulationEngine();
	}

	public void computeCDBTSimulation(Parameters parameters) {
		Result result = simulationEngine.computeSimulation(parameters);
	}

	public void setToForeground(AbstractPageController pageController) {
		foregroundManager.setToForeground(pageController.getPageWrapper());
	}

	public MessageBoxManager getMessageBoxManager() {
		return messageBoxManager;
	}
}
