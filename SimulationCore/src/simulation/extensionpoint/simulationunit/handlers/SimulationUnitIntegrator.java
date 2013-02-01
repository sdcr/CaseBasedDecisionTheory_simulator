package simulation.extensionpoint.simulationunit.handlers;

import simulation.extensionpoint.simulationunit.ISimulationUnit;

public class SimulationUnitIntegrator {
	
	public static final String EXTENSION_POINT_ID = "simulation.extensionpoint.simulationunit";
	
	public void integrateSimulationUnit(ISimulationUnit simulationUnit){
		System.out.println("integrating the simulation unit into the plugins bar");
	}
}
