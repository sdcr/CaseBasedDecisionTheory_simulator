package simulation.core.view.menu;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import simulation.core.control.Controller;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class FileMenuWrapper {

	private Menu fileMenu;
	private Controller controller;
	
	private MenuItem addPluginItem;
	private Menu removePluginMenu;
	private MenuItem removePluginMenuHeader;
	
	/**
	 * Wraps around the Menu object which is to be displayed in the menu bar of the application as the file menu. 
	 * The file menu contains MenuItems for adding and removing ISimulation plugins.
	 * @param parent
	 * @param style
	 * @param menuBar
	 * @param controller The controller which should be called upon user interaction with the file menu.
	 */
	public FileMenuWrapper(Decorations parent, int style, Menu menuBar, Controller controller) {
		fileMenu = new Menu(parent, style);
		this.controller = controller;
		
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");
	    fileMenuHeader.setMenu(fileMenu);
	    
	    addPluginItem = new MenuItem(fileMenu, SWT.PUSH);
	    addPluginItem.setText("&Add a simulation plugin");
	    addPluginItem.addSelectionListener(new AddPluginMenuItemSelectionListener(fileMenu.getShell(), controller));
	    
	    removePluginMenuHeader = new MenuItem(fileMenu, SWT.CASCADE);
	    removePluginMenuHeader.setText("&Remove plugin...");
	    removePluginMenu = new Menu(fileMenu.getShell(), SWT.DROP_DOWN);
	    removePluginMenuHeader.setMenu(removePluginMenu);		
	    removePluginMenuHeader.setEnabled(false);
	}

	/**
	 * Adds MenuItems to the remove plugin menu, which allow to remove a specific ISimulationPlugin.
	 * @param plugins
	 */
	public void addRemoveMenuItems(List<ISimulationPlugin> plugins){
		if(plugins!=null && !plugins.isEmpty()){
			removePluginMenuHeader.setEnabled(true);
		}
		for (ISimulationPlugin plugin : plugins) {
			MenuItem removeMenuItem = new MenuItem(removePluginMenu, SWT.PUSH);
			removeMenuItem.setText(plugin.getName());
			removeMenuItem.addSelectionListener(
					new RemovePluginSelectionListener(controller, plugin));
		}
	}
}
