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
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameters.SimpleParameterComposite;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;

public class DFSmatrixHighPrecisionConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	private Label reqDecimalPlacesLabel;
	private SimpleParameterComposite requiredDecimalPlacesComposite;
	private IntegerFormatChecker integerFormatChecker;

	public DFSmatrixHighPrecisionConfigWidgetsWrapper(Composite parent) {
		super(parent);
		reqDecimalPlacesLabel = new Label(parent, SWT.NONE);
		reqDecimalPlacesLabel.setText("Precision in decimal places:");
		GridData reqDecPlacesLabelGridData = new GridData();
		reqDecPlacesLabelGridData.verticalIndent = MARGIN_TOP;
		reqDecimalPlacesLabel.setLayoutData(reqDecPlacesLabelGridData);

		requiredDecimalPlacesComposite = new SimpleParameterComposite(
				parent);
		requiredDecimalPlacesComposite.setHintLabel(new HintLabelWrapper(
				requiredDecimalPlacesComposite));
		requiredDecimalPlacesComposite
				.setToolTipText("The value must be a natural number");
		GridData reqDecPlacesCompositeGridData = new GridData();
		reqDecPlacesCompositeGridData.verticalIndent = MARGIN_TOP;
		requiredDecimalPlacesComposite.setLayoutData(reqDecPlacesCompositeGridData);

		integerFormatChecker = new IntegerFormatChecker();
		requiredDecimalPlacesComposite
				.setNumberFormatChecker(integerFormatChecker);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		super.update(arg0, arg1);
		if (arg0 instanceof DFSmatrixHighPrecEngineConfig) {
			DFSmatrixHighPrecEngineConfig config = (DFSmatrixHighPrecEngineConfig) arg0;
			requiredDecimalPlacesComposite.getText().setText(
					String.valueOf(config.getNumberOfDecimalPlaces()));
		}
	}

	@Override
	public void setParent(Composite parent) {
		super.setParent(parent);
		reqDecimalPlacesLabel.setParent(parent);
		requiredDecimalPlacesComposite.setParent(parent);
	}

	@Override
	public void setConfigController(
			AbstractEngineConfigController configController) {
		super.setConfigController(configController);
		DFSmatrixHighPrecisionConfigController customController = 
				(DFSmatrixHighPrecisionConfigController)configController;
		requiredDecimalPlacesComposite.getText().addModifyListener(
				new RequiredDecimalPlacesModifyListener(
						customController,
						requiredDecimalPlacesComposite.getHintLabel(),
						integerFormatChecker));
	}

}