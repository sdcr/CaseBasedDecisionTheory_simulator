package simulation.core.view.menu;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import simulation.core.control.Controller;

public class CloseAppSelectionListener implements SelectionListener {

	private Controller controller;

	public CloseAppSelectionListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		controller.stopApplication();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
