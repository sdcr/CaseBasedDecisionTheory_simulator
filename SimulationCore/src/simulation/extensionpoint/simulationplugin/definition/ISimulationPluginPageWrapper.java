package simulation.extensionpoint.simulationplugin.definition;

import org.eclipse.swt.widgets.Composite;

/**
 * The ISimulationPluginPageContentWrapper models one page in the simulation frame.
 * It is to be implemented once for every page that is to be displayed in the simulation frame. 
 * @author S-lenovo
 */
public interface ISimulationPluginPageWrapper {

	/**
	 * @return The name of this page. It will be displayed in the menu of the simulation frame.
	 */
	public String getName();
	
	/**
	 * @param parent The parent composite of the page content. 
	 * @return The content that is to be displayed in the page of the simulation frame.
	 */
	public Composite getPageComposite(Composite parent);
	
	/**
	 * @return The ISimulationPlugin class this page belongs to.
	 */
	public ISimulationPlugin getSimulationPlugin();
	
	/**
	 * @param plugin The ISimulationPlugin class this page belongs to.
	 */
	public void setSimulationPlugin(ISimulationPlugin plugin);
	
}
