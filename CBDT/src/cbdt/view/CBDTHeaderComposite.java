package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

/**
 * A subclass of {@link Composite}, which defines a title {@link Label} for the
 * CBDT plugin.<br>
 * It is to be used as the parent of the {@link Composite}s defining the plugin
 * pages.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class CBDTHeaderComposite extends Composite {

	private final FontData CBDT_TITLE_FONT_DATA = new FontData("Arial", 14,
			SWT.NORMAL);

	/**
	 * The constructor. It defines a {@link RowLayout} for this
	 * {@link Composite} subclass and instantiates the title {@link Label} for
	 * Case-Based Decision Theory.
	 * 
	 * @param parent
	 * @param style
	 */
	public CBDTHeaderComposite(Composite parent, int style) {
		super(parent, style);

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
