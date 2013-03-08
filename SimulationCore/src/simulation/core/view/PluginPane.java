package simulation.core.view;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

/**
 * This class fills the part of the main shell where the view 
 * content of the ISimulationPlugins will be displayed.
 * @author S-lenovo
 */
public class PluginPane extends Composite {

	public PluginPane(Composite parent, int style) {
		super(parent, style);

		// initialize
		Color mainPaneColor = new Color(parent.getDisplay(), 200, 200, 200);
		this.setBackground(mainPaneColor);

		GridData mainPaneGridData = new GridData();
		mainPaneGridData.verticalAlignment = GridData.FILL;
		mainPaneGridData.horizontalAlignment = GridData.FILL;
		mainPaneGridData.grabExcessHorizontalSpace = true;
		this.setLayoutData(mainPaneGridData);
		
		this.setLayout(new FillLayout());
	}

}
