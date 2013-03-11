package cbdt.view.parameters.menu;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.ParametersController;

public class OpenParametersSelectionListener extends OpenFileDialogSelectionListener {

	private ParametersController controller;

	public OpenParametersSelectionListener(Shell shell, ParametersController controller) {
		super(shell);
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String filepathFromDialog = this.getFilepathFromDialog();
		if(filepathFromDialog != null)
			controller.loadParametersFromFile(filepathFromDialog);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
