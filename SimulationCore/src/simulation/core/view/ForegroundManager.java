package simulation.core.view;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;

/**
 * Manages which pluginPage Composite is shown on the application in foreground. 
 * @author S-lenovo
 */
public class ForegroundManager {

	private Map<ISimulationPluginPageFactory, Composite> pluginPagesPreviouslyInForeground;
	private Composite currentlyInForeground;
	private Composite currentlyInBackgroundsParent;
	private Composite pluginPane;

	public ForegroundManager(Composite foregroundParent) {
		pluginPane = foregroundParent;
		pluginPagesPreviouslyInForeground = new HashMap<ISimulationPluginPageFactory, Composite>();
		currentlyInBackgroundsParent = new Composite(new Shell(), SWT.NONE);
	}

	/**
	 * Sets the composite which the pageWrapper wraps to the foreground. It the composite
	 * has already been initialized, the existing composite is put to foreground.
	 * It it does not yet exist, the composite is initialized with the pluginPane as parent.
	 * @param pageWrapper The wrapper of the Composite which will be put in foreground.
	 */
	public void setToForeground(ISimulationPluginPageFactory pageWrapper) {
		if (pluginPagesPreviouslyInForeground.keySet().contains(pageWrapper)) {
			setParentToBackground(currentlyInForeground);
			currentlyInForeground = pluginPagesPreviouslyInForeground.get(pageWrapper);
			setParentToPluginPane(currentlyInForeground);
		} else {			
			setParentToBackground(currentlyInForeground);
			currentlyInForeground = pageWrapper.getPageComposite(pluginPane);
			if (currentlyInForeground != null) {
				pluginPagesPreviouslyInForeground.put(pageWrapper, currentlyInForeground);
			}
		}
		pluginPane.layout();
	}
	
	private void setParentToBackground(Composite pluginPage){
		if(pluginPage!=null){
			pluginPage.setVisible(false);
			pluginPage.setParent(currentlyInBackgroundsParent);	
		}
	}
	
	private void setParentToPluginPane(Composite pluginPage){
		if(pluginPage!=null){
			pluginPage.setVisible(true);
			pluginPage.setParent(pluginPane);
		}
	}
	
}
