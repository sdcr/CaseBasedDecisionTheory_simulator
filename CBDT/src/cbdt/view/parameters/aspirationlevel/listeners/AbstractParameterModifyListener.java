package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyListener;

import cbdt.control.pages.ParametersPageController;
import cbdt.view.HintLabelWrapper;
import cbdt.view.NumberFormatChecker;

public abstract class AbstractParameterModifyListener implements ModifyListener {

	protected ParametersPageController controller;
	protected HintLabelWrapper hintLabel;
	protected NumberFormatChecker numberFormatChecker;

	public AbstractParameterModifyListener(ParametersPageController controller,
			HintLabelWrapper hintLabel, NumberFormatChecker numberFormatChecker) {
		this.controller = controller;
		this.hintLabel = hintLabel;
		this.numberFormatChecker = numberFormatChecker;
	}
}
