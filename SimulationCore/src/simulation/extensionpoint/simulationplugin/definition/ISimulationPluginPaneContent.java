package simulation.extensionpoint.simulationplugin.definition;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

public interface ISimulationPluginPaneContent {

	public String getName();
	
	public Widget getWidget(Composite parent);
	
	public ISimulationPlugin getSimulationPlugin();
	
	public void setSimulationPlugin(ISimulationPlugin plugin);
	
}
