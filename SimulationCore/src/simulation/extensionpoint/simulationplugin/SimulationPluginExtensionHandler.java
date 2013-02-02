package simulation.extensionpoint.simulationplugin;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;


public class SimulationPluginExtensionHandler implements IExtensionChangeHandler{//IRegistryEventListener {

	public static final String EXTENSION_POINT_ID = "simulation.extensionpoint.simulationplugin";
	
	private SimulationPluginViewIntegrator simulationPluginIntegrator;

	public SimulationPluginExtensionHandler() {
		this.simulationPluginIntegrator = new SimulationPluginViewIntegrator();
	}

	@Override
	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		IConfigurationElement[] configurationElements = extension.getConfigurationElements();
		for(IConfigurationElement config : configurationElements){
			try {
				Object o = config.createExecutableExtension("class");
				if(o instanceof ISimulationPlugin){
					simulationPluginIntegrator.integrateSimulationUnit((ISimulationPlugin) o);
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
