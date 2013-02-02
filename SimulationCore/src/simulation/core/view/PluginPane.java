package simulation.core.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class PluginPane extends Composite {

	Map<ISimulationPluginPaneContent, Composite> previouslyInForeground;
	Composite currentlyInForeground;
	Composite backgroundParent;

	public PluginPane(Composite parent, int style) {
		super(parent, style);

		// initialize
		previouslyInForeground = new HashMap<ISimulationPluginPaneContent, Composite>();
		backgroundParent = new Composite(new Shell(), SWT.NONE);

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
			beforeForegroundChangeHelper();
			currentlyInForeground = previouslyInForeground.get(toForeground);
			afterForegroundChangeHelper();
		} else {			
			beforeForegroundChangeHelper();
			currentlyInForeground = toForeground.getComposite(this);
			afterForegroundChangeHelper();
			if (currentlyInForeground != null) {
				previouslyInForeground.put(toForeground, currentlyInForeground);
			}
		}
		this.layout();
	}
	
	private void beforeForegroundChangeHelper(){
		if (currentlyInForeground != null){
			currentlyInForeground.setVisible(false);
			currentlyInForeground.setParent(backgroundParent);
		}
	}
	
	private void afterForegroundChangeHelper(){
		if (currentlyInForeground != null){
			currentlyInForeground.setVisible(true);
			currentlyInForeground.setParent(this);
		}
	}
	
}
