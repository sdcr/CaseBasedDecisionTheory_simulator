package cbdt.view.parameters;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;
import cbdt.controller.Controller;
import cbdt.view.HeaderComposite;

public class ParameterPaneContent implements ISimulationPluginPaneContent{

	private ISimulationPlugin plugin;
	private ParameterComposite parameterComposite;
	private Controller controller;
	
	public ParameterPaneContent(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getName() {
		return "Parameter-Eingabe";
	}

	@Override
	public Composite getComposite(Composite parent) {
		Composite cbdtFrameComposite = new HeaderComposite(parent, SWT.NONE| SWT.BORDER);		
		parameterComposite = new ParameterComposite(cbdtFrameComposite, SWT.NONE, controller);
		
		parameterComposite.initialize(controller.getParametersModel());
		
		return cbdtFrameComposite;
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
