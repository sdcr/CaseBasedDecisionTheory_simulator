package simulation.core.control;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import simulation.core.model.SimPluginStore;
import simulation.core.view.MainView;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class Controller {

	private BundleContext context;
	private SimPluginStore pluginStore;

	public Controller(BundleContext context) {
		this.context = context;
		
		pluginStore = new SimPluginStore();

		SimulationPluginExtensionHandler extensionHandler = new SimulationPluginExtensionHandler();
		// add extensions to model which are already "installed"
		for(ISimulationPlugin iSimPlugin : extensionHandler.getActiveISimulationPlugins()){
			pluginStore.addSimPlugin(iSimPlugin);	
		}
		
		MainView mainView = new MainView(this, pluginStore);
		pluginStore.setMainView(mainView);		
		mainView.updateFromModel();
		mainView.startView();
		
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

	public void doSth() {
		// TODO Auto-generated method stub
		System.out.println("doSth");
		pluginStore.changeSth();
	}

}
