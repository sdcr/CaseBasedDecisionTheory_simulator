package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

public class CBDTHeaderComposite extends Composite {

	private final FontData CBDT_TITLE_FONT_DATA = new FontData("Arial", 14,
			SWT.NORMAL);

	public CBDTHeaderComposite(Composite parent, int style) {
		super(parent, style | SWT.BORDER);

		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.wrap = false;
		rowLayout.marginTop = 25;
		rowLayout.marginLeft = 30;
		// rowLayout.marginBottom = 20;
		this.setLayout(rowLayout);

		Label cbdtTitle = new Label(this, SWT.NONE);
		cbdtTitle.setText("Case-Based Decision Theory");
		cbdtTitle.setFont(new Font(parent.getDisplay(), CBDT_TITLE_FONT_DATA));

		RowData rowData = new RowData();
		rowData.height = 35;
		cbdtTitle.setLayoutData(rowData);
	}

}
