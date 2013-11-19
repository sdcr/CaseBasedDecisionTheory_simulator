package cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;

import cbdt.control.parameters.config.engine.DFSkeepTreeConfigController;

public class SaveTreeSelectionListener implements SelectionListener {

	private DFSkeepTreeConfigController configController;
	private Button saveTreeButton;

	public SaveTreeSelectionListener(
			DFSkeepTreeConfigController configController,
			Button saveTreeButton) {
				this.configController = configController;
				this.saveTreeButton = saveTreeButton;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		configController.setSaveTree(saveTreeButton.getSelection());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
