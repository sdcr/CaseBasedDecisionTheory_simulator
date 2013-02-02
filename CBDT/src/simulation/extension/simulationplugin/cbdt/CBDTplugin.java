package simulation.extension.simulationplugin.cbdt;

import java.util.ArrayList;
import java.util.List;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

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
		List<ISimulationPluginPaneContent> retVal = new ArrayList<ISimulationPluginPaneContent>();
		retVal.add(new ParameterPaneContent());
		return retVal;
	}

}
