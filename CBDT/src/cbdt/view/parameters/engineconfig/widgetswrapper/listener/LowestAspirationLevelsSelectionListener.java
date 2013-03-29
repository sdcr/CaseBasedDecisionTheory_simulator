package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.ParametersPageController;

public class LowestAspirationLevelsSelectionListener implements
		SelectionListener {

	private ParametersPageController pageController;
	private Button lowestAspirationLevelsButton;

	public LowestAspirationLevelsSelectionListener(ParametersPageController pageController,
			Button lowestAspirationLevelsButton) {
				this.pageController = pageController;
				this.lowestAspirationLevelsButton = lowestAspirationLevelsButton;
	}
	
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		pageController.setCalcLowestAspirationLevels(lowestAspirationLevelsButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub

	}

}
