package cbdt.view.parameterspage.config.engine.dfskeeptree;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.parameterspage.config.engine.DFSkeepTreeConfigController;
import cbdt.control.parameterspage.config.engine.IEngineConfigController;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.view.parameterspage.config.engine.AbstractEngineConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.engine.dfskeeptree.listeners.SaveActionNamesSelectionListener;
import cbdt.view.parameterspage.config.engine.dfskeeptree.listeners.SaveAspirationLevelsSelectionListener;
import cbdt.view.parameterspage.config.engine.dfskeeptree.listeners.SaveTreeSelectionListener;

/**
 * The {@link AbstractEngineConfigWidgetsWrapper} subclass for the DFSkeepTree
 * algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSkeepTreeConfigWidgetsWrapper extends
		AbstractEngineConfigWidgetsWrapper {

	private Label saveTreeStructureLabel;
	private Button saveActionNamesButton;
	private Button saveAspirationLevelsButton;
	private Button saveTreeButton;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 */
	public DFSkeepTreeConfigWidgetsWrapper(Composite parent) {
		DFSkeepTreeConfigWidgetsFactory factory = new DFSkeepTreeConfigWidgetsFactory();
		saveTreeStructureLabel = factory.createSaveTreeStructureLabel(parent);

		saveTreeButton = new Button(parent, SWT.CHECK);
		saveTreeButton.setText("keep tree");

		saveActionNamesButton = factory.createCheckbox(parent, "action names");
		saveAspirationLevelsButton = factory.createCheckbox(parent,
				"aspiration levels");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// super.update(arg0, arg1);
		if (arg0 instanceof DFSkeepTreeEngineConfig) {
			DFSkeepTreeEngineConfig config = (DFSkeepTreeEngineConfig) arg0;
			saveTreeButton.setSelection(config.isSaveTreeStructure());
			saveActionNamesButton.setEnabled(config.isSaveTreeStructure());
			saveAspirationLevelsButton.setEnabled(config.isSaveTreeStructure());

			saveActionNamesButton.setSelection(config.isSaveActionNames());
			saveAspirationLevelsButton.setSelection(config
					.isSaveAspirationLevels());
		}
	}

	@Override
	public void setParent(Composite parent) {
		// super.setParent(parent);
		saveTreeStructureLabel.setParent(parent);
		saveTreeButton.setParent(parent);
		saveActionNamesButton.setParent(parent);
		saveAspirationLevelsButton.setParent(parent);
	}

	@Override
	public void setEngineConfigController(
			IEngineConfigController configController) {
		// super.setControllers(pageController, configController);
		DFSkeepTreeConfigController customConfigController = (DFSkeepTreeConfigController) configController;
		saveTreeButton.addSelectionListener(new SaveTreeSelectionListener(
				customConfigController, saveTreeButton));
		saveActionNamesButton
				.addSelectionListener(new SaveActionNamesSelectionListener(
						customConfigController, saveActionNamesButton));
		saveAspirationLevelsButton
				.addSelectionListener(new SaveAspirationLevelsSelectionListener(
						customConfigController, saveAspirationLevelsButton));
	}

	@Override
	public boolean hasContentToShow() {
		return true;
	}

}
