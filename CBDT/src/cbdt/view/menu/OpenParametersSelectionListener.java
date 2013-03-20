package cbdt.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.pages.ParametersPageController;

public class OpenParametersSelectionListener extends OpenFileDialogSelectionListener {

	private ParametersPageController controller;

	public OpenParametersSelectionListener(Shell shell, ParametersPageController controller) {
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
