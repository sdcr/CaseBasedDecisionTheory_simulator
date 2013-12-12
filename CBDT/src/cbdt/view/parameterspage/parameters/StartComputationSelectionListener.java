package cbdt.view.parameterspage.parameters;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;

import cbdt.control.parameterspage.ParametersConfigPageController;

/**
 * An implementation of the {@link SelectionListener}, which is supposed to
 * start the computation.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class StartComputationSelectionListener implements SelectionListener {

	private ParametersConfigPageController controller;

	/**
	 * The constructor.
	 * 
	 * @param controller
	 */
	public StartComputationSelectionListener(
			ParametersConfigPageController controller) {
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
