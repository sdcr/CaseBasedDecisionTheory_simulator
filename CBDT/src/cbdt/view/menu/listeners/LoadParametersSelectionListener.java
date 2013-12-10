package cbdt.view.menu.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.parameterspage.parameters.ParametersController;

/**
 * This class extends {@link OpenFileDialogSelectionListener} and is used to be
 * called when the parameters are to be loaded from a file.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class LoadParametersSelectionListener extends
		OpenFileDialogSelectionListener {

	private ParametersController controller;

	/**
	 * The constructor.
	 * 
	 * @param shell
	 * @param controller
	 */
	public LoadParametersSelectionListener(Shell shell,
			ParametersController controller) {
		super(shell);
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String[] filterExt = { "*.xml", "*.*" };
		String filepathFromDialog = this.getFilepathFromDialog(SWT.OPEN,
				"Open parameters", filterExt);
		if (filepathFromDialog != null)
			controller.loadParametersFromFile(filepathFromDialog);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
