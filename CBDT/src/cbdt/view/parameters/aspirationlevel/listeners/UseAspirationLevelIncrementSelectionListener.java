package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.ParametersPageController;

public class UseAspirationLevelIncrementSelectionListener implements
		SelectionListener {

	private ParametersPageController pageController;
	private Button useAspirationLevelIncrementButton;

	public UseAspirationLevelIncrementSelectionListener(
			ParametersPageController pageController,
			Button useAspirationLevelIncrementButton) {
				this.pageController = pageController;
				this.useAspirationLevelIncrementButton = useAspirationLevelIncrementButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		pageController.setUsingAspirationLevelIncrement(useAspirationLevelIncrementButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
