package cbdt.view.parameters.engineconfig.widgetswrapper.dfsmatrixhp;

import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.widgets.Text;

import cbdt.control.pages.engineconfig.DFSmatrixHighPrecisionConfigController;
import cbdt.view.HintLabelWrapper;
import cbdt.view.IntegerFormatChecker;

public class RequiredDecimalPlacesModifyListener implements ModifyListener {

	private DFSmatrixHighPrecisionConfigController configController;
	private HintLabelWrapper hintLabel;
	private IntegerFormatChecker integerFormatChecker;

	public RequiredDecimalPlacesModifyListener(
			DFSmatrixHighPrecisionConfigController configController,
			HintLabelWrapper hintLabel,
			IntegerFormatChecker integerFormatChecker) {
				this.configController = configController;
				this.hintLabel = hintLabel;
				this.integerFormatChecker = integerFormatChecker;
	}

	@Override
	public void modifyText(ModifyEvent e) {
		Text text = (Text) e.widget;
		String textValue = text.getText();
		if (integerFormatChecker.isValidValue(textValue)) {
			configController.setRequiredDecimalPlaces(Integer
					.parseInt(textValue));
			hintLabel.setVisible(false);
		} else {
			hintLabel.setVisible(true);
		}
	}

}
