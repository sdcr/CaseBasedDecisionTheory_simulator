package cbdt.view.parameters.menu;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.ParametersController;

public class SaveParametersSelectionListener extends OpenFileDialogSelectionListener {

	private ParametersController controller;

	public SaveParametersSelectionListener(Shell shell, ParametersController controller) {
		super(shell);
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		controller.saveParametersToFile(this.getFilepathFromDialog());
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
