package simulation.core.view.menu;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import simulation.core.control.Controller;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

/**
 * The menu bar hich is to be displayed in the applications shell.
 * @author S-lenovo
 *
 */
public class MenuBar extends Menu{

	private static final int PLUGINS_MENU_POSITION_INDEX_START = 1;
	
	private Decorations shell;
	private FileMenu fileMenu;

	public MenuBar(Decorations shell, Controller controller) {
		super(shell, SWT.BAR);
		this.shell = shell;
		
		shell.setMenuBar(this);
		fileMenu = new FileMenu(shell, SWT.DROP_DOWN, this, controller);
		createHelpMenu();
	}
	
	/**
	 * Creates the help menu and its content.
	 */
	private void createHelpMenu() {
		MenuItem helpMenuHeader = new MenuItem(this, SWT.CASCADE);
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
			plugin.getMenu(shell, this, positionIndex);
			positionIndex++;
		}
	}	
	
}
