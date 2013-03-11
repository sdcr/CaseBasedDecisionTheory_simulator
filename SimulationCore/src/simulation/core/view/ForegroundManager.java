package simulation.core.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;
import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;

/**
 * Manages which pluginPage Composite is shown on the application in foreground. 
 * @author S-lenovo
 */
public class ForegroundManager implements IForegroundManager{

	private Composite currentlyInBackgroundsParent;
	private Composite pluginPane;	
	private AbstractPluginPageCompositeWrapper currentlyInForegroundPage;

	public ForegroundManager(Composite foregroundParent) {
		pluginPane = foregroundParent;
		currentlyInBackgroundsParent = new Composite(new Shell(), SWT.NONE);
	}

	@Override
	public void setToForeground(AbstractPluginPageCompositeWrapper pageFactory) {
		if(currentlyInForegroundPage != null)
			currentlyInForegroundPage.setParent(currentlyInBackgroundsParent);
		pageFactory.setParent(pluginPane);
		currentlyInForegroundPage = pageFactory;
		pluginPane.layout();
	}

	@Override
	public Shell getShell() {
		return pluginPane.getShell();
	}
	
}
