package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * This class is responsible for displaying {@link MessageBox}es to the user.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class MessageBoxManager {

	private Shell shell;

	/**
	 * The constructor.
	 * 
	 * @param shell
	 */
	public MessageBoxManager(Shell shell) {
		this.shell = shell;
	}

	/**
	 * Show the passed {@link String} as an error message to the user.
	 * 
	 * @param message
	 */
	public void showErrorMessage(String message) {
		MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
		String defaultMessage = "An error occured: ";
		if (message != null)
			defaultMessage = defaultMessage + message;
		box.setMessage(defaultMessage);
		box.open();
	}

	/**
	 * Show the passed {@link String} as an info message to the user.
	 * 
	 * @param message
	 */
	public void showInfoMessage(String message) {
		MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
		box.setMessage(message);
		box.open();
	}
}
