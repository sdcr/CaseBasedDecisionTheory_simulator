package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class LabelFactory {

	public Label createTitleLabel(Composite parent, String title){
		Label titleLabel = new Label(parent, SWT.NONE);
		titleLabel.setText(title);
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		titleLabel.setFont(new Font(parent.getDisplay(), labelFontData));
		return titleLabel;
	}
}
