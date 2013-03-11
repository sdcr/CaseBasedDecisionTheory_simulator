package simulation.extensionpoint.simulationplugin.resources;

import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;

public interface IForegroundManager {

	/**
	 * Sets the composite which the pageWrapper wraps to the foreground. It the composite
	 * has already been initialized, the existing composite is put to foreground.
	 * It it does not yet exist, the composite is initialized with the pluginPane as parent.
	 * @param pageWrapper The wrapper of the Composite which will be put in foreground.
	 */
	public void setToForeground(AbstractPluginPageWrapper pageFactory);
	
	public Shell getShell();
}
