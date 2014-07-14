package cbdt.view.parameterspage.config.engine.dfskeeptree.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.config.engine.DFSkeepTreeConfigController;

/**
 * The {@link SelectionListener} implementation in order to set the model to
 * save the aspiration levels in the computed tree.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SaveAspirationLevelsSelectionListener implements SelectionListener {

	private DFSkeepTreeConfigController configController;
	private Button saveAspirationLevelsButton;

	/**
	 * The constructor.
	 * 
	 * @param configController
	 * @param saveAspirationLevelsButton
	 */
	public SaveAspirationLevelsSelectionListener(
			DFSkeepTreeConfigController configController,
			Button saveAspirationLevelsButton) {
		this.configController = configController;
		this.saveAspirationLevelsButton = saveAspirationLevelsButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setSaveAspirationLevels(saveAspirationLevelsButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
