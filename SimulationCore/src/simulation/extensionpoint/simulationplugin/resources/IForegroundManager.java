package simulation.extensionpoint.simulationplugin.resources;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;

public interface IForegroundManager {

	/**
	 * Sets the composite which the pageWrapper wraps to the foreground. It the composite
	 * has already been initialized, the existing composite is put to foreground.
	 * It it does not yet exist, the composite is initialized with the pluginPane as parent.
	 * @param pageWrapper The wrapper of the Composite which will be put in foreground.
	 */
	public void setToForeground(AbstractPluginPageCompositeWrapper pageFactory);
}
