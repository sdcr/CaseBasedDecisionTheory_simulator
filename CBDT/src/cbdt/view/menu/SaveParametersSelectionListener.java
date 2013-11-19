package cbdt.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.parameterspage.parameters.ParametersController;

public class SaveParametersSelectionListener extends OpenFileDialogSelectionListener {

	private ParametersController parametersController;

	public SaveParametersSelectionListener(Shell shell, ParametersController controller) {
		super(shell);
		this.parametersController = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		parametersController.getParametersConfigPageController().goToForeground();
		if(parametersController.getParametersConfigPageController().getPageWrapper().getParametersPage().hasValidAspirationLevelParameters()){
			String[] filterExt = { "*.xml", "*.*" };
			String filepathFromDialog = this.getFilepathFromDialog(SWT.SAVE, "Save parameters", filterExt);
			if(filepathFromDialog != null)
				parametersController.saveParametersToFile(filepathFromDialog);
		} else {
			parametersController.getParametersConfigPageController().getMessageBoxManager().showInfoMessage("At least one aspiration level parameter has an invalid value.");
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
