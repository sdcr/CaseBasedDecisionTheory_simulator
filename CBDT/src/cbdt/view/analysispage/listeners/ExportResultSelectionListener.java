package cbdt.view.analysispage.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.analysispage.AnalysisPageController;
import cbdt.model.result.Result;
import cbdt.view.menu.listeners.OpenFileDialogSelectionListener;

/**
 * This class is used to listen for a {@link SelectionEvent} to export the
 * {@link Result}s into a file.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ExportResultSelectionListener extends
		OpenFileDialogSelectionListener {

	/**
	 * The {@link AnalysisPageController}.
	 */
	private AnalysisPageController controller;

	public ExportResultSelectionListener(Shell shell,
			AnalysisPageController controller) {
		super(shell);
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		String[] filterExt = { "*.csv", "*.*" };
		String filePath = getFilepathFromDialog(SWT.SAVE,
				"Export simulation results", filterExt);
		if (filePath != null)
			controller.exportResults(filePath);
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
