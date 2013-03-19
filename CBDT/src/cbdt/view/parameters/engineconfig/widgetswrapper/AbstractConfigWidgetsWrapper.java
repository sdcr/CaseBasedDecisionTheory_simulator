package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.view.HintLabelWrapper;
import cbdt.view.IntegerFormatChecker;
import cbdt.view.parameters.SimpleParameterComposite;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.AbsoluteActionOccurancesSelectionListener;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.RelativeActionOccurancesSelectionListener;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.RequestedExpectedUtilityValuesModifyListener;

public abstract class AbstractConfigWidgetsWrapper implements Observer {

	private Label reqValuesLabel;
	private SimpleParameterComposite requiredExpectedUtilitiesComposite;
	private IntegerFormatChecker integerFormatChecker;
	
	private Label calcActionOcurrancesLabel;
	private Button absActionOcurrancesButton;
	private Button relActionOcurrancesButton;
	
	public AbstractConfigWidgetsWrapper(Composite parent) {
		createRequestedExpectedUtilitiesWidgets(parent);
		createSaveActionOccurancesCheckBoxes(parent);
	}

	private void createSaveActionOccurancesCheckBoxes(Composite parent) {
		calcActionOcurrancesLabel = new Label(parent, SWT.NONE);
		calcActionOcurrancesLabel.setText("Save action occurances:");
		GridData checkBoxGridData = new GridData();
		checkBoxGridData.verticalSpan = 2;
		checkBoxGridData.verticalAlignment = SWT.BEGINNING;
		calcActionOcurrancesLabel.setLayoutData(checkBoxGridData);
		
		absActionOcurrancesButton = new Button(parent, SWT.CHECK);
		absActionOcurrancesButton.setText("absolute numbers");

		relActionOcurrancesButton = new Button(parent, SWT.CHECK);
		relActionOcurrancesButton.setText("relative numbers");
	}

	private void createRequestedExpectedUtilitiesWidgets(Composite parent) {
		reqValuesLabel = new Label(parent, SWT.NONE);
		reqValuesLabel.setText("Expected utility values:");

		requiredExpectedUtilitiesComposite = new SimpleParameterComposite(
				parent);
		requiredExpectedUtilitiesComposite.setHintLabel(new HintLabelWrapper(
				requiredExpectedUtilitiesComposite));
		requiredExpectedUtilitiesComposite
				.setToolTipText("The value must be a natural number");

		integerFormatChecker = new IntegerFormatChecker();
		requiredExpectedUtilitiesComposite
				.setNumberFormatChecker(integerFormatChecker);
	}

//	public void setEngineConfigModel(AbstractEngineConfiguration config) {
//		config.addObserver(this);
//		update(config, null);
//	}

	public abstract void setEngineConfigModel(AbstractEngineConfiguration config);
	
	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof AbstractEngineConfiguration) {
			AbstractEngineConfiguration config = (AbstractEngineConfiguration) arg0;
			requiredExpectedUtilitiesComposite.getText().setText(
					String.valueOf(config.getRequestedExpectedUtilityValues()));
			absActionOcurrancesButton.setSelection(config.isCalculateAbsoluteActionOccurances());
			relActionOcurrancesButton.setSelection(config.isCalculateRelativeActionOccurances());
		}
	}

	public void setParent(Composite parent) {
		reqValuesLabel.setParent(parent);
		requiredExpectedUtilitiesComposite.setParent(parent);
		calcActionOcurrancesLabel.setParent(parent);
		absActionOcurrancesButton.setParent(parent);
		relActionOcurrancesButton.setParent(parent);
		parent.layout();
	}

	public void setConfigController(
			AbstractEngineConfigController configController) {
		requiredExpectedUtilitiesComposite.getText().addModifyListener(
				new RequestedExpectedUtilityValuesModifyListener(
						configController,
						requiredExpectedUtilitiesComposite.getHintLabel(),
						integerFormatChecker));
		absActionOcurrancesButton.addSelectionListener(
				new AbsoluteActionOccurancesSelectionListener(configController, 
						absActionOcurrancesButton));
		relActionOcurrancesButton.addSelectionListener(
				new RelativeActionOccurancesSelectionListener(configController, 
						relActionOcurrancesButton));
	}

}
