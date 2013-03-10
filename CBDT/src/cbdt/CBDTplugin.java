package cbdt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.AnalysisController;
import cbdt.control.ParametersController;
import cbdt.view.MenuFactory;

public class CBDTplugin implements ISimulationPlugin {

	private ParametersController paramsController;
	private AnalysisController analysisController;

	/**
	 * Obligatory nullary constructor.
	 */
	public CBDTplugin(){
	}
	
	@Override
	public void setForegroundManager(IForegroundManager foregroundManager) {
		paramsController = new ParametersController(foregroundManager);
		analysisController = new AnalysisController(foregroundManager);
	}

	@Override
	public String getName() {
		return "CBDT";
	}

	@Override
	public List<AbstractPluginPageCompositeWrapper> getPageFactories() {
		List<AbstractPluginPageCompositeWrapper> pageFactories = new ArrayList<AbstractPluginPageCompositeWrapper>();
		
		pageFactories.add(paramsController.getPageWrapper());		
		pageFactories.add(analysisController.getPageWrapper());
		
		return pageFactories;
	}

	@Override
	public Menu getMenu(Decorations shell, Menu menuBar, int index) {
		MenuFactory menuFactory = new MenuFactory();
		return menuFactory.getMenu(shell, menuBar, index, paramsController);
	}


}
