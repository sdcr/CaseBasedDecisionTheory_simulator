package cbdt.view.parameterspage.config.engine.dfskeeptree;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.view.parameterspage.config.ConfigBlockTitleLabelFactory;

/**
 * Creates the widgets for the DFSkeepTree configuration.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSkeepTreeConfigWidgetsFactory {

	private static final int INNER_CHECKBOX_MARGIN_LEFT = 20;

	/**
	 * Create and return a {@link Button} in form of a check box.
	 * 
	 * @param parent
	 * @param checkboxString
	 * @return
	 */
	public Button createCheckbox(Composite parent, String checkboxString) {
		Button checkboxButton = new Button(parent, SWT.CHECK);
		checkboxButton.setText(checkboxString);

		GridData gridData = new GridData();
		gridData.horizontalIndent = INNER_CHECKBOX_MARGIN_LEFT;
		checkboxButton.setLayoutData(gridData);
		return checkboxButton;
	}

	/**
	 * Create and return a {@link Label} for the configuration of saving tree
	 * structure.
	 * 
	 * @param parent
	 * @return
	 */
	public Label createSaveTreeStructureLabel(Composite parent) {
		Label saveTreeStructureLabel = new Label(parent, SWT.NONE);
		saveTreeStructureLabel.setText("Save tree structure:");

		GridData labelGridData = new GridData();
		labelGridData.verticalSpan = 3;
		labelGridData.verticalAlignment = SWT.BEGINNING;
		labelGridData.horizontalIndent = ConfigBlockTitleLabelFactory.CONFIG_BLOCK_H_INDENT;
		saveTreeStructureLabel.setLayoutData(labelGridData);
		return saveTreeStructureLabel;
	}

}
