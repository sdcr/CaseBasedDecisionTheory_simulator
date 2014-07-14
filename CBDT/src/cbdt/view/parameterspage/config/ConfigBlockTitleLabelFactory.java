package cbdt.view.parameterspage.config;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * This factory class is able to produce title {@link Label}s which are to be
 * shown as titles for blocks of configuration widgets.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ConfigBlockTitleLabelFactory {

	public static final int CONFIG_BLOCK_V_SPACE = 14;

	public static final int CONFIG_BLOCK_H_INDENT = 15;

	public static final FontData CONFIG_BLOCK_TITLE_FONT_DATA = new FontData(
			"Arial", 9, SWT.BOLD);

	/**
	 * Returns a {@link Label} object which is to be shown as a title for blocks
	 * of configuration widgets.
	 * 
	 * @param parent
	 * @return
	 */
	public Label addConfigBlockTitleLabel(Composite parent) {
		Label configTitleLabel = new Label(parent, SWT.NONE);
		configTitleLabel.setFont(new Font(parent.getDisplay(),
				CONFIG_BLOCK_TITLE_FONT_DATA));

		GridData titleGridData = new GridData();
		titleGridData.horizontalSpan = 2;
		titleGridData.verticalIndent = CONFIG_BLOCK_V_SPACE;
		titleGridData.horizontalIndent = CONFIG_BLOCK_H_INDENT;
		configTitleLabel.setLayoutData(titleGridData);

		return configTitleLabel;
	}

}
