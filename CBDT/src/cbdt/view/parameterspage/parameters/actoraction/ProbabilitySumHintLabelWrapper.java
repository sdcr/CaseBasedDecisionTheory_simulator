package cbdt.view.parameterspage.parameters.actoraction;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import cbdt.model.parameters.ActorActionOutcome;
import cbdt.view.HintLabelWrapper;

/**
 * This class extends {@link HintLabelWrapper} and is used to display to the
 * user that the sum of the probabilities of {@link ActorActionOutcome}s must be
 * equal to one.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ProbabilitySumHintLabelWrapper extends HintLabelWrapper {

	/**
	 * The constructor.
	 * 
	 * @param parent
	 */
	public ProbabilitySumHintLabelWrapper(Composite parent) {
		super(parent);

		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		hintLabel.setLayoutData(gridData);

		hintLabel
				.setToolTipText("The sum of the probabilities should to be one.");
	}

}
