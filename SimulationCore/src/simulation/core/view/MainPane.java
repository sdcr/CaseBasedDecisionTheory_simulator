package simulation.core.view;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Widget;

public class MainPane extends Composite{
	
	private Widget shownWidget;

	public MainPane(Composite parent, int style) {
		super(parent, style);
		
		//initialize
		Color mainPaneColor = new Color(parent.getDisplay(), 200, 200, 200);
		setBackground(mainPaneColor);
		GridData mainPaneGridData = new GridData();
		mainPaneGridData.verticalAlignment = GridData.FILL;
		mainPaneGridData.horizontalAlignment = GridData.FILL;
		mainPaneGridData.grabExcessHorizontalSpace = true;
		setLayoutData(mainPaneGridData);
	}
}
