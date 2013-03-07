package cbdt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageWrapper;
import cbdt.controller.Controller;

public class CBDTplugin implements ISimulationPlugin {

	public CBDTplugin() {
		System.out.println("CBDTplugin created");
	}

	@Override
	public String getName() {
		return "CBDT";
	}

	@Override
	public List<ISimulationPluginPageWrapper> getPageContents() {
		Controller controller = new Controller();
		return controller.getPaneContents();
	}

	@Override
	public Menu getMenu(Shell shell, Menu menuBar, int index) {
		System.out.println("create the menu");
		Menu cbdtMenu = new Menu(shell, SWT.DROP_DOWN);
	    MenuItem cbdtMenuHeader = new MenuItem(menuBar, SWT.CASCADE, index);
	    cbdtMenuHeader.setText("&CBDT");

	    cbdtMenuHeader.setMenu(cbdtMenu);

	    MenuItem fileSaveItem = new MenuItem(cbdtMenu, SWT.PUSH);
	    fileSaveItem.setText("&Bla1");

	    MenuItem fileExitItem = new MenuItem(cbdtMenu, SWT.PUSH);
	    fileExitItem.setText("Bla2");
	    
		return cbdtMenu;
	}

}
