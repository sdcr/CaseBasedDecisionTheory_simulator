package cbdt.view.menu.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.parameterspage.parameters.ParametersController;

/**
 * This class extends {@link OpenFileDialogSelectionListener} and is used to be
 * called when the parameters are to be stored a file.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SaveParametersSelectionListener extends
		OpenFileDialogSelectionListener {

	private ParametersController parametersController;

	/**
	 * The constructor.
	 * 
	 * @param shell
	 * @param controller
	 */
	public SaveParametersSelectionListener(Shell shell,
			ParametersController controller) {
		super(shell);
		this.parametersController = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		parametersController.getParametersConfigPageController()
				.goToForeground();
		if (parametersController.getParametersConfigPageController()
				.getPageWrapper().getParametersPage()
				.hasValidAspirationLevelParameters()) {
			String[] filterExt = { "*.xml", "*.*" };
			String filepathFromDialog = this.getFilepathFromDialog(SWT.SAVE,
					"Save parameters", filterExt);
			if (filepathFromDialog != null)
				parametersController.saveParametersToFile(filepathFromDialog);
		} else {
			parametersController
					.getParametersConfigPageController()
					.getMainController()
					.getMessageBoxManager()
					.showInfoMessage(
							"At least one aspiration level parameter has "
									+ "an invalid value.");
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
