package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Text;

import cbdt.control.pages.ParametersController;
import cbdt.view.HintLabelWrapper;
import cbdt.view.NumberFormatChecker;

public class AspirationLevelIncrementModifyListener extends
		AbstractParameterModifyListener {

	public AspirationLevelIncrementModifyListener(
			ParametersController controller, HintLabelWrapper hintLabel,
			NumberFormatChecker numberFormatChecker) {
		super(controller, hintLabel, numberFormatChecker);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text = (Text) e.widget;
		String textValue = text.getText();
		if (numberFormatChecker.isValidValue(textValue)) {
			controller.setAspirationLevelIncrement(Double
					.parseDouble(textValue));
			hintLabel.setVisible(false);
		} else {
			hintLabel.setVisible(true);
		}
	}

}
