package cbdt.view.parameterspage.parameters.aspirationlevel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import cbdt.view.HintLabelWrapper;
import cbdt.view.parameterspage.parameters.SimpleParameterComposite;

/**
 * This class is a subclass of {@link HintLabelWrapper} and is used to display a
 * hint label in {@link SimpleParameterComposite}s.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SimpleParameterHintLabelWrapper extends HintLabelWrapper {

	/**
	 * The constructor.
	 * 
	 * @param parent
	 */
	public SimpleParameterHintLabelWrapper(Composite parent) {
		super(parent);
		this.setToolTipText("The value must be a decimal.");
		GridData hintLabelLayoutData = new GridData();
		hintLabelLayoutData.horizontalAlignment = SWT.END;
		hintLabel.setLayoutData(hintLabelLayoutData);
	}

}
