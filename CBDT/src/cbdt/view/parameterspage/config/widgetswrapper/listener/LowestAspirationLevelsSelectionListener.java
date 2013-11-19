package cbdt.view.parameterspage.config.widgetswrapper.listener;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.config.common.CommonConfigController;

public class LowestAspirationLevelsSelectionListener implements
		SelectionListener {

	private CommonConfigController commonConfigController;
	private Button lowestAspirationLevelsButton;

	public LowestAspirationLevelsSelectionListener(
			CommonConfigController commonConfigController,
			Button lowestAspirationLevelsButton) {
				this.commonConfigController = commonConfigController;
				this.lowestAspirationLevelsButton = lowestAspirationLevelsButton;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
		commonConfigController.setCalcLowestAspirationLevels(lowestAspirationLevelsButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
		// TODO Auto-generated method stub
	}

}
