package cbdt.control;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;

public interface IPageController {

	/**
	 * @return The ISimulationPluginPageFactory, i.e. the view, for which this 
	 * class is the controller.
	 */
	public ISimulationPluginPageFactory getPageFactory();
}
