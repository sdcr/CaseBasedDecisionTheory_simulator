package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import cbdt.view.parameters.menu.ParametersMenuFactory;

public class MenuFactory {

	public Menu getMenu(Decorations shell, Menu menuBar, int index){
		Menu cbdtMenu = new Menu(shell, SWT.DROP_DOWN);
	    MenuItem cbdtMenuHeader = new MenuItem(menuBar, SWT.CASCADE, index);
	    cbdtMenuHeader.setText("&CBDT");
	    cbdtMenuHeader.setMenu(cbdtMenu);
	    
	    ParametersMenuFactory parametersMenuFactory = new ParametersMenuFactory();
	    parametersMenuFactory.createParameterMenuItems(cbdtMenu);

		return cbdtMenu;
	}
}
