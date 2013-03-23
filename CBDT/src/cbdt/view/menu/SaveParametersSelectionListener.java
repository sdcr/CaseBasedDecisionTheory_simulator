package cbdt.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.pages.ParametersPageController;

public class SaveParametersSelectionListener extends OpenFileDialogSelectionListener {

	private ParametersPageController controller;

	public SaveParametersSelectionListener(Shell shell, ParametersPageController controller) {
		super(shell);
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		controller.goToForeground();
		if(controller.getPageReference().getParametersPage().hasValidAspirationLevelParameters()){
			String[] filterExt = { "*.xml", "*.*" };
			String filepathFromDialog = this.getFilepathFromDialog(SWT.SAVE, "Save parameters", filterExt);
			if(filepathFromDialog != null)
				controller.saveParametersToFile(filepathFromDialog);
		} else {
			controller.getMessageBoxManager().showInfoMessage("At least one aspiration level parameter has an invalid value.");
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
