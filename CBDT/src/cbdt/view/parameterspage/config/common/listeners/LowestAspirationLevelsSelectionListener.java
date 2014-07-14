package cbdt.view.parameterspage.config.common.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.config.common.CommonConfigController;

/**
 * This class is an implementation of the {@link SelectionListener} and is used
 * to set whether the lowest aspiration level for every tree depth should be
 * stored.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class LowestAspirationLevelsSelectionListener implements
		SelectionListener {

	private CommonConfigController commonConfigController;
	private Button lowestAspirationLevelsButton;

	/**
	 * The constructor.
	 * 
	 * @param commonConfigController
	 * @param lowestAspirationLevelsButton
	 */
	public LowestAspirationLevelsSelectionListener(
			CommonConfigController commonConfigController,
			Button lowestAspirationLevelsButton) {
		this.commonConfigController = commonConfigController;
		this.lowestAspirationLevelsButton = lowestAspirationLevelsButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		commonConfigController
				.setCalcLowestAspirationLevels(lowestAspirationLevelsButton
						.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
