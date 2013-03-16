package cbdt.view.parameters;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import cbdt.control.pages.ParametersPageController;

public class StartComputationSelectionListener implements SelectionListener {

	private ParametersPageController controller;
	
	public StartComputationSelectionListener(ParametersPageController controller) {
		this.controller = controller;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		controller.startComputation();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
