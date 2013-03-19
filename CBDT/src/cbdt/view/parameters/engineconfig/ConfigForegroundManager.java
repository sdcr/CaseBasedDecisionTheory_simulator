package cbdt.view.parameters.engineconfig;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;

public class ConfigForegroundManager {

	private Composite currentlyInBackgroundsParent;
	private Composite parametersPage;
	private AbstractConfigWidgetsWrapper currentlyInForegroundPage;

	public ConfigForegroundManager(Composite foregroundParent) {
		parametersPage = foregroundParent;
		currentlyInBackgroundsParent = new Composite(new Shell(), SWT.NONE);
	}

	public void setToForeground(
			AbstractConfigWidgetsWrapper configWidgetsWrapper) {
		if (currentlyInForegroundPage != null){
			currentlyInForegroundPage.setParent(currentlyInBackgroundsParent);
			currentlyInForegroundPage.setVisible(false);
		}
		configWidgetsWrapper.setParent(parametersPage);
//		configWidgetsWrapper.setGridData();
		configWidgetsWrapper.setVisible(true);
		currentlyInForegroundPage = configWidgetsWrapper;

		parametersPage.getParent().getParent().getParent().pack();
	}

	public void setToBackground(AbstractConfigWidgetsWrapper configComposite) {
		configComposite.setParent(currentlyInBackgroundsParent);
	}

}
