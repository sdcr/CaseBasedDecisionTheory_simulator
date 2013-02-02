package simulation.extension.simulationplugin.cbdt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

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

	
	public Map<String, Widget> getMainPaneContent(Composite parent) {
		Map<String, Widget> retVal = new HashMap<String, Widget>();
		
		retVal.put("test1", new Button(parent, SWT.PUSH));
		return retVal;
	}

	@Override
	public List<ISimulationPluginPaneContent> getPaneContents() {
		// TODO Auto-generated method stub
		return null;
	}

}
