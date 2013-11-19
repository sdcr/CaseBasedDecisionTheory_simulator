package simulation.extensionpoint.simulationplugin.definition;

import java.util.List;

import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Menu;

import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;


public interface ISimulationPlugin {

	/**
	 * Is called by the simulation framework to give an IForegroundManager 
	 * to the instantiating plugin.
	 * @param foregroundManager The ForegroundManager which can be used to put a
	 * specific plugin page in foreground.
	 */
	public void setForegroundManager(IForegroundManager foregroundManager);
	
	/**
	 * @return the name of the simulation plugin which should be displayed as
	 *         part of the simulation core window.
	 */
	public String getName();
	
	/**
	 * 
	 * @param shell The shell which functions as the parent for the menu.
	 * @param menuBar The menubar which should be used for the menu.
	 * @param index The index of the position on the menubar at which the menu should be created.  
	 * @return The menu which is displayed for this plugin.
	 */
	public Menu getMenu(Decorations shell, Menu menuBar, int index);
	
	/**
	 * @return A list of AbstractPluginPageWrappers, whose wrapped composites will be displayed on the 
	 * plugin pane of the SimulationCore application.
	 */
	public List<AbstractPluginPageWrapper> getPageWrappers();
}
