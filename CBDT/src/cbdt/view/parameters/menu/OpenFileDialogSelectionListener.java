package cbdt.view.parameters.menu;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public abstract class OpenFileDialogSelectionListener implements SelectionListener{

	private Shell shell;

	public OpenFileDialogSelectionListener(Shell shell) {
		this.shell = shell;
	}
	
	public String getFilepathFromDialog(){
        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
        fileDialog.setText("Add a plugin");
        fileDialog.setFilterPath("C:/");
        String[] filterExt = { "*.xml", "*.*" };
        fileDialog.setFilterExtensions(filterExt);
        return fileDialog.open();
	}
}
