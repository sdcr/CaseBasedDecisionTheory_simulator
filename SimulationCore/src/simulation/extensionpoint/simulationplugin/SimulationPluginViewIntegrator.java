package simulation.extensionpoint.simulationplugin;

import simulation.core.view.MainWindow;

/**
 * Handles the integration of simulation plugins into the view of the core window.
 * @author S-lenovo
 *
 */
public class SimulationPluginViewIntegrator {
	
	private MainWindow mainWindow;

	public SimulationPluginViewIntegrator(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
	}

	public void integrateSimulationUnit(ISimulationPlugin simulationPlugin){
		System.out.println("integrating the simulation unit into the plugins bar");
		mainWindow.addSimulationPluginView(simulationPlugin.getName(), simulationPlugin.getMainPaneContent());
	}
}
