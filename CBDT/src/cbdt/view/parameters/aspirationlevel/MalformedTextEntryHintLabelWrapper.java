package cbdt.view.parameters.aspirationlevel;

import org.eclipse.swt.widgets.Composite;

import cbdt.view.parameters.HintLabelWrapper;

public class MalformedTextEntryHintLabelWrapper extends HintLabelWrapper {

	public MalformedTextEntryHintLabelWrapper(Composite parent) {
		super(parent);
		
		hintLabel.setToolTipText("The value must be a decimal.");
	}

}
