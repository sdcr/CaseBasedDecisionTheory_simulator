package cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.control.pages.engineconfig.DFSkeepTreeConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;

public class DFSkeepTreeConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	private Label saveTreeStructureLabel;
	private Button saveActionNamesButton;
	private Button saveAspirationLevelsButton;
	private Button saveTreeButton;

	public DFSkeepTreeConfigWidgetsWrapper(Composite parent) {
		super(parent);
		saveTreeStructureLabel = new Label(parent, SWT.NONE);
		saveTreeStructureLabel.setText("Save tree structure:");
		GridData checkBoxGridData = new GridData();
		checkBoxGridData.verticalSpan = 3;
		checkBoxGridData.verticalAlignment = SWT.BEGINNING;
		saveTreeStructureLabel.setLayoutData(checkBoxGridData);
		
		saveTreeButton = new Button(parent, SWT.CHECK);
		saveTreeButton.setText("save tree");

		saveActionNamesButton = new Button(parent, SWT.CHECK);
		saveActionNamesButton.setText("action names");
		GridData saveActionNamesGridData = new GridData();
		saveActionNamesGridData.horizontalIndent = 20;
		saveActionNamesButton.setLayoutData(saveActionNamesGridData);

		saveAspirationLevelsButton = new Button(parent, SWT.CHECK);
		saveAspirationLevelsButton.setText("aspiration levels");
		GridData saveAspirationLevelsGridData = new GridData();
		saveAspirationLevelsGridData.horizontalIndent = 20;
		saveAspirationLevelsButton.setLayoutData(saveAspirationLevelsGridData);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		super.update(arg0, arg1);
		if (arg0 instanceof DFSkeepTreeEngineConfig) {
			DFSkeepTreeEngineConfig config = (DFSkeepTreeEngineConfig) arg0;
			saveTreeButton.setSelection(config.isSaveTreeStructure());
			saveActionNamesButton.setSelection(config.isSaveActionNames());
			saveAspirationLevelsButton.setSelection(config.isSaveAspirationLevels());
		}
	}

	@Override
	public void setParent(Composite parent) {
		super.setParent(parent);
	}

	@Override
	public void setConfigController(
			AbstractEngineConfigController configController) {
		super.setConfigController(configController);
		DFSkeepTreeConfigController customConfigController = (DFSkeepTreeConfigController) configController; 
		saveTreeButton.addSelectionListener(new SaveTreeSelectionListener(customConfigController, saveTreeButton));
		saveActionNamesButton.addSelectionListener(new SaveActionNamesSelectionListener(customConfigController, saveActionNamesButton));
		saveAspirationLevelsButton.addSelectionListener(new SaveAspirationLevelsSelectionListener(customConfigController, saveAspirationLevelsButton));
	}

	@Override
	public void setEngineConfigModel(AbstractEngineConfiguration config) {
		config.addObserver(this);
		update(config, null);
	}

}
