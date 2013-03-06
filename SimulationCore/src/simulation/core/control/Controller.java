package simulation.core.control;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import simulation.core.model.SimPluginStore;
import simulation.core.view.MainView;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class Controller {

	private BundleContext context;
	private SimPluginStore pluginStore;
	private MainView mainView;

	public Controller(BundleContext context) {
		this.context = context;
		
//		pluginStore = new SimPluginStore();

		SimulationPluginManager pluginManager = new SimulationPluginManager();
		// add extensions to model which are already "installed"
		for(ISimulationPlugin iSimPlugin : pluginManager.getActiveISimulationPlugins()){
//			pluginStore.addSimPlugin(iSimPlugin);	
		}
		
		mainView = new MainView(this);
		mainView.setPluginManager(pluginManager);
//		pluginStore.setMainView(mainView);		
//		mainView.updateFromModel();
		mainView.startView();

	}

	public void shellDisposed() {
		Bundle systemBundle = context.getBundle(0);
		try {
			systemBundle.stop();
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}

	public void installBundle(String filePath) {
    	File f = new File(filePath);
    	FileInputStream fileStream;
    	
    	Bundle newBundle = null;
    	try {
    		fileStream = new FileInputStream(f);
    		//filePath here may become a problem
    		newBundle = context.installBundle(filePath, fileStream);
    		String symName = newBundle.getSymbolicName();
    		newBundle.start();
    	} catch (BundleException e1) {
    		try {
				newBundle.uninstall();
			} catch (BundleException e) {
				System.out.println("Could not uninstall the bundle which failed to start or install.");
				e.printStackTrace();
			}
    		mainView.showMessage("Could not install the selected bundle.");
    		e1.printStackTrace();
    	} catch (FileNotFoundException e1) {
    		mainView.showMessage("Could not find the selected file.");
    		e1.printStackTrace();
    	}
		
		
	}

}
