package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.widgets.Text;

import cbdt.control.Controller;
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameters.aspirationlevel.NumberFormatChecker;

public class InitialAspirationLevelModifyListener extends AbstractParameterModifyListener {

	public InitialAspirationLevelModifyListener(Controller controller, HintLabelWrapper hintLabel) {
		super(controller, hintLabel);
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text =  (Text)e.widget;
		String textValue = text.getText();
		if(NumberFormatChecker.hasValidDoubleFormat(textValue)){
			controller.setInitialAspirationLevel(Double.parseDouble(textValue));
			hintLabel.setVisible(false);
		}else{
			hintLabel.setVisible(true);
		}
	}

}
