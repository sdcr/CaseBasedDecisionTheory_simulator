package simulation.core.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IExtensionChangeHandler;
import org.eclipse.core.runtime.dynamichelpers.IExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class SimulationPluginManager extends Observable implements
		IExtensionChangeHandler {// IRegistryEventListener {

	public static final String EXTENSION_POINT_ID = "simulation.extensionpoint.simulationplugin";
	private IExtensionRegistry extensionRegistry;

	/**
	 * Sets up itself as the listener for new simulation plugin extensions.
	 */
	public SimulationPluginManager() {
		extensionRegistry = RegistryFactory.getRegistry();
		IExtensionPoint ep = extensionRegistry
				.getExtensionPoint(SimulationPluginManager.EXTENSION_POINT_ID);
		IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
		ExtensionTracker tracker = new ExtensionTracker(extensionRegistry);
		tracker.registerHandler(this, filter);
	}

	public List<ISimulationPlugin> getActiveISimulationPlugins() {
		List<ISimulationPlugin> retVal = new ArrayList<ISimulationPlugin>();
		
		IConfigurationElement[] config = extensionRegistry
				.getConfigurationElementsFor(SimulationPluginManager.EXTENSION_POINT_ID);
		try {
			for (IConfigurationElement e : config) {
				System.out.println("Evaluating extension");
				final Object o = e.createExecutableExtension("class");
				if (o instanceof ISimulationPlugin) {
					retVal.add((ISimulationPlugin)o);
					
//					ISafeRunnable runnable = new ISafeRunnable() {
//						@Override
//						public void handleException(Throwable e) {
//							System.out.println("Exception in client");
//						}
//
//						@Override
//						public void run() throws Exception {
//							// TODO add plugin to model
//						}
//					};
//					SafeRunner.run(runnable);
				}
			}
		} catch (CoreException ex) {
			System.out.println(ex.getMessage());
		}
		return retVal;
	}

	@Override
	public void addExtension(IExtensionTracker tracker, IExtension extension) {
		IConfigurationElement[] configurationElements = extension
				.getConfigurationElements();
		for (IConfigurationElement config : configurationElements) {
			try {
				Object o = config.createExecutableExtension("class");
				if (o instanceof ISimulationPlugin) {
					// TODO add plugin to model
					setChanged();
					notifyObservers();
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
