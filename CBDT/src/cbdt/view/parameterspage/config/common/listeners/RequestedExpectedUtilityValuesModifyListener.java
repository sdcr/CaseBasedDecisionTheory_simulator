package cbdt.view.parameterspage.config.common.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

/**
 * This class is an implementation of the {@link ModifyListener} and is used to
 * set the number of expected utility values should be be computed during the
 * simulation.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class RequestedExpectedUtilityValuesModifyListener implements
		ModifyListener {

	private HintLabelWrapper hintLabel;
	private NumberFormatChecker numberFormatChecker;
	private CommonConfigController commonConfigController;

	/**
	 * The constructor.
	 * 
	 * @param commonConfigController
	 * @param hintLabel
	 * @param formatChecker
	 */
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
			commonConfigController
					.setRequestedNumberOfExpectedUtilities(Integer
							.parseInt(textValue));
			hintLabel.setVisible(false);
		} else {
			hintLabel.setVisible(true);
		}
	}

}
