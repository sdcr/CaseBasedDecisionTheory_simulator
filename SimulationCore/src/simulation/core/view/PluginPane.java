package simulation.core.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class PluginPane extends Composite {

	Map<ISimulationPluginPaneContent, Composite> previouslyInForeground;
	Composite currentlyInForeground;

	public PluginPane(Composite parent, int style) {
		super(parent, style);

		// initialize
		previouslyInForeground = new HashMap<ISimulationPluginPaneContent, Composite>();

		Color mainPaneColor = new Color(parent.getDisplay(), 200, 200, 200);
		setBackground(mainPaneColor);
		GridData mainPaneGridData = new GridData();
		mainPaneGridData.verticalAlignment = GridData.FILL;
		mainPaneGridData.horizontalAlignment = GridData.FILL;
		mainPaneGridData.grabExcessHorizontalSpace = true;
		setLayoutData(mainPaneGridData);
		
		setLayout(new FillLayout());
	}

	public void installPaneContents(List<ISimulationPlugin> plugins) {
		// TODO Auto-generated method stub

	}

	public void setToForeGround(ISimulationPluginPaneContent toForeground) {
		System.out.println("set to foreground");
		if (previouslyInForeground.keySet().contains(toForeground)) {
			if (currentlyInForeground != null)
				currentlyInForeground.setVisible(false);
			currentlyInForeground = previouslyInForeground.get(toForeground);
			if (currentlyInForeground != null)
				currentlyInForeground.setVisible(true);
		} else {			
			if (currentlyInForeground != null)
				currentlyInForeground.setVisible(false);
			currentlyInForeground = toForeground.getComposite(this);
			if (currentlyInForeground != null) {
				currentlyInForeground.setVisible(true);
				previouslyInForeground.put(toForeground, currentlyInForeground);
			}
		}
		this.layout();
	}
}
