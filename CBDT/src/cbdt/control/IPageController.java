package cbdt.control;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;

public interface IPageController {

	/**
	 * @return The ISimulationPluginPageFactory, i.e. the view, for which this 
	 * class is the controller.
	 */
	public AbstractPluginPageCompositeWrapper getPageWrapper();
}
