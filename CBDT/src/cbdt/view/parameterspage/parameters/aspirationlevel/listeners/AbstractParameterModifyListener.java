package cbdt.view.parameterspage.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyListener;

import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

/**
 * This abstract class implements the {@link ModifyListener}. Its subclasses are
 * used as listeners for changes of parameters.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public abstract class AbstractParameterModifyListener implements ModifyListener {

	protected ParametersController controller;
	protected HintLabelWrapper hintLabel;
	protected NumberFormatChecker numberFormatChecker;

	/**
	 * The constructor.
	 * 
	 * @param controller
	 * @param hintLabel
	 * @param numberFormatChecker
	 */
	public AbstractParameterModifyListener(ParametersController controller,
			HintLabelWrapper hintLabel, NumberFormatChecker numberFormatChecker) {
		this.controller = controller;
		this.hintLabel = hintLabel;
		this.numberFormatChecker = numberFormatChecker;
	}
}
