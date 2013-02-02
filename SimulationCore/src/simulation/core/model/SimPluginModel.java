package simulation.core.model;

import simulation.extensionpoint.simulationplugin.ISimulationPlugin;


public class SimPluginModel {

	private ISimulationPlugin wrappedPlugin;

	public SimPluginModel(ISimulationPlugin iSimulationPlugin) {
		wrappedPlugin = iSimulationPlugin;
	}

	
}
