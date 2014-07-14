package cbdt.view.menu.listeners;

import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

/**
 * This abstract class extends {@link SelectionListener} and allows all
 * extending classes to get a file path from the user by use of a
 * {@link FileDialog}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public abstract class OpenFileDialogSelectionListener implements
		SelectionListener {

	private Shell shell;

	/**
	 * The constructor.
	 * 
	 * @param shell
	 */
	public OpenFileDialogSelectionListener(Shell shell) {
		this.shell = shell;
	}

	/**
	 * Get a file path by showing a {@link FileDialog} to the user.
	 * 
	 * @param style
	 *            The style, the {@link FileDialog} should have.
	 * @param title
	 *            The title of the dialog.
	 * @param filterExt
	 *            The file extension the {@link FileDialog} should filter for.
	 * @return Returns the {@link String} of the file path the user selected.
	 */
	protected String getFilepathFromDialog(int style, String title,
			String[] filterExt) {
		FileDialog fileDialog = new FileDialog(shell, style);
		fileDialog.setText(title);
		fileDialog.setFilterPath("C:/");
		fileDialog.setFilterExtensions(filterExt);
		return fileDialog.open();
	}
}
