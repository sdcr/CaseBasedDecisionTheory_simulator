package cbdt.view.parameterspage.parameters.aspirationlevel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import cbdt.view.HintLabelWrapper;

public class SimpleParameterHintLabelWrapper extends HintLabelWrapper {

	public SimpleParameterHintLabelWrapper(Composite parent) {
		super(parent);
		this.setToolTipText("The value must be a decimal.");
		GridData hintLabelLayoutData = new GridData();
		hintLabelLayoutData.horizontalAlignment = SWT.END;
		hintLabel.setLayoutData(hintLabelLayoutData);
	}

}
