package cbdt.view.parameters.aspirationlevel.listeners;

import org.eclipse.swt.events.ModifyListener;

import cbdt.control.Controller;
import cbdt.view.HintLabelWrapper;

public abstract class AbstractParameterModifyListener implements ModifyListener{

	protected Controller controller;
	protected HintLabelWrapper hintLabel;

	public AbstractParameterModifyListener(Controller controller, HintLabelWrapper hintLabel){
		this.controller = controller;
		this.hintLabel = hintLabel;
	}
}
