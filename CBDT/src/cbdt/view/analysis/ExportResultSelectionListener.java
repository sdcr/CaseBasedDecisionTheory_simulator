package cbdt.view.analysis;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.pages.AnalysisPageController;
import cbdt.view.menu.OpenFileDialogSelectionListener;

public class ExportResultSelectionListener extends OpenFileDialogSelectionListener {

	private AnalysisPageController controller;

	public ExportResultSelectionListener(Shell shell, AnalysisPageController controller) {
		super(shell);
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String[] filterExt = { "*.csv", "*.*" };
		controller.exportResults(getFilepathFromDialog(SWT.SAVE, "Export simulation results", filterExt));
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
