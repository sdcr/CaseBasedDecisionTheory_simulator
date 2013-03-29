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

public class PluginInfoDialog extends TitleAreaDialog {

  public PluginInfoDialog(Shell shell) {
    super(shell);
  }

  /**
   * @see org.eclipse.jface.window.Window#create() We complete the dialog with
   *      a title and a message
   */
  public void create() {
    super.create();
    setTitle("Case-Based Decision Theory");
    setMessage("A plugin to simulate decision processes according to Case-Based Decision Theory.");
  }

  /**
   * @see org.eclipse.jface.dialogs.Dialog#
   *      createDialogArea(org.eclipse.swt.widgets.Composite) Here we fill the
   *      center area of the dialog
   */
  protected Control createDialogArea(Composite parent) {
    final Composite area = new Composite(parent, SWT.NONE);
    GridData gridData = new GridData();
    gridData.grabExcessHorizontalSpace = true;
    area.setLayoutData(gridData);

    final GridLayout gridLayout = new GridLayout(2,false);
    gridLayout.marginWidth = 30;
    gridLayout.marginTop = 20;
    gridLayout.horizontalSpacing = 50;    
    area.setLayout(gridLayout);
    
    Label textLabel = new Label(area, SWT.NONE);
    textLabel.setText("This software was developed as part of an interdisciplinary project" +
    		"\nat the Chair of Economics at TU München.\n");
    
    GridData textGridData = new GridData();
    textGridData.horizontalSpan = 2;
    textGridData.grabExcessVerticalSpace = true;
    textGridData.minimumHeight = 45;
    textLabel.setLayoutData(textGridData);
    
    Label developer = new Label(area, SWT.NONE);
    developer.setText("Developer:");
    
    Label developerName = new Label(area, SWT.NONE);
    developerName.setText("Stephan da Costa Ribeiro");
    
    Label versionLabel = new Label(area, SWT.NONE);
    versionLabel.setText("Version:");
    
    Label versionNumberLabel = new Label(area, SWT.NONE);
    versionNumberLabel.setText("1.0.1");
    
    return area;
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
   *      The OK and Cancel buttons are replaced by just an OK button.
   *      createButton() (from Dialog) is used to create the button.
   */
  protected void createButtonsForButtonBar(Composite parent) {
    Button okButton = createButton(parent, IDialogConstants.OK_ID, "Ok", true);
    okButton.addSelectionListener(new SelectionAdapter() {
      public void widgetSelected(SelectionEvent e) {
        close();
      }
    });
  }

}