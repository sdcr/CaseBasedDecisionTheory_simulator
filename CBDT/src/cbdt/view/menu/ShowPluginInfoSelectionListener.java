package cbdt.view.menu;

import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;

public class ShowPluginInfoSelectionListener implements SelectionListener {

	private Shell shell;

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
