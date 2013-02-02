package simulation.extension.simulationplugin.cbdt;

import java.util.Map;

import org.eclipse.swt.widgets.Widget;
import simulation.extensionpoint.simulationplugin.ISimulationPlugin;

public class CBDTplugin implements ISimulationPlugin {

	public CBDTplugin() {
		System.out.println("CBDTplugin created");
	}

	@Override
	public String getName() {
		return "CBDT";
	}

	@Override
	public Map<String, Widget> getMainPaneContent() {
		// TODO Auto-generated method stub
		return null;
	}

}
