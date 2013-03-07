package simulation.core.view;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageWrapper;

public class ForegroundManager {

	Map<ISimulationPluginPageWrapper, Composite> previouslyInForeground;
	Composite currentlyInForeground;
	private Composite currentlyInBackgroundsParent;
	
	private Composite pluginPage;

	public ForegroundManager(Composite parent, int style) {
		pluginPage = new Composite(parent, style);

		// initialize
		previouslyInForeground = new HashMap<ISimulationPluginPageWrapper, Composite>();
		currentlyInBackgroundsParent = new Composite(new Shell(), SWT.NONE);

		Color mainPaneColor = new Color(parent.getDisplay(), 200, 200, 200);
		pluginPage.setBackground(mainPaneColor);

		GridData mainPaneGridData = new GridData();
		mainPaneGridData.verticalAlignment = GridData.FILL;
		mainPaneGridData.horizontalAlignment = GridData.FILL;
		mainPaneGridData.grabExcessHorizontalSpace = true;
		pluginPage.setLayoutData(mainPaneGridData);
		
		pluginPage.setLayout(new FillLayout());
	}


	public void setToForeGround(ISimulationPluginPageWrapper toForeground) {
		if (previouslyInForeground.keySet().contains(toForeground)) {
			beforeForegroundChangeHelper();
			currentlyInForeground = previouslyInForeground.get(toForeground);
			afterForegroundChangeHelper();
		} else {			
			beforeForegroundChangeHelper();
			currentlyInForeground = toForeground.getPageComposite(pluginPage);
			afterForegroundChangeHelper();
			if (currentlyInForeground != null) {
				previouslyInForeground.put(toForeground, currentlyInForeground);
			}
		}
		pluginPage.layout();
	}
	
	private void beforeForegroundChangeHelper(){
		if (currentlyInForeground != null){
			currentlyInForeground.setVisible(false);
			currentlyInForeground.setParent(currentlyInBackgroundsParent);
		}
	}
	
	private void afterForegroundChangeHelper(){
		if (currentlyInForeground != null){
			currentlyInForeground.setVisible(true);
			currentlyInForeground.setParent(pluginPage);
		}
	}
	
}
