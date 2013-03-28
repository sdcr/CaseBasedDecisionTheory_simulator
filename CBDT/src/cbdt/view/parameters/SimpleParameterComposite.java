package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;

import cbdt.control.validators.NumberFormatChecker;
import cbdt.view.HintLabelWrapper;

public class SimpleParameterComposite extends Composite {

	private Text text;
	private HintLabelWrapper hintLabel;

	private NumberFormatChecker numberFormatChecker;

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

	public Text getText() {
		return text;
	}

	public HintLabelWrapper getHintLabel() {
		return hintLabel;
	}

	public void setHintLabel(HintLabelWrapper hintLabel) {
		this.hintLabel = hintLabel;
	}

	public boolean hasValidValue() {
		return numberFormatChecker.isValidValue(text.getText());
	}

	public NumberFormatChecker getNumberFormatChecker() {
		return numberFormatChecker;
	}

	public void setNumberFormatChecker(NumberFormatChecker numberFormatChecker) {
		this.numberFormatChecker = numberFormatChecker;
	}
}
