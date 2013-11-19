package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import cbdt.control.parameters.config.common.CommonConfigController;
import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

public class RequestedExpectedUtilityValuesModifyListener implements
		ModifyListener {

	private HintLabelWrapper hintLabel;
	private NumberFormatChecker numberFormatChecker;
	private CommonConfigController commonConfigController;

	public RequestedExpectedUtilityValuesModifyListener(
			CommonConfigController commonConfigController,
			HintLabelWrapper hintLabel, NumberFormatChecker formatChecker) {
		this.commonConfigController = commonConfigController;
		this.hintLabel = hintLabel;
		this.numberFormatChecker = formatChecker;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text = (Text) e.widget;
		String textValue = text.getText();
		if (numberFormatChecker.isValidValue(textValue)) {
			commonConfigController.setRequestedNumberOfExpectedUtilities(Integer
					.parseInt(textValue));
			hintLabel.setVisible(false);
		} else {
			hintLabel.setVisible(true);
		}
	}

}
