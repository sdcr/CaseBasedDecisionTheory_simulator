package cbdt.plugin;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class ParameterPaneContent implements ISimulationPluginPaneContent{

	private ISimulationPlugin plugin;
	
	@Override
	public String getName() {
		return "Parameter-Eingabe";
	}

	@Override
	public Composite getComposite(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setBackground(new Color(parent.getDisplay(),255,0,0));
		c.setLayout(new RowLayout());
		Label l = new Label(c, SWT.NONE);
		l.setText("Parameter");
		return c;
	}

	@Override
	public ISimulationPlugin getSimulationPlugin() {
		return plugin;
	}

	@Override
	public void setSimulationPlugin(ISimulationPlugin plugin) {
		this.plugin = plugin;
	}

}
