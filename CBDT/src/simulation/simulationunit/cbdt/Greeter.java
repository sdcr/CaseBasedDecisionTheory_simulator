package simulation.simulationunit.cbdt;

import simulation.extensionpoint.simulationunit.ISimulationUnit;

public class Greeter implements ISimulationUnit {

	public Greeter() {
		// TODO Auto-generated constructor stub
		System.out.println("Greeter created");
	}

	@Override
	public void sayHello() {
		System.out.println("Hello froooooom CBDT Greeter");
	}

}
