package cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.view.HintLabelWrapper;
import cbdt.view.NumberFormatChecker;

public class RequestedExpectedUtilityValuesModifyListener implements
		ModifyListener {

	private AbstractEngineConfigController controller;
	private HintLabelWrapper hintLabel;
	private NumberFormatChecker numberFormatChecker;

	public RequestedExpectedUtilityValuesModifyListener(
			AbstractEngineConfigController configController, HintLabelWrapper hintLabel,
			NumberFormatChecker formatChecker) {
		this.controller = configController;
		this.hintLabel = hintLabel;
		this.numberFormatChecker = formatChecker;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text = (Text) e.widget;
		String textValue = text.getText();
		if (numberFormatChecker.isValidValue(textValue)) {
			controller.setRequestedNumberOfExpectedUtilities(Integer
					.parseInt(textValue));
			hintLabel.setVisible(false);
		} else {
			hintLabel.setVisible(true);
		}
	}

}
