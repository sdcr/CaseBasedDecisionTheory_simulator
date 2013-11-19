package cbdt.view.parameterspage.config;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import cbdt.view.parameterspage.config.widgetswrapper.AbstractEngineConfigWidgetsWrapper;

public class ConfigForegroundManager {

	private Composite currentlyInBackgroundsParent;
	private Composite parametersPage;
	private AbstractEngineConfigWidgetsWrapper currentlyInForegroundPage;
	private Label algoSpecificTitleLabel;

	public ConfigForegroundManager(Composite foregroundParent, Label algoSpecificTitleLabel) {
		parametersPage = foregroundParent;
		this.algoSpecificTitleLabel = algoSpecificTitleLabel;
		currentlyInBackgroundsParent = new Composite(new Shell(), SWT.NONE);
	}

	public void setToForeground(
			AbstractEngineConfigWidgetsWrapper configWidgetsWrapper) {
		if (currentlyInForegroundPage != null){
			currentlyInForegroundPage.setParent(currentlyInBackgroundsParent);
		}
		configWidgetsWrapper.setParent(parametersPage);
		currentlyInForegroundPage = configWidgetsWrapper;
		
		algoSpecificTitleLabel.setEnabled(configWidgetsWrapper.hasContentToShow());

		parametersPage.getParent().getParent().getParent().pack();
	}

	public void setToBackground(AbstractEngineConfigWidgetsWrapper configComposite) {
		configComposite.setParent(currentlyInBackgroundsParent);
	}

}
