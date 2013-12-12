package simulation.core.view.menu;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import simulation.core.control.Controller;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

/**
 * Initiates the controller to remove an ISimulationPlugin and the bundle
 * defining it, when the widget listened to is selected.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class RemovePluginSelectionListener implements SelectionListener {

	private Controller controller;
	private ISimulationPlugin toRemove;

	public RemovePluginSelectionListener(Controller controller,
			ISimulationPlugin toRemove) {
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
