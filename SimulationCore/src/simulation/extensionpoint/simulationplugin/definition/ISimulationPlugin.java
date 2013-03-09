package simulation.extensionpoint.simulationplugin.definition;

import java.util.List;

import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;


public interface ISimulationPlugin {

	/**
	 * @return the name of the simulation plugin which should be displayed as
	 *         part of the simulation core window.
	 */
	String getName();
	
	/**
	 * 
	 * @param shell The shell which functions as the parent for the menu.
	 * @param menuBar The menubar which should be used for the menu.
	 * @param index The index of the position on the menubar at which the menu should be created.  
	 * @return The menu which is displayed for this plugin.
	 */
	Menu getMenu(Decorations shell, Menu menuBar, int index);
	
	/**
	 * @return A list of ISimulationPluginPageWrapper, whose contents will be displayed on the 
	 * plugin pane of the SimulationCore application.
	 */
	List<ISimulationPluginPageFactory> getPageFactories();
}
