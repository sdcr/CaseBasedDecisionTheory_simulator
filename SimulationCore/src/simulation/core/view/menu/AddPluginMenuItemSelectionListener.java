package simulation.core.view.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;

/**
 * Shows a file dialog to select a filepath to a bundle, which in turn defines
 * an ISimulationPlugin which should be added.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class AddPluginMenuItemSelectionListener implements SelectionListener {

	private Shell shell;
	private Controller controller;

	public AddPluginMenuItemSelectionListener(Shell shell, Controller controller) {
		this.shell = shell;
		this.controller = controller;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
		fileDialog.setText("Add a plugin");
		fileDialog.setFilterPath("C:/");
		String[] filterExt = { "*.jar", "*.*" };
		fileDialog.setFilterExtensions(filterExt);
		String filepath = fileDialog.open();

		if (filepath != null) {
			controller.addISimulationPlugin(filepath);
		}
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}
}
