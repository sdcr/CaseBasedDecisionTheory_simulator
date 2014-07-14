package cbdt.view.parameterspage.config.engine;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * A manager which is used to selectively show only one of the
 * {@link AbstractEngineConfig}s widgets at a time.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class EngineConfigForegroundManager {

	private Composite currentlyInBackgroundsParent;
	private Composite parametersPage;
	private AbstractEngineConfigWidgetsWrapper currentlyInForegroundPage;
	private Label algoSpecificTitleLabel;

	/**
	 * Constructor.
	 * 
	 * @param foregroundParent
	 * @param algoSpecificTitleLabel
	 */
	public EngineConfigForegroundManager(Composite foregroundParent,
			Label algoSpecificTitleLabel) {
		parametersPage = foregroundParent;
		this.algoSpecificTitleLabel = algoSpecificTitleLabel;
		currentlyInBackgroundsParent = new Composite(new Shell(), SWT.NONE);
	}

	/**
	 * Sets the widgets of a specific {@link AbstractEngineConfigWidgetsWrapper}
	 * to the foreground.
	 * 
	 * @param configWidgetsWrapper
	 */
	public void setToForeground(
			AbstractEngineConfigWidgetsWrapper configWidgetsWrapper) {
		if (currentlyInForegroundPage != null) {
			currentlyInForegroundPage.setParent(currentlyInBackgroundsParent);
		}
		configWidgetsWrapper.setParent(parametersPage);
		currentlyInForegroundPage = configWidgetsWrapper;

		algoSpecificTitleLabel.setEnabled(configWidgetsWrapper
				.hasContentToShow());

		parametersPage.getParent().getParent().getParent().pack();
	}

	/**
	 * Sets the widgets of a {@link AbstractEngineConfigWidgetsWrapper} to the
	 * background.
	 * 
	 * @param configComposite
	 */
	public void setToBackground(
			AbstractEngineConfigWidgetsWrapper configComposite) {
		configComposite.setParent(currentlyInBackgroundsParent);
	}

}
