package simulation.core;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import simulation.core.view.WindowRunner;
import simulation.extensionpoint.simulationplugin.SimulationPluginExtensionHandler;
import simulation.extensionpoint.simulationplugin.SimulationPluginViewIntegrator;

public class Activator implements BundleActivator {

	private SimulationPluginExtensionHandler extensionHandler;
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		System.out.println("Hello World from SimulationCore!!");
		initSimulationPluginExtensionPoint();
		WindowRunner windowRunner = new WindowRunner(context);
		windowRunner.showWindow();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World from SimulationCore!!");
	}

	private void initSimulationPluginExtensionPoint(){
		extensionHandler = new SimulationPluginExtensionHandler();

		//set up handling of simulation unit extensions, included by the user.
		IExtensionRegistry reg = RegistryFactory.getRegistry();
		IExtensionPoint ep = reg
				.getExtensionPoint(SimulationPluginExtensionHandler.EXTENSION_POINT_ID);
		IFilter filter = ExtensionTracker.createExtensionPointFilter(ep);
		ExtensionTracker tracker = new ExtensionTracker(reg);
		tracker.registerHandler(extensionHandler, filter);
		
		//add extensions already "installed"
		IExtension[] extensions = ep.getExtensions();
		for (IExtension extension : extensions)
			extensionHandler.addExtension(tracker, extension);
	}
}
