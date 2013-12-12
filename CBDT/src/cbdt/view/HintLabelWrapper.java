package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This class wraps a {@link Label} object. It is supposed to indicate to the
 * user that there is a problem with an entered parameter. This class is an
 * opaque wrapper, in the sense that the wrapped object is not accessible.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class HintLabelWrapper {

	/**
	 * The wrapped {@link Label} object.
	 */
	protected Label hintLabel;

	/**
	 * The constructor. Sets the wrapped {@link Label} to a red asterisk.
	 * 
	 * @param parent
	 */
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
	 * See {@link Label}'s documentation for
	 * {@link #org.eclipse.swt.widgets.Label.setVisible(boolean) setVisible()}.
	 */
	public void setVisible(boolean visible) {
		hintLabel.setVisible(visible);
	}

	/**
	 * See {@link Label}'s documentation for
	 * {@link #org.eclipse.swt.widgets.Label.setToolTipText(boolean)
	 * setToolTipText()}.
	 * 
	 * @param string
	 */
	public void setToolTipText(String string) {
		hintLabel.setToolTipText(string);
	}
}
