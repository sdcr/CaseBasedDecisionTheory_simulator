package cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.engineconfig.DFSkeepTreeConfigController;

public class SaveActionNamesSelectionListener implements SelectionListener {

	private DFSkeepTreeConfigController configController;
	private Button saveActionNamesButton;

	public SaveActionNamesSelectionListener(
			DFSkeepTreeConfigController configController,
			Button saveActionNamesButton) {
				this.configController = configController;
				this.saveActionNamesButton = saveActionNamesButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setSaveActionNames(saveActionNamesButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
