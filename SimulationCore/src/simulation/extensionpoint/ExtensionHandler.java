package simulation.extensionpoint;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;

import simulation.extensionpoint.simulationplugin.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.handlers.SimulationPluginViewIntegrator;

public class ExtensionHandler implements IExtensionChangeHandler{//IRegistryEventListener {


	private SimulationPluginViewIntegrator simulationUnitIntegrator;

	public ExtensionHandler() {
		this.simulationUnitIntegrator = new SimulationPluginViewIntegrator();
	}

	@Override
	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		IConfigurationElement[] configurationElements = extension.getConfigurationElements();
		for(IConfigurationElement config : configurationElements){
			try {
				Object o = config.createExecutableExtension("class");
				if(o instanceof ISimulationPlugin){
					simulationUnitIntegrator.integrateSimulationUnit((ISimulationPlugin) o);
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
