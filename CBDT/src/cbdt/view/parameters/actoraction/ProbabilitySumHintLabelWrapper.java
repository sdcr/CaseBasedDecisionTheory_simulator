package cbdt.view.parameters.actoraction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import cbdt.view.parameters.HintLabelWrapper;

public class ProbabilitySumHintLabelWrapper extends HintLabelWrapper{

	public ProbabilitySumHintLabelWrapper(Composite parent) {
		super(parent);
		
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		hintLabel.setLayoutData(gridData);
		
		hintLabel.setToolTipText("The sum of the probabilities should to be one.");
	}

}
