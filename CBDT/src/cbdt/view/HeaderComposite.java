package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class HeaderComposite extends Composite{

	private final FontData CBDT_TITLE_FONT_DATA = new FontData("Arial", 14, SWT.NORMAL);
	
	public HeaderComposite(Composite parent, int style) {
		super(parent, style);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		rowLayout.marginTop = 25;
		rowLayout.marginLeft = 50;
		this.setLayout(rowLayout);

		Label cbdtTitle = new Label(this, SWT.NONE);
		cbdtTitle.setText("Case-Based Decision Theory");
		cbdtTitle.setFont(new Font(parent.getDisplay(), CBDT_TITLE_FONT_DATA));
	}

	
}
