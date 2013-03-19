package cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.pages.engineconfig.DFSkeepTreeConfigController;

public class SaveAspirationLevelsSelectionListener implements SelectionListener {

	private DFSkeepTreeConfigController configController;
	private Button saveAspirationLevelsButton;

	public SaveAspirationLevelsSelectionListener(
			DFSkeepTreeConfigController configController,
			Button saveAspirationLevelsButton) {
				this.configController = configController;
				this.saveAspirationLevelsButton = saveAspirationLevelsButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setSaveAspirationLevels(saveAspirationLevelsButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
