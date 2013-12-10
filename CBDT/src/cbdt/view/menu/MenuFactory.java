package cbdt.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import cbdt.control.MainController;
import cbdt.view.menu.listeners.LoadParametersSelectionListener;
import cbdt.view.menu.listeners.SaveParametersSelectionListener;
import cbdt.view.menu.listeners.ShowPluginInfoSelectionListener;

/**
 * This class is able to create the {@link Menu} which is to be shown for the
 * CBDT plugin in the applications menu bar
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class MenuFactory {

	/**
	 * Instantiates and returns a menu for the CBDT plugin.
	 * 
	 * @param shell
	 *            A {@link Decorations} object needed to create the menu items.
	 * @param menuBar
	 *            The {@link Menu} into which the new CBDT header
	 *            {@link MenuItem} should be plassed.
	 * @param index
	 *            The index of the position in the menuBar in which the header
	 *            {@link MenuItem} should be placed.
	 * @param mainController
	 *            The {@link MainController}, on which the actions are called
	 *            when the user clicks one of the {@link MenuItem}s.
	 * @return The {@link Menu} object to which all the created {@link MenuItem}
	 *         s are added.
	 */
	public Menu getMenu(Decorations shell, Menu menuBar, int index,
			MainController mainController) {
		Menu cbdtMenu = new Menu(shell, SWT.DROP_DOWN);
		addHeaderMenuItem(menuBar, index, cbdtMenu);

		addSaveParamsMenuItem(shell, mainController, cbdtMenu);
		addLoadParamsMenuItem(shell, mainController, cbdtMenu);
		new MenuItem(cbdtMenu, SWT.SEPARATOR);
		addShowInfoMenuItem(shell, cbdtMenu);

		return cbdtMenu;
	}

	/**
	 * Adds the {@link MenuItem} for the header to the {@link Menu}.
	 * 
	 * @param menuBar
	 *            The {@link Menu} bar to which the header should be added.
	 * @param index
	 *            The position in the menuBar of the new header.
	 * @param cbdtMenu
	 *            The {@link Menu} which should be accessible from the created
	 *            header.
	 */
	private void addHeaderMenuItem(Menu menuBar, int index, Menu cbdtMenu) {
		MenuItem cbdtMenuHeader = new MenuItem(menuBar, SWT.CASCADE, index);
		cbdtMenuHeader.setText("&CBDT");
		cbdtMenuHeader.setMenu(cbdtMenu);
	}

	/**
	 * Adds a {@link MenuItem} to the menu to show information about the plugin.
	 * 
	 * @param shell
	 * @param cbdtMenu
	 */
	private void addShowInfoMenuItem(Decorations shell, Menu cbdtMenu) {
		MenuItem showInfo = new MenuItem(cbdtMenu, SWT.PUSH);
		showInfo.setText("&Info");
		showInfo.addSelectionListener(new ShowPluginInfoSelectionListener(shell
				.getShell()));
	}

	/**
	 * Adds a {@link MenuItem} to the menu to allow the user to load parameters
	 * from a file.
	 * 
	 * @param shell
	 * @param mainController
	 * @param cbdtMenu
	 */
	private void addLoadParamsMenuItem(Decorations shell,
			MainController mainController, Menu cbdtMenu) {
		MenuItem loadParameters = new MenuItem(cbdtMenu, SWT.PUSH);
		loadParameters.setText("&Open parameters");
		loadParameters
				.addSelectionListener(new LoadParametersSelectionListener(shell
						.getShell(), mainController
						.getParametersConfigPageController()
						.getParametersController()));
	}

	/**
	 * Adds a {@link MenuItem} to the menu to allow the user to save parameters
	 * to a file.
	 * 
	 * @param shell
	 * @param mainController
	 * @param cbdtMenu
	 */
	private void addSaveParamsMenuItem(Decorations shell,
			MainController mainController, Menu cbdtMenu) {
		MenuItem saveParameters = new MenuItem(cbdtMenu, SWT.PUSH);
		saveParameters.setText("&Save parameters");
		saveParameters
				.addSelectionListener(new SaveParametersSelectionListener(shell
						.getShell(), mainController
						.getParametersConfigPageController()
						.getParametersController()));
	}

}
