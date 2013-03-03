package simulation.core.model;

import java.util.ArrayList;
import java.util.List;

import simulation.core.view.MainView;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageContentWrapper;

public class SimPluginStore {

	List<ISimulationPlugin> activeSimPlugins;
	private MainView mainView;
	
	private ISimulationPluginPageContentWrapper foregroundPaneContent;
	
	public SimPluginStore() {
		activeSimPlugins = new ArrayList<ISimulationPlugin>();
	}
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void addSimPlugin(ISimulationPlugin newISimulationPlugin){
		activeSimPlugins.add(newISimulationPlugin);
		if(mainView != null)
			mainView.updateFromModel();
	}
	
	public List<ISimulationPlugin> getSimulationPlugins(){
		return activeSimPlugins;
	}

	public ISimulationPluginPageContentWrapper getForegroundPaneContent() {
		return foregroundPaneContent;
	}

	public void setForegroundPaneContent(ISimulationPluginPageContentWrapper foregroundPaneContent) {
		this.foregroundPaneContent = foregroundPaneContent;
	}

}
