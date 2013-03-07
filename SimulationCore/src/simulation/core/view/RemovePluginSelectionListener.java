package simulation.core.view;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import simulation.core.control.Controller;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class RemovePluginSelectionListener implements SelectionListener{

	private Controller controller;
	private ISimulationPlugin toRemove;
	

	public RemovePluginSelectionListener(Controller controller, ISimulationPlugin toRemove) {
		this.controller = controller;
		this.toRemove = toRemove;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		controller.removeISimulationPlugin(toRemove);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	
}
