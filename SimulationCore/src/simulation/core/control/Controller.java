package simulation.core.control;

import java.io.FileNotFoundException;

import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import simulation.core.control.plugins.SimulationPluginManager;
import simulation.core.view.MainViewManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class Controller {

	private BundleContext context;
	private MainViewManager mainView;
	private SimulationPluginManager pluginManager;

	public Controller(BundleContext context) {
		this.context = context;
		pluginManager = new SimulationPluginManager(context);

		mainView = new MainViewManager(this);
		mainView.updateView(pluginManager);
		mainView.startView();
	}

	/**
	 * Stops the SimulationCore application, by stopping the system bundle.
	 */
	public void stopApplication() {
		try {
			getSystemBundle().stop();
		} catch (BundleException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adds a ISimulationplugin instance defined in a bundle. A plugin is defined as added, once its 
	 * bundle is installed and the application has been restarted. 
	 * @param filePath The path to the bundle, which defines the plugin which is to be added.
	 */
	public void addISimulationPlugin(String filePath) {
		try {
			if(mainView.askUser("After the installation, the application has to restart. Proceed?")){
				pluginManager.installBundle(filePath);
				this.restartApplication();
			}
		} catch (FileNotFoundException e) {
    		mainView.showMessage("Could not find the selected file.");
			e.printStackTrace();
		} catch (BundleException e) {
    		mainView.showMessage("Could not install the selected bundle.");
			e.printStackTrace();
		}
	}
	

	/**
	 * Removes an ISimulationPlugin. An ISimulationPlugin is defined as removed once its defining bundle 
	 * has been uninstalled and the application was restarted.
	 * @param plugin
	 */
	public void removeISimulationPlugin(ISimulationPlugin plugin){
		try {
			if(mainView.askUser("After the uninstallation, the application has to restart. Proceed?")){			
				pluginManager.uninstallBundle(plugin);
				this.restartApplication();
			}
		} catch (BundleException e) {
			mainView.showMessage("Could not uninstall the selected bundle.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Restarts the application by updating the system bundle (instead of stopping and starting it) and by 
	 * subsequently disposing the view.
	 */
	private void restartApplication(){
		try {
			getSystemBundle().update();
			mainView.disposeView();
		} catch (BundleException e) {
			mainView.showMessage("The application restart failed.");
			e.printStackTrace();
		}
	}
	
	/**
	 * @return the system Bundle, i.e. the bundle with id=0;
	 */
	private Bundle getSystemBundle(){
		return context.getBundle(0);
	}
}
