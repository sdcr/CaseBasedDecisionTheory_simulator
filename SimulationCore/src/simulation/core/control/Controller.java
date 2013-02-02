package simulation.core.control;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import simulation.core.model.SimPluginStore;
import simulation.core.view.MainView;
import simulation.extensionpoint.simulationplugin.ISimulationPlugin;

public class Controller {

	private BundleContext context;

	public Controller(BundleContext context) {
		this.context = context;
		
		SimulationPluginExtensionHandler extensionHandler = new SimulationPluginExtensionHandler();
		SimPluginStore pluginStore = new SimPluginStore();

		// add extensions to model which are already "installed"
		for(ISimulationPlugin iSimPlugin : extensionHandler.getActiveISimulationPlugins()){
			pluginStore.addSimPlugin(iSimPlugin);	
		}
		
		new MainView(this);
		
//		IExtension[] extensions = ep.getExtensions();
//		for (IExtension extension : extensions)
//			extensionHandler.addExtension(tracker, extension);


	}

	public void shellDisposed() {
		System.out.println("trying to stop system bundle");
		Bundle systemBundle = context.getBundle(0);
		try {
			systemBundle.stop();
		} catch (BundleException e) {
			e.printStackTrace();
		}
		System.out.println("stopped system bundle");
	}

}
