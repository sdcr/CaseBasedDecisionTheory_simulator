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
import cbdt.control.analysispage.AnalysisPageController;
import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.view.menu.MenuFactory;

/**
 * The main class defining the CBDT simulation plugin.
 * It forms the connection point between the simulation frame, and manages the 
 * controllers for the different pages.
 * It is also responsible for the instantiation of the different parts of the plugin.
 * @author Stephan da Costa Ribeiro
 */
public class CBDTplugin implements ISimulationPlugin {

	/**
	 * The controller of the parameters page.
	 */
	private ParametersConfigPageController parametersController;

	/**
	 * The controller of the analysis page.
	 */
	private AnalysisPageController analysisController;

	/**
	 * Obligatory nullary constructor. Instantiates a log manager.
	 */
	public CBDTplugin(){
		LogManager logManager = new LogManager();
		logManager.initLogging();
	}
	
	@Override
	public void setForegroundManager(IForegroundManager foregroundManager) {
		parametersController = new ParametersConfigPageController();
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
		
		pageWrappers.add(parametersController.getPageWrapper());		
		pageWrappers.add(analysisController.getPageWrapper());
		
		return pageWrappers;
	}

	@Override
	public Menu getMenu(Decorations shell, Menu menuBar, int index) {
		MenuFactory menuFactory = new MenuFactory();
		return menuFactory.getMenu(shell, menuBar, index, parametersController);
	}


}
