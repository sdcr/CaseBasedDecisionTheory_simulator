package cbdt.view.menu.listeners;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;

import cbdt.view.menu.PluginInfoDialog;

/**
 * This class extends from {@link SelectionListener} and is used to show the
 * {@link PluginInfoDialog} to the user.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ShowPluginInfoSelectionListener implements SelectionListener {

	private Shell shell;

	/**
	 * The constructor.
	 * 
	 * @param shell
	 */
	public ShowPluginInfoSelectionListener(Shell shell) {
		this.shell = shell;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		PluginInfoDialog pluginInfoDialog = new PluginInfoDialog(shell);
		pluginInfoDialog.open();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

}
