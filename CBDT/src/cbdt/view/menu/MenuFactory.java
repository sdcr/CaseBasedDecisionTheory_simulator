package cbdt.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import cbdt.control.MainController;
import cbdt.view.menu.listeners.OpenParametersSelectionListener;
import cbdt.view.menu.listeners.SaveParametersSelectionListener;
import cbdt.view.menu.listeners.ShowPluginInfoSelectionListener;

public class MenuFactory {

	public Menu getMenu(Decorations shell, Menu menuBar, int index,
			MainController mainController) {
		Menu cbdtMenu = new Menu(shell, SWT.DROP_DOWN);
		MenuItem cbdtMenuHeader = new MenuItem(menuBar, SWT.CASCADE, index);
		cbdtMenuHeader.setText("&CBDT");
		cbdtMenuHeader.setMenu(cbdtMenu);

		MenuItem saveParameters = new MenuItem(cbdtMenu, SWT.PUSH);
		saveParameters.setText("&Save parameters");
		saveParameters
				.addSelectionListener(new SaveParametersSelectionListener(shell
						.getShell(), mainController.getParametersConfigPageController().getParametersController()));

		MenuItem openParameters = new MenuItem(cbdtMenu, SWT.PUSH);
		openParameters.setText("&Open parameters");
		openParameters
				.addSelectionListener(new OpenParametersSelectionListener(shell
						.getShell(), mainController.getParametersConfigPageController().getParametersController()));

		new MenuItem(cbdtMenu, SWT.SEPARATOR);

		MenuItem showInfo = new MenuItem(cbdtMenu, SWT.PUSH);
		showInfo.setText("&Info");
		showInfo.addSelectionListener(new ShowPluginInfoSelectionListener(shell
				.getShell()));

		return cbdtMenu;
	}

}
