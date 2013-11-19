package cbdt.view.parameterspage.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyListener;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

public abstract class AbstractParameterModifyListener implements ModifyListener {

	protected ParametersController controller;
	protected HintLabelWrapper hintLabel;
	protected NumberFormatChecker numberFormatChecker;

	public AbstractParameterModifyListener(ParametersController controller,
			HintLabelWrapper hintLabel, NumberFormatChecker numberFormatChecker) {
		this.controller = controller;
		this.hintLabel = hintLabel;
		this.numberFormatChecker = numberFormatChecker;
	}
}
