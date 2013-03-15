package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A label which is supposed to indicate to the user that there is a problem
 * with an entered parameter. This class is an opaque wrapper, in the sense that
 * the wrapped object is not accessible.
 * 
 * @author S-lenovo
 */
public class HintLabelWrapper {

	protected Label hintLabel;

	public HintLabelWrapper(Composite parent) {
		hintLabel = new Label(parent, SWT.NONE);

		hintLabel.setText("*");

		FontData hintFontData = new FontData("Arial", 13, SWT.BOLD);
		hintLabel.setFont(new Font(hintLabel.getDisplay(), hintFontData));

		Color fontColor = new Color(hintLabel.getDisplay(), 255, 150, 150);
		hintLabel.setForeground(fontColor);
		hintLabel.setVisible(false);
	}

	/**
	 * See Label's documentation for
	 * {@link #org.eclipse.swt.widgets.Label.setVisible(boolean) setVisible()}.
	 */
	public void setVisible(boolean visible) {
		hintLabel.setVisible(visible);
	}

	/**
	 * See Label's documentation for
	 * {@link #org.eclipse.swt.widgets.Label.setToolTipText(boolean)
	 * setToolTipText()}.
	 * 
	 * @param string
	 */
	public void setToolTipText(String string) {
		hintLabel.setToolTipText(string);
	}
}
