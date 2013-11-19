package cbdt.view.parameterspage.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Text;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

public class InitialAspirationLevelModifyListener extends
		AbstractParameterModifyListener {

	public InitialAspirationLevelModifyListener(
			ParametersController controller, HintLabelWrapper hintLabel,
			NumberFormatChecker numberFormatChecker) {
		super(controller, hintLabel, numberFormatChecker);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text = (Text) e.widget;
		String textValue = text.getText();
		if (numberFormatChecker.isValidValue(textValue)) {
			controller.setInitialAspirationLevel(Double.parseDouble(textValue));
			hintLabel.setVisible(false);
		} else {
			hintLabel.setVisible(true);
		}
	}

}
