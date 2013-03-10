package simulation.extensionpoint.simulationplugin.definition;

import org.eclipse.swt.widgets.Composite;

/**
 * The AbstractPluginPageCompositeWrapper models one page in the simulation frame.
 * It is to be subclassed once for every page that is to be displayed in the simulation frame. 
 * @author S-lenovo
 */
public abstract class AbstractPluginPageCompositeWrapper {

	private Composite simulationPluginPage;
	
	/**
	 * @return The name of this page. It will be displayed in the menu of the simulation frame.
	 */
	public abstract String getName();
	
	/**
	 * Instantiates the Composite and all child elements which are to be displayed. The
	 * parent of the instantiated composite should be the passed parent. 
	 * @param parent The parent composite of the page content. 
	 * @return The content that is to be displayed in the page of the simulation frame.
	 */
	protected abstract Composite getPageComposite(Composite parent);

	/**
	 * Sets the parent of the wrapped plugin page Composite.
	 * If the wrapped plugin page is not yet instantiated, it will be instantiated. 
	 * @param parent
	 */
	public void setParent(Composite parent){
		if(simulationPluginPage==null){
			simulationPluginPage = getPageComposite(parent);
		} else {
			simulationPluginPage.setParent(parent);
		}
	}

}
