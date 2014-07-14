package cbdt.view.parameterspage.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

/**
 * A subclass of {@link Composite} which encompasses a {@link Text}, a
 * {@link HintLabelWrapper} and a {@link NumberFormatChecker}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SimpleParameterComposite extends Composite {

	private Text text;
	
	private HintLabelWrapper hintLabel;

	private NumberFormatChecker numberFormatChecker;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 */
	public SimpleParameterComposite(Composite parent) {
		super(parent, SWT.NONE);

		SimpleParameterGridDataFactory gridDataFactory = new SimpleParameterGridDataFactory();
		this.setLayoutData(gridDataFactory.getCompositesGridData());

		GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.horizontalSpacing = 0;
		this.setLayout(gridLayout);

		text = new Text(this, SWT.SINGLE | SWT.BORDER);
		text.setLayoutData(gridDataFactory.getTextGridData());
	}

	/**
	 * @return The {@link Text} object contained in this composite.
	 */
	public Text getText() {
		return text;
	}

	/**
	 * @return A {@link HintLabelWrapper} that can display a hint about the
	 *         parameter represented by this composite.
	 */
	public HintLabelWrapper getHintLabel() {
		return hintLabel;
	}

	/**
	 * @param hintLabel
	 *            A {@link HintLabelWrapper} that can display a hint about the
	 *            parameter represented by this composite.
	 */
	public void setHintLabel(HintLabelWrapper hintLabel) {
		this.hintLabel = hintLabel;
	}

	/**
	 * @return Returns whether the {@link Text} field of this
	 *         {@link SimpleParameterComposite} is valid, according to the
	 *         associated {@link NumberFormatChecker}.<br>
	 * <br>
	 *         The {@link NumberFormatChecker} of this object must be set before
	 *         calling this method.
	 */
	public boolean hasValidValue() {
		return numberFormatChecker.isValidValue(text.getText());
	}

	/**
	 * @return The {@link NumberFormatChecker} of this object.
	 */
	public NumberFormatChecker getNumberFormatChecker() {
		return numberFormatChecker;
	}

	/**
	 * @param numberFormatChecker The {@link NumberFormatChecker} of this object.
	 */
	public void setNumberFormatChecker(NumberFormatChecker numberFormatChecker) {
		this.numberFormatChecker = numberFormatChecker;
	}
}
