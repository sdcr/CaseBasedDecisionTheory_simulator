package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Text;

import cbdt.control.Controller;
import cbdt.view.parameters.HintLabelWrapper;
import cbdt.view.parameters.aspirationlevel.NumberFormatChecker;

public class AspirationLevelDiscountModifyListener extends AbstractParameterModifyListener{

	public AspirationLevelDiscountModifyListener(Controller controller, HintLabelWrapper hintLabel) {
		super(controller, hintLabel);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text =  (Text)e.widget;
		String textValue = text.getText();
		if(NumberFormatChecker.hasValidDoubleFormat(textValue)){
			controller.setAspirationDiscountFactor(Double.parseDouble(textValue));
			hintLabel.setVisible(false);
		}else{
			hintLabel.setVisible(true);
		}
	}

}
