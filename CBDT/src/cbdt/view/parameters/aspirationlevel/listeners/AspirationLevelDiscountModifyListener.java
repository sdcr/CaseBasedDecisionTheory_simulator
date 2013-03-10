package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Text;

import cbdt.control.Controller;
import cbdt.view.parameters.HintLabelWrapper;

public class AspirationLevelDiscountModifyListener extends AbstractParameterModifyListener{

	public AspirationLevelDiscountModifyListener(Controller controller, HintLabelWrapper hintLabel) {
		super(controller, hintLabel);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text =  (Text)e.widget;
		try {
			double parseDouble = Double.parseDouble(text.getText());
			controller.setAspirationDiscountFactor(parseDouble);
			hintLabel.setVisible(false);
		} catch (NumberFormatException e1) {
			hintLabel.setVisible(true);
		}
	}

}
