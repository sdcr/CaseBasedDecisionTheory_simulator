package simulation.extensionpoint.simulationplugin.handlers;

import simulation.extensionpoint.simulationplugin.ISimulationPlugin;

public class SimulationPluginViewIntegrator {
	
	public static final String EXTENSION_POINT_ID = "simulation.extensionpoint.simulationunit";
	
	public void integrateSimulationUnit(ISimulationPlugin simulationUnit){
		System.out.println("integrating the simulation unit into the plugins bar");
	}
}
