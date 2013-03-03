package simulation.extensionpoint.simulationplugin.definition;

import java.util.List;


public interface ISimulationPlugin {

	/**
	 * @return the name of the simulation plugin which should be displayed as
	 *         part of the simulation core window.
	 */
	String getName();

	
	List<ISimulationPluginPageContentWrapper> getPageContents();
}
