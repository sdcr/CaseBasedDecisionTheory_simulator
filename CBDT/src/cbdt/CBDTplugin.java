package cbdt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.LogManager;
import cbdt.control.MainController;
import cbdt.control.pages.AnalysisPageController;
import cbdt.control.pages.ParametersPageController;
import cbdt.view.menu.MenuFactory;

public class CBDTplugin implements ISimulationPlugin {

	private ParametersPageController parametersController;
	private AnalysisPageController analysisController;

	/**
	 * Obligatory nullary constructor.
	 */
	public CBDTplugin(){
		LogManager logManager = new LogManager();
		logManager.initLogging();
	}
	
	@Override
	public void setForegroundManager(IForegroundManager foregroundManager) {
		parametersController = new ParametersPageController();
		analysisController = new AnalysisPageController();
		MainController mainController = new MainController(analysisController, parametersController, foregroundManager);
		parametersController.setMainController(mainController);
		analysisController.setMainController(mainController);
	}

	@Override
	public String getName() {
		return "CBDT";
	}

	@Override
	public List<AbstractPluginPageWrapper> getPageWrappers() {
		List<AbstractPluginPageWrapper> pageWrappers = new ArrayList<AbstractPluginPageWrapper>();
		
		pageWrappers.add(parametersController.getPageReference());		
		pageWrappers.add(analysisController.getPageReference());
		
		return pageWrappers;
	}

	@Override
	public Menu getMenu(Decorations shell, Menu menuBar, int index) {
		MenuFactory menuFactory = new MenuFactory();
		return menuFactory.getMenu(shell, menuBar, index, parametersController);
	}


}
