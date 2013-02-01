package simulation.simulationunit.cbdt;

import simulation.extensionpoint.simulationplugin.ISimulationPlugin;

public class Greeter implements ISimulationPlugin {

	public Greeter() {
		// TODO Auto-generated constructor stub
		System.out.println("Greeter created");
	}

	@Override
	public void sayHello() {
		System.out.println("Hello froooooom CBDT Greeter");
	}

}
