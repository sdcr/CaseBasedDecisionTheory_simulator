package cbdt.control;

import java.lang.reflect.InvocationTargetException;

import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.pages.AbstractPageController;
import cbdt.control.pages.AnalysisPageController;
import cbdt.control.pages.ParametersPageController;
import cbdt.control.simulation.EngineContext;
import cbdt.control.validators.InvalidActorActionException;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;
import cbdt.view.MessageBoxManager;

public class MainController {

	private EngineContext simulationEngine;

	@SuppressWarnings("unused")
	private ParametersPageController parametersController;
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
			Result result = simulationEngine.performSimulation(parameters);
			setToForeground(analysisController);
			analysisController.setConfig(engineConfig);
			analysisController.setSimulationResult(result);
		} catch (InterruptedException e) {
			messageBoxManager.showInfoMessage(e.getMessage());
			System.gc();
		} catch (InvocationTargetException e) {
			if(e.getCause() instanceof OutOfMemoryError){
				messageBoxManager.showErrorMessage("OutOfMemoryError: "+e.getCause().getMessage());
			} else {
				messageBoxManager.showErrorMessage("An unknown error occured error.");
			}
			e.printStackTrace();
		} catch (InvalidActorActionException e) {
			messageBoxManager.showErrorMessage(e.getMessage());
			e.printStackTrace();
		} catch (RuntimeException e){
			messageBoxManager.showErrorMessage(e.getMessage());
		}
	}

	public void setToForeground(AbstractPageController pageController) {
		foregroundManager.setToForeground(pageController.getPageWrapper());
	}

	public MessageBoxManager getMessageBoxManager() {
		return messageBoxManager;
	}
}
