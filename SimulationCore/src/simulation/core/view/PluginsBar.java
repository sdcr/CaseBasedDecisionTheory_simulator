package simulation.core.view;

import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

public class PluginsBar extends ScrolledComposite{

	public PluginsBar(Composite parent, int style) {
		super(parent, style);
		
		//initialize
		Color simulationPluginsBackgroundColor = new Color(parent.getDisplay(), 0, 0, 255);
		setBackground(simulationPluginsBackgroundColor);
		setExpandVertical(true);
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		setLayoutData(pluginsBarGridData);
	}

}
