package simulation.extensionpoint.simulationplugin.definition;

import org.eclipse.swt.widgets.Composite;

public interface ISimulationPluginPaneContent {

	public String getName();
	
	public Composite getComposite(Composite parent);
	
	public ISimulationPlugin getSimulationPlugin();
	
	public void setSimulationPlugin(ISimulationPlugin plugin);
	
}
