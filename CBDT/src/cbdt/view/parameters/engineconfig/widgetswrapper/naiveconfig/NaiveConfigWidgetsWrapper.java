package cbdt.view.parameters.engineconfig.widgetswrapper.naiveconfig;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.engineconfig.AbstractEngineConfigController;
import cbdt.control.engineconfig.NaiveConfigController;
import cbdt.model.parameters.engineconfig.NaiveEngineConfig;
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameters.SimpleParameterComposite;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.IntegerFormatChecker;


public class NaiveConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	private Label reqValuesLabel;
	private SimpleParameterComposite requiredExpectedUtilitiesComposite;
	private IntegerFormatChecker integerFormatChecker;

	public NaiveConfigWidgetsWrapper(Composite parent) {
		reqValuesLabel = new Label(parent, SWT.NONE);
		reqValuesLabel.setText("Required expected utility values:");
		
		requiredExpectedUtilitiesComposite = new SimpleParameterComposite(parent);
		requiredExpectedUtilitiesComposite.setHintLabel(new HintLabelWrapper(requiredExpectedUtilitiesComposite));
		requiredExpectedUtilitiesComposite.setToolTipText("The value must be a natural number");
		
		integerFormatChecker = new IntegerFormatChecker();
		requiredExpectedUtilitiesComposite.setNumberFormatChecker(integerFormatChecker);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof NaiveEngineConfig){
			NaiveEngineConfig config = (NaiveEngineConfig)arg0;
			requiredExpectedUtilitiesComposite.getText().setText(String.valueOf(config.getRequestedExpectedUtilityValues()));
		}
	}

	@Override
	public void setParent(Composite parent) {
		reqValuesLabel.setParent(parent);
		requiredExpectedUtilitiesComposite.setParent(parent);
	}

	@Override
	public void setConfigController(
			AbstractEngineConfigController configController) {
		NaiveConfigController naiveConfigController = (NaiveConfigController)configController;
		
		requiredExpectedUtilitiesComposite.getText().addModifyListener(new RequestedExpectedUtilityValuesModifyListener(
				naiveConfigController, requiredExpectedUtilitiesComposite.getHintLabel(), integerFormatChecker));
	}

}
