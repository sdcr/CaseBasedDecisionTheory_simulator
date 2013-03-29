package cbdt.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import cbdt.control.pages.ParametersPageController;

public class MenuFactory {

	public Menu getMenu(Decorations shell, Menu menuBar, int index, ParametersPageController controller){
		Menu cbdtMenu = new Menu(shell, SWT.DROP_DOWN);
	    MenuItem cbdtMenuHeader = new MenuItem(menuBar, SWT.CASCADE, index);
	    cbdtMenuHeader.setText("&CBDT");
	    cbdtMenuHeader.setMenu(cbdtMenu);
	    
		MenuItem saveParameters = new MenuItem(cbdtMenu, SWT.PUSH);
		saveParameters.setText("&Save parameters");
		saveParameters.addSelectionListener(new SaveParametersSelectionListener(shell.getShell(), controller));
		
		MenuItem openParameters = new MenuItem(cbdtMenu, SWT.PUSH);
		openParameters.setText("&Open parameters");
		openParameters.addSelectionListener(new OpenParametersSelectionListener(shell.getShell(), controller));
	    
		new MenuItem(cbdtMenu, SWT.SEPARATOR);
		
		MenuItem showInfo = new MenuItem(cbdtMenu, SWT.PUSH);
		showInfo.setText("&Info");
		showInfo.addSelectionListener(new ShowPluginInfoSelectionListener(shell.getShell()));
	    
		return cbdtMenu;
	}

}
