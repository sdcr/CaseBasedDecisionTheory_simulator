package simulation.extensionpoint.simulationplugin.definition;

import java.util.List;


public interface ISimulationPlugin {

	/**
	 * 
	 * @return the name of the simulation plugin which should be displayed as
	 *         part of the simulation core window.
	 */
	String getName();

	/**
	 * @return The content to be displayed in the main pane as part of the
	 *         simulation core window. The map should contain entries where the
	 *         key is the title of the referenced Widget object (e.g. a
	 *         Composite) to be displayed in the main pane.
	 */
//	Map<String, Widget> getMainPaneContent(Composite parent);

	
	List<ISimulationPluginPaneContent> getPaneContents();
}