package cbdt;

import java.util.List;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;
import cbdt.controller.Controller;

public class CBDTplugin implements ISimulationPlugin {

	public CBDTplugin() {
		System.out.println("CBDTplugin created");
	}

	@Override
	public String getName() {
		return "CBDT";
	}

	@Override
	public List<ISimulationPluginPaneContent> getPaneContents() {
		Controller controller = new Controller();
		return controller.getPaneContents();
	}

}
