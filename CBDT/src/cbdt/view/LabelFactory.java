package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This factory class is able to create {@link Label} objects with consistent
 * font and size properties.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class LabelFactory {

	/**
	 * Create and return a {@link Label} object with a specific title.
	 * 
	 * @param parent
	 * @param title
	 * @return
	 */
	public Label createTitleLabel(Composite parent, String title) {
		Label titleLabel = createTitleLabel(parent);
		titleLabel.setText(title);
		return titleLabel;
	}

	/**
	 * Create and return a {@link Label} object with no title specified.
	 * 
	 * @param parent
	 * @return
	 */
	public Label createTitleLabel(Composite parent) {
		Label titleLabel = new Label(parent, SWT.NONE);
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		titleLabel.setFont(new Font(parent.getDisplay(), labelFontData));
		return titleLabel;
	}

}
