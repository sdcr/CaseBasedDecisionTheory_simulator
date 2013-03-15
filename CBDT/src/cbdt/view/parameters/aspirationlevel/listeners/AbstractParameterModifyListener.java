package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyListener;

import cbdt.control.ParametersController;
import cbdt.view.HintLabelWrapper;
import cbdt.view.NumberFormatChecker;

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
