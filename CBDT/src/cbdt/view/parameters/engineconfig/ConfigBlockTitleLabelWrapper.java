package cbdt.view.parameters.engineconfig;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class ConfigBlockTitleLabelWrapper {

	public static final int CONFIG_BLOCK_V_SPACE = 14;
	
	public static final int CONFIG_BLOCK_H_INDENT = 15;
	
	public static final FontData CONFIG_BLOCK_TITLE_FONT_DATA = new FontData("Arial", 9,
			SWT.BOLD);

	private Label commonConfigLabel;
	
	public ConfigBlockTitleLabelWrapper(Composite parent) {
		commonConfigLabel = new Label(parent, SWT.NONE);
		getLabel().setFont(new Font(parent.getDisplay(), 
				CONFIG_BLOCK_TITLE_FONT_DATA));
		
		GridData titleGridData = new GridData();
		titleGridData.horizontalSpan = 2;
		titleGridData.verticalIndent = CONFIG_BLOCK_V_SPACE;
		titleGridData.horizontalIndent = CONFIG_BLOCK_H_INDENT;
		getLabel().setLayoutData(titleGridData);
	}

	public Label getLabel() {
		return commonConfigLabel;
	}

}
