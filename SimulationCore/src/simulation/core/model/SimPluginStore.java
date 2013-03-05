package simulation.core.model;

import java.util.ArrayList;
import java.util.List;

import simulation.core.view.MainView;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageWrapper;

public class SimPluginStore {

	List<ISimulationPlugin> activeSimPlugins;
	private MainView mainView;
	
	private ISimulationPluginPageWrapper foregroundPaneContent;
	
	public SimPluginStore() {
		activeSimPlugins = new ArrayList<ISimulationPlugin>();
	}
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void addSimPlugin(ISimulationPlugin newISimulationPlugin){
		activeSimPlugins.add(newISimulationPlugin);
//		if(mainView != null)
//			mainView.updateFromModel();
	}
	
	public List<ISimulationPlugin> getSimulationPlugins(){
		return activeSimPlugins;
	}

	public ISimulationPluginPageWrapper getForegroundPaneContent() {
		return foregroundPaneContent;
	}

	public void setForegroundPaneContent(ISimulationPluginPageWrapper foregroundPaneContent) {
		this.foregroundPaneContent = foregroundPaneContent;
	}

}
