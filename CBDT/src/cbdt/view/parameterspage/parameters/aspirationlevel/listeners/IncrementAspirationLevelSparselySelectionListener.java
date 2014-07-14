package cbdt.view.parameterspage.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.parameters.ParametersController;

/**
 * This class implements the {@link SelectionListener} interface and is used to
 * set whether the aspiration level is to be incremented sparsely.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class IncrementAspirationLevelSparselySelectionListener implements
		SelectionListener {

	private ParametersController parametersController;
	private Button useAspirationLevelIncrementButton;

	/**
	 * The constructor.
	 * 
	 * @param parametersController
	 * @param useAspirationLevelIncrementButton
	 */
	public IncrementAspirationLevelSparselySelectionListener(
			ParametersController parametersController,
			Button useAspirationLevelIncrementButton) {
		this.parametersController = parametersController;
		this.useAspirationLevelIncrementButton = useAspirationLevelIncrementButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		parametersController
				.setIncrementAspirationLevelSparsely(useAspirationLevelIncrementButton
						.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}
}
