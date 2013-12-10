package cbdt.view.parameterspage.config.widgetswrapper.dfskeeptree;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameterspage.config.engine.DFSkeepTreeConfigController;

/**
 * The {@link SelectionListener} implementation in order to set the config model
 * to save action names in the computed tree.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SaveActionNamesSelectionListener implements SelectionListener {

	private DFSkeepTreeConfigController configController;
	private Button saveActionNamesButton;

	/**
	 * Constructor.
	 * 
	 * @param configController
	 * @param saveActionNamesButton
	 */
	public SaveActionNamesSelectionListener(
			DFSkeepTreeConfigController configController,
			Button saveActionNamesButton) {
		this.configController = configController;
		this.saveActionNamesButton = saveActionNamesButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setSaveActionNames(saveActionNamesButton
				.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
