package cbdt.view.parameters.engineconfig.widgetswrapper.dfsmatrixhp;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.control.pages.engineconfig.DFSmatrixHighPrecisionConfigController;
import cbdt.control.validators.IntegerFormatChecker;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;
import cbdt.view.parameters.SimpleParameterComposite;
import cbdt.view.parameters.aspirationlevel.SimpleParameterHintLabelWrapper;
import cbdt.view.parameters.engineconfig.ConfigBlockTitleLabelWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;

public class DFSmatrixHighPrecisionConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	private Label reqDecimalPlacesLabel;
	private SimpleParameterComposite requiredDecimalPlacesComposite;
	private IntegerFormatChecker integerFormatChecker;

	public DFSmatrixHighPrecisionConfigWidgetsWrapper(Composite parent) {
		reqDecimalPlacesLabel = new Label(parent, SWT.NONE);
		GridData labelLayoutData = new GridData();
		labelLayoutData.horizontalIndent = ConfigBlockTitleLabelWrapper.CONFIG_BLOCK_H_INDENT;
		reqDecimalPlacesLabel.setLayoutData(labelLayoutData);
		reqDecimalPlacesLabel.setText("Precision in decimal places:");

		requiredDecimalPlacesComposite = new SimpleParameterComposite(
				parent);
		requiredDecimalPlacesComposite.setHintLabel(new SimpleParameterHintLabelWrapper(
				requiredDecimalPlacesComposite));
		requiredDecimalPlacesComposite
				.setToolTipText("The value must be a natural number");

		integerFormatChecker = new IntegerFormatChecker();
		requiredDecimalPlacesComposite
				.setNumberFormatChecker(integerFormatChecker);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof DFSmatrixHighPrecEngineConfig) {
			DFSmatrixHighPrecEngineConfig config = (DFSmatrixHighPrecEngineConfig) arg0;
			requiredDecimalPlacesComposite.getText().setText(
					String.valueOf(config.getNumberOfDecimalPlaces()));
		}
	}

	@Override
	public void setParent(Composite parent) {
		reqDecimalPlacesLabel.setParent(parent);
		requiredDecimalPlacesComposite.setParent(parent);
	}

	@Override
	public void setConfigController(AbstractEngineConfigController configController) {
		DFSmatrixHighPrecisionConfigController customController = 
				(DFSmatrixHighPrecisionConfigController)configController;
		requiredDecimalPlacesComposite.getText().addModifyListener(
				new RequiredDecimalPlacesModifyListener(
						customController,
						requiredDecimalPlacesComposite.getHintLabel(),
						integerFormatChecker));
	}

}
