package simulation.core.view.menu;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import simulation.core.control.Controller;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

/**
 * Wraps around the menu bar which is to be displayed in the applications shell.
 * @author S-lenovo
 */
public class MenuBarWrapper {

	private static final int PLUGINS_MENU_POSITION_INDEX_START = 1;
	
	private Decorations shell;
	private FileMenu fileMenu;
	
	private Menu menuBar;

	public MenuBarWrapper(Decorations shell, Controller controller) {
		menuBar = new Menu(shell, SWT.BAR);
		this.shell = shell;
		
		shell.setMenuBar(menuBar);
		fileMenu = new FileMenu(shell, SWT.DROP_DOWN, menuBar, controller);
		createHelpMenu();
	}
	
	/**
	 * Creates the help menu and its content.
	 */
	private void createHelpMenu() {
		MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("&Help");
	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);
	    MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("&Get Help");
	}
	
	/**
	 * Set all elements of this menu bar according to the simulation plugins passed.
	 * @param simulaionPlugins
	 */
	public void addMenuElements(List<ISimulationPlugin> simulaionPlugins) {
		fileMenu.addRemoveMenuItems(simulaionPlugins);
		
		int positionIndex = PLUGINS_MENU_POSITION_INDEX_START;
		for(ISimulationPlugin plugin : simulaionPlugins){
			plugin.getMenu(shell, menuBar, positionIndex);
			positionIndex++;
		}
	}	
	
}