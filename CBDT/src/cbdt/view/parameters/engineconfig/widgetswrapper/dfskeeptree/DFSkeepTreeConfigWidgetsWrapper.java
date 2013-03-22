package cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.control.pages.engineconfig.DFSkeepTreeConfigController;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;

public class DFSkeepTreeConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	private static final int INNER_CHECKBOX_MARGIN_LEFT = 20;
	
	private Label saveTreeStructureLabel;
	private Button saveActionNamesButton;
	private Button saveAspirationLevelsButton;
	private Button saveTreeButton;

	public DFSkeepTreeConfigWidgetsWrapper(Composite parent) {
		saveTreeStructureLabel = new Label(parent, SWT.NONE);
		saveTreeStructureLabel.setText("Save tree structure:");
		GridData labelGridData = new GridData();
		labelGridData.verticalSpan = 3;
		labelGridData.verticalAlignment = SWT.BEGINNING;
		labelGridData.verticalIndent = MARGIN_TOP;
		saveTreeStructureLabel.setLayoutData(labelGridData);
		
		saveTreeButton = new Button(parent, SWT.CHECK);
		saveTreeButton.setText("keep tree");
		GridData saveTreeButtonGridData = new GridData();
		saveTreeButtonGridData.verticalIndent = MARGIN_TOP;
		saveTreeButton.setLayoutData(saveTreeButtonGridData);
		
		saveActionNamesButton = new Button(parent, SWT.CHECK);
		saveActionNamesButton.setText("action names");
		GridData saveActionNamesGridData = new GridData();
		saveActionNamesGridData.horizontalIndent = INNER_CHECKBOX_MARGIN_LEFT;
		saveActionNamesButton.setLayoutData(saveActionNamesGridData);

		saveAspirationLevelsButton = new Button(parent, SWT.CHECK);
		saveAspirationLevelsButton.setText("aspiration levels");
		GridData saveAspirationLevelsGridData = new GridData();
		saveAspirationLevelsGridData.horizontalIndent = INNER_CHECKBOX_MARGIN_LEFT;
		saveAspirationLevelsButton.setLayoutData(saveAspirationLevelsGridData);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
//		super.update(arg0, arg1);
		if (arg0 instanceof DFSkeepTreeEngineConfig) {
			DFSkeepTreeEngineConfig config = (DFSkeepTreeEngineConfig) arg0;
			saveTreeButton.setSelection(config.isSaveTreeStructure());
			saveActionNamesButton.setSelection(config.isSaveActionNames());
			saveAspirationLevelsButton.setSelection(config.isSaveAspirationLevels());
		}
	}

	@Override
	public void setParent(Composite parent) {
//		super.setParent(parent);
		saveTreeStructureLabel.setParent(parent);
		saveTreeButton.setParent(parent);
		saveActionNamesButton.setParent(parent);
		saveAspirationLevelsButton.setParent(parent);
	}

	@Override
	public void setConfigController(
			AbstractEngineConfigController configController) {
//		super.setControllers(pageController, configController);
		DFSkeepTreeConfigController customConfigController = (DFSkeepTreeConfigController) configController; 
		saveTreeButton.addSelectionListener(new SaveTreeSelectionListener(customConfigController, saveTreeButton));
		saveActionNamesButton.addSelectionListener(new SaveActionNamesSelectionListener(customConfigController, saveActionNamesButton));
		saveAspirationLevelsButton.addSelectionListener(new SaveAspirationLevelsSelectionListener(customConfigController, saveAspirationLevelsButton));
	}

}
