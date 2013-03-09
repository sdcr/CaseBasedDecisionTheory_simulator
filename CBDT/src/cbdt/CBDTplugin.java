package cbdt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;
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
	public List<ISimulationPluginPageFactory> getPageFactories() {
		Controller controller = new Controller();
		return controller.getPaneContents();
	}

	@Override
	public Menu getMenu(Decorations shell, Menu menuBar, int index) {
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
