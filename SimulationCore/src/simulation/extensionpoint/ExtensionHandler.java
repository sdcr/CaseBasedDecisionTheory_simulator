package simulation.extensionpoint;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;

import simulation.extensionpoint.simulationunit.ISimulationUnit;
import simulation.extensionpoint.simulationunit.handlers.SimulationUnitIntegrator;

public class ExtensionHandler implements IExtensionChangeHandler{//IRegistryEventListener {


	private SimulationUnitIntegrator simulationUnitIntegrator;

	public ExtensionHandler() {
		this.simulationUnitIntegrator = new SimulationUnitIntegrator();
	}

	@Override
	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		IConfigurationElement[] configurationElements = extension.getConfigurationElements();
		for(IConfigurationElement config : configurationElements){
			try {
				Object o = config.createExecutableExtension("class");
				if(o instanceof ISimulationUnit){
					simulationUnitIntegrator.integrateSimulationUnit((ISimulationUnit) o);
				}
			} catch (CoreException e) {
				e.printStackTrace();
			} 
		}
	}

	@Override
	public void removeExtension(IExtension extension, Object[] objects) {
		System.out.println("Removed an ext");
	}
}
