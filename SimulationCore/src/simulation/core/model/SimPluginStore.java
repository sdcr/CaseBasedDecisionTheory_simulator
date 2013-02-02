package simulation.core.model;

import java.util.ArrayList;
import java.util.List;

import simulation.extensionpoint.simulationplugin.ISimulationPlugin;

public class SimPluginStore {

	List<SimPluginModel> activeSimPlugins;
	
	public SimPluginStore() {
		activeSimPlugins = new ArrayList<SimPluginModel>();
	}
	
	public void addSimPlugin(ISimulationPlugin newISimulationPlugin){
		activeSimPlugins.add(new SimPluginModel(newISimulationPlugin));
	}
}
