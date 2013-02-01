package simulation.extensionpoint.simulationunit.handlers;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IRegistryEventListener;

public class SimulationUnitHandler implements IRegistryEventListener {

	public static final String extensionPointID = "simulation.extensionpoint.simulationunit";

	@Override
	public void added(IExtension[] extensions) {
		// TODO Auto-generated method stub
		System.out.println("An extension was added.");
	}

	@Override
	public void removed(IExtension[] extensions) {
		System.out.println("An extension was removed.");
		
	}

	@Override
	public void added(IExtensionPoint[] extensionPoints) {
		// TODO Auto-generated method stub
		System.out.println("An extensionpoint was added.");
	}

	@Override
	public void removed(IExtensionPoint[] extensionPoints) {
		System.out.println("An extensionpoint was removed.");
	}
}
