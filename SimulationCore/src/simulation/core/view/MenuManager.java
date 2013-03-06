package simulation.core.view;

import java.util.HashMap;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.control.SimulationPluginManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class MenuManager implements Observer {

	private Menu menuBar;
	private MenuItem fileAddPluginItem;
	private Menu removePluginMenu;
	
	public void createMenuBar(Shell shell){
		menuBar = new Menu(shell, SWT.BAR);
		
		createFileMenu(shell);
		
		createHelpMenu(shell);
	    
	    shell.setMenuBar(menuBar);
	}

	private void createHelpMenu(Shell shell) {
		MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("&Help");
	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);
	    MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("&Get Help");
	}

	private void createFileMenu(Shell shell) {
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");
	    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    fileAddPluginItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileAddPluginItem.setText("&Add a plugin");
	    
	    MenuItem removePluginMenuHeader = new MenuItem(fileMenu, SWT.CASCADE);
	    removePluginMenuHeader.setText("&Remove plugin...");
	    removePluginMenu = new Menu(shell, SWT.DROP_DOWN);
	    removePluginMenuHeader.setMenu(removePluginMenu);
	    
	    MenuItem fileRemovePluginItem = new MenuItem(removePluginMenu, SWT.PUSH);
	    fileRemovePluginItem.setText("&Plugin name here");
//	    fileRemovePluginItem.addSelectionListener(new AddPluginMenuItemSelectionListener(shell, controller));
//	    shownPluginToRemoveMenuItem = new HashMap<ISimulationPlugin, MenuItem>();
	}
	
	public void initSelectionListeners(Controller controller){
//		fileAddPluginItem.addSelectionListener(new AddPluginMenuItemSelectionListener(shell, controller));
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		// TODO update the shown menu
		if(arg0 instanceof SimulationPluginManager){
			MenuItem[] currentRemovePluginItems = removePluginMenu.getItems();
			SimulationPluginManager pluginManager = (SimulationPluginManager)arg0;
			
			List<ISimulationPlugin> activeSimPlugins = pluginManager.getActiveISimulationPlugins();
			
//			menuManager.update()
//			
//			for(ISimulationPlugin plugin : activeSimPlugins){
//				if(!shownPluginToRemoveMenuItem.keySet().contains(plugin)){
//					//create the plugins menu item and remove menu item 
//				}
//			}
//			for()
//			
		}
	}
}
