package cbdt.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class MessageBoxManager {

	private Shell shell;

	public MessageBoxManager(Shell shell) {
		this.shell = shell;
	}
	
	public void showErrorMessage(String message){
		MessageBox box = new MessageBox(shell, SWT.ICON_ERROR);
		String defaultMessage = "An error occured: ";
		if(message != null)
			defaultMessage = defaultMessage + message;
		box.setMessage(defaultMessage);
		box.open();
	}

	public void showInfoMessage(String message) {
		MessageBox box = new MessageBox(shell, SWT.ICON_INFORMATION);
		box.setMessage(message);
		box.open();
	}
}
