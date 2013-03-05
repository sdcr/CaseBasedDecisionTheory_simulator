package cbdt;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageContentWrapper;
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
	public List<ISimulationPluginPageContentWrapper> getPageContents() {
		Controller controller = new Controller();
		return controller.getPaneContents();
	}

	@Override
	public Menu getMenu(Shell shell, Menu menuBar) {
		Menu cbdtMenu = new Menu(shell, SWT.DROP_DOWN);
	    MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    fileMenuHeader.setText("&File");

	    fileMenuHeader.setMenu(cbdtMenu);

	    MenuItem fileSaveItem = new MenuItem(cbdtMenu, SWT.PUSH);
	    fileSaveItem.setText("&Save");

	    MenuItem fileExitItem = new MenuItem(cbdtMenu, SWT.PUSH);
	    fileExitItem.setText("E&xit");
	    
		return cbdtMenu;
	}

}
