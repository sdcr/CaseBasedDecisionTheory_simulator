package simulation.extension.simulationplugin.cbdt;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class AnalysisPaneContent implements ISimulationPluginPaneContent{

	private ISimulationPlugin plugin;
	
	@Override
	public String getName() {
		return "Ergebnis-Analyse";
	}

	@Override
	public Composite getComposite(Composite parent) {
		Composite c = new Composite(parent, SWT.NONE);
		c.setBackground(new Color(parent.getDisplay(),255,255,0));
		c.setLayout(new RowLayout());
		Label l = new Label(c, SWT.NONE);
		l.setText("Analyse");
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
