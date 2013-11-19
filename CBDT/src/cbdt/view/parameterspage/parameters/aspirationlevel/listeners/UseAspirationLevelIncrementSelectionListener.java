package cbdt.view.parameterspage.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.parameters.ParametersController;

public class UseAspirationLevelIncrementSelectionListener implements
		SelectionListener {

	private ParametersController parametersController;
	private Button useAspirationLevelIncrementButton;

	public UseAspirationLevelIncrementSelectionListener(
			ParametersController parametersController,
			Button useAspirationLevelIncrementButton) {
				this.parametersController = parametersController;
				this.useAspirationLevelIncrementButton = useAspirationLevelIncrementButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		parametersController.setUsingAspirationLevelIncrement(useAspirationLevelIncrementButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}
}
