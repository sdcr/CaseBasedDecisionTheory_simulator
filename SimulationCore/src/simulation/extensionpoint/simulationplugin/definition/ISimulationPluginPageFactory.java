package simulation.extensionpoint.simulationplugin.definition;

import org.eclipse.swt.widgets.Composite;

/**
 * The ISimulationPluginPageContentWrapper models one page in the simulation frame.
 * It is to be implemented once for every page that is to be displayed in the simulation frame. 
 * @author S-lenovo
 */
public interface ISimulationPluginPageFactory {

	/**
	 * @return The name of this page. It will be displayed in the menu of the simulation frame.
	 */
	public String getName();
	
	/**
	 * Instantiates the Composite and all child elements which are to be displayed. The
	 * parent of the instantiated composite should be the passed parent. 
	 * @param parent The parent composite of the page content. 
	 * @return The content that is to be displayed in the page of the simulation frame.
	 */
	public Composite getPageComposite(Composite parent);
	
}
