package cbdt.view.menu;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

public abstract class OpenFileDialogSelectionListener implements SelectionListener{

	private Shell shell;

	public OpenFileDialogSelectionListener(Shell shell) {
		this.shell = shell;
	}
	
	protected String getFilepathFromDialog(int style, String title, String[] filterExt){
        FileDialog fileDialog = new FileDialog(shell, style);
        fileDialog.setText(title);
        fileDialog.setFilterPath("C:/");
        fileDialog.setFilterExtensions(filterExt);
        return fileDialog.open();
	}
}
