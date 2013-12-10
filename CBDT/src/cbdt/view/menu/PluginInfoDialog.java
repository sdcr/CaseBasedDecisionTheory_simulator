package cbdt.view.menu;

import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

/**
 * This class defines a dialog to display information about the CBDT plugin to
 * the user. For that, it extends the {@link TitleAreaDialog} class.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class PluginInfoDialog extends TitleAreaDialog {

	/**
	 * The constructor.
	 * 
	 * @param shell
	 */
	public PluginInfoDialog(Shell shell) {
		super(shell);
	}

	/**
	 * @see org.eclipse.jface.window.Window#create()
	 * 
	 *      Set the title and message of the dialog.
	 */
	public void create() {
		super.create();
		setTitle("Case-Based Decision Theory");
		setMessage("A plugin to simulate decision processes according to Case-Based Decision Theory.");
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#
	 *      createDialogArea(org.eclipse.swt.widgets.Composite)
	 * 
	 *      Fill the center area of the dialog with information about the
	 *      plugin.
	 */
	protected Control createDialogArea(Composite parent) {
		final Composite dialogArea = new Composite(parent, SWT.NONE);
		setGridLayout(dialogArea);

		Label infoTextLabel = new Label(dialogArea, SWT.NONE);
		infoTextLabel.setText("This software was developed as part of an "
				+ "interdisciplinary project"
				+ "\nat the Chair of Economics at TU München.\n");
		setInfoTextGridData(infoTextLabel);

		Label developer = new Label(dialogArea, SWT.NONE);
		developer.setText("Developer:");

		Label developerName = new Label(dialogArea, SWT.NONE);
		developerName.setText("Stephan da Costa Ribeiro");

		Label versionLabel = new Label(dialogArea, SWT.NONE);
		versionLabel.setText("Version:");

		Label versionNumberLabel = new Label(dialogArea, SWT.NONE);
		versionNumberLabel.setText("1.0.1");

		return dialogArea;
	}

	/**
	 * Sets {@link GridData} to the info text {@link Label}.
	 * 
	 * @param infoTextLabel
	 */
	private void setInfoTextGridData(Label infoTextLabel) {
		GridData infoTextGridData = new GridData();
		infoTextGridData.horizontalSpan = 2;
		infoTextGridData.grabExcessVerticalSpace = true;
		infoTextGridData.minimumHeight = 45;
		infoTextLabel.setLayoutData(infoTextGridData);
	}

	/**
	 * Sets the {@link GridData} and {@link GridLayout} of the dialog area.
	 * 
	 * @param dialogArea
	 */
	private void setGridLayout(final Composite dialogArea) {
		GridData gridData = new GridData();
		gridData.grabExcessHorizontalSpace = true;
		dialogArea.setLayoutData(gridData);

		final GridLayout gridLayout = new GridLayout(2, false);
		gridLayout.marginWidth = 30;
		gridLayout.marginTop = 20;
		gridLayout.horizontalSpacing = 50;
		dialogArea.setLayout(gridLayout);
	}

	@Override
	protected Point getInitialSize() {
		Point initialSize = super.getInitialSize();
		initialSize.y = 270;
		return initialSize;
	}

	/**
	 * @see org.eclipse.jface.dialogs.Dialog#
	 *      createButtonsForButtonBar(org.eclipse.swt.widgets.Composite).
	 * 
	 *      The OK and Cancel buttons are replaced by just an OK button.
	 */
	protected void createButtonsForButtonBar(Composite parent) {
		Button okButton = createButton(parent, IDialogConstants.OK_ID, "Ok",
				true);
		okButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				close();
			}
		});
	}

}