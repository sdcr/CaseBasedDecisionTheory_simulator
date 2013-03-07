package simulation.core.view.menu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.control.SimulationPluginManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class MenuManager {

	private static final int PLUGINS_MENU_POSITION_INDEX = 1;
	private Menu menuBar;
	private MenuItem fileAddPluginItem;
	private Menu removePluginMenu;
	
	private Map<ISimulationPlugin,MenuItem> pluginToRemoveMenuItem;
	private Map<ISimulationPlugin,Menu> pluginToPluginsMenu;
	private Controller controller;
	private Shell shell;
	private Menu helpMenu;
	private MenuItem helpMenuHeader;
	
	
	public MenuManager(Controller controller) {
		this.controller = controller;
	}
	
	public void createMenuBar(Shell shell){
		this.shell = shell;
		menuBar = new Menu(shell, SWT.BAR);
		
		pluginToRemoveMenuItem = new HashMap<ISimulationPlugin,MenuItem>();
		pluginToPluginsMenu = new HashMap<ISimulationPlugin,Menu>();
		
		createFileMenu();
		
		createHelpMenu();
	    
	    shell.setMenuBar(menuBar);
	}

	private void createHelpMenu() {
		helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("&Help");
	    helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);
	    MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("&Get Help");
	}

	private void createFileMenu() {
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");
	    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    fileAddPluginItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileAddPluginItem.setText("&Add a plugin");
	    fileAddPluginItem.addSelectionListener(new AddPluginMenuItemSelectionListener(shell, controller));
	    
	    MenuItem removePluginMenuHeader = new MenuItem(fileMenu, SWT.CASCADE);
	    removePluginMenuHeader.setText("&Remove plugin...");
	    removePluginMenu = new Menu(shell, SWT.DROP_DOWN);
	    removePluginMenuHeader.setMenu(removePluginMenu);
	}

	public void update(SimulationPluginManager pluginManager) {
		List<ISimulationPlugin> activeSimPlugins = pluginManager.getISimulationPlugins();
			
		for (ISimulationPlugin activePlugin : activeSimPlugins) {
			if (!pluginToRemoveMenuItem.keySet().contains(activePlugin)) {
				// create the plugins menu item and remove menu item
				addMenuItems(activePlugin);
			}
		}
		for (ISimulationPlugin shownPlugin : pluginToRemoveMenuItem.keySet()) {
			if (!activeSimPlugins.contains(shownPlugin)) {
				// remove the remove menu item and the menu for the shownplugin
				// from
			}
		}
	
	}

	private void addMenuItems(ISimulationPlugin activePlugin) {
		MenuItem removePluginMenuItem = new MenuItem(removePluginMenu, SWT.PUSH);
		removePluginMenuItem.addSelectionListener(new RemovePluginSelectionListener(controller, activePlugin));
		removePluginMenuItem.setText(activePlugin.getName());
		pluginToRemoveMenuItem.put(activePlugin, removePluginMenuItem);
		
		Menu pluginsMenu = activePlugin.getMenu(shell, menuBar, PLUGINS_MENU_POSITION_INDEX);
		pluginToPluginsMenu.put(activePlugin, pluginsMenu);
	}
	
	
}
