package cbdt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;
import cbdt.control.AnalysisController;
import cbdt.control.IPageController;
import cbdt.control.ParametersController;
import cbdt.view.MenuFactory;

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
		List<ISimulationPluginPageFactory> pageFactories = new ArrayList<ISimulationPluginPageFactory>();
		
		IPageController paramsController = new ParametersController();
		pageFactories.add(paramsController.getPageFactory());		
		IPageController analysisController = new AnalysisController();
		pageFactories.add(analysisController.getPageFactory());
		
		return pageFactories;
	}

	@Override
	public Menu getMenu(Decorations shell, Menu menuBar, int index) {
		MenuFactory menuFactory = new MenuFactory();
		return menuFactory.getMenu(shell, menuBar, index);
	}

}
