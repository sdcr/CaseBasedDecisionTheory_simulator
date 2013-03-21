package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
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

		GridData gridData = new GridData();
		gridData.widthHint = 200;
		this.setLayoutData(gridData);

		this.setLayout(new RowLayout());

		text = new Text(this, SWT.SINGLE | SWT.BORDER);
		RowData textRowData = new RowData();
		textRowData.width = 150;
		text.setLayoutData(textRowData);
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
