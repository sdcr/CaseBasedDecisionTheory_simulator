package cbdt.view.parameters.engineconfig.widgetswrapper.listener;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import cbdt.control.pages.ParametersPageController;
import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

public class RequestedExpectedUtilityValuesModifyListener implements
		ModifyListener {

	private HintLabelWrapper hintLabel;
	private NumberFormatChecker numberFormatChecker;
	private ParametersPageController pageController;

	public RequestedExpectedUtilityValuesModifyListener(
			ParametersPageController pageController,
			HintLabelWrapper hintLabel, NumberFormatChecker formatChecker) {
		this.pageController = pageController;
		this.hintLabel = hintLabel;
		this.numberFormatChecker = formatChecker;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text = (Text) e.widget;
		String textValue = text.getText();
		if (numberFormatChecker.isValidValue(textValue)) {
			pageController.setRequestedNumberOfExpectedUtilities(Integer
					.parseInt(textValue));
			hintLabel.setVisible(false);
		} else {
			hintLabel.setVisible(true);
		}
	}

}
