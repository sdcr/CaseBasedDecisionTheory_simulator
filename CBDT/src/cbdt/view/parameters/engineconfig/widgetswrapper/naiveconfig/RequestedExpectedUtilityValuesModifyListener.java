package cbdt.view.parameters.engineconfig.widgetswrapper.naiveconfig;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import cbdt.control.engineconfig.NaiveConfigController;
import cbdt.view.HintLabelWrapper;
import cbdt.view.NumberFormatChecker;

public class RequestedExpectedUtilityValuesModifyListener implements
		ModifyListener {

	private NaiveConfigController controller;
	private HintLabelWrapper hintLabel;
	private NumberFormatChecker numberFormatChecker;

	public RequestedExpectedUtilityValuesModifyListener(
			NaiveConfigController configController, HintLabelWrapper hintLabel,
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
