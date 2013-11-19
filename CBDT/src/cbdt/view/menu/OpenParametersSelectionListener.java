package cbdt.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.parameterspage.parameters.ParametersController;

public class OpenParametersSelectionListener extends OpenFileDialogSelectionListener {

	private ParametersController controller;

	public OpenParametersSelectionListener(Shell shell, ParametersController controller) {
		super(shell);
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String[] filterExt = { "*.xml", "*.*" };
		String filepathFromDialog = this.getFilepathFromDialog(SWT.OPEN, "Open parameters", filterExt);
		if(filepathFromDialog != null)
			controller.loadParametersFromFile(filepathFromDialog);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
