package simulation.extensionpoint.simulationplugin.definition;

import java.util.List;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Shell;


public interface ISimulationPlugin {

	/**
	 * @return the name of the simulation plugin which should be displayed as
	 *         part of the simulation core window.
	 */
	String getName();

	/**
	 * @param shell
	 * @return The menu which is displayed for this plugin.
	 */
	Menu getMenu(Shell shell, Menu menuBar);
	
	List<ISimulationPluginPageWrapper> getPageContents();
}
