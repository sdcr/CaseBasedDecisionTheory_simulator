package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyListener;

import cbdt.control.ParametersController;
import cbdt.view.HintLabelWrapper;

public abstract class AbstractParameterModifyListener implements ModifyListener{

	protected ParametersController controller;
	protected HintLabelWrapper hintLabel;

	public AbstractParameterModifyListener(ParametersController controller, HintLabelWrapper hintLabel){
		this.controller = controller;
		this.hintLabel = hintLabel;
	}
}
