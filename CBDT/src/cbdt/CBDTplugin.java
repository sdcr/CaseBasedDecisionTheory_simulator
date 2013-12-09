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
import cbdt.view.menu.MenuFactory;

//YELLOW
/**
 * The main class defining the CBDT simulation plugin. It forms the connection
 * point between the simulation frame, and instantiates the
 * {@link MainController}.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class CBDTplugin implements ISimulationPlugin {

	/**
	 * The main controller of the plugin.
	 */
	private MainController mainController;

	/**
	 * Obligatory nullary constructor. Instantiates a {@link LogManager}.
	 */
	public CBDTplugin() {
		LogManager logManager = new LogManager();
		logManager.initLogging();
	}

	@Override
	/**
	 * must be called before getPageWrappers().
	 */
	public void setForegroundManager(IForegroundManager foregroundManager) {
		mainController = new MainController(foregroundManager);
	}

	@Override
	public String getName() {
		return "CBDT";
	}

	@Override
	/**
	 * setForegroundManager(IForegroundManager foregroundManager) must be called before this method is called.
	 */
	public List<AbstractPluginPageWrapper> getPageWrappers() {
		/*
		 * possible problem: mainController may be null. maybe instantiate in
		 * constructor
		 */
		List<AbstractPluginPageWrapper> pageWrappers = new ArrayList<AbstractPluginPageWrapper>();
		pageWrappers.add(mainController.getParametersConfigPageController()
				.getPageWrapper());
		pageWrappers.add(mainController.getAnalysisPageController()
				.getPageWrapper());

		return pageWrappers;
	}

	@Override
	public Menu getMenu(Decorations shell, Menu menuBar, int index) {
		MenuFactory menuFactory = new MenuFactory();
		return menuFactory.getMenu(shell, menuBar, index, mainController);
	}

}
