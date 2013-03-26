package simulation.core.view.menu;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import simulation.core.control.Controller;

public class AppInfoSelectionListener implements SelectionListener {

	private Controller controller;

	public AppInfoSelectionListener(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		controller.showApplicationInformation();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
