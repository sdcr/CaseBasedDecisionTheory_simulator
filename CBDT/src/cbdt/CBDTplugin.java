package cbdt;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.control.MainController;
import cbdt.control.pages.AnalysisController;
import cbdt.control.pages.ParametersController;
import cbdt.view.MenuFactory;

public class CBDTplugin implements ISimulationPlugin {

	private ParametersController parametersController;
	private AnalysisController analysisController;

	/**
	 * Obligatory nullary constructor.
	 */
	public CBDTplugin(){
	}
	
	@Override
	public void setForegroundManager(IForegroundManager foregroundManager) {
		parametersController = new ParametersController();
		analysisController = new AnalysisController();
		MainController mainController = new MainController(analysisController, parametersController, foregroundManager);
		parametersController.setMainController(mainController);
		analysisController.setMainController(mainController);
	}

	@Override
	public String getName() {
		return "CBDT";
	}

	@Override
	public List<AbstractPluginPageWrapper> getPageFactories() {
		List<AbstractPluginPageWrapper> pageFactories = new ArrayList<AbstractPluginPageWrapper>();
		
		pageFactories.add(parametersController.getPageWrapper());		
		pageFactories.add(analysisController.getPageWrapper());
		
		return pageFactories;
	}

	@Override
	public Menu getMenu(Decorations shell, Menu menuBar, int index) {
		MenuFactory menuFactory = new MenuFactory();
		return menuFactory.getMenu(shell, menuBar, index, parametersController);
	}


}
