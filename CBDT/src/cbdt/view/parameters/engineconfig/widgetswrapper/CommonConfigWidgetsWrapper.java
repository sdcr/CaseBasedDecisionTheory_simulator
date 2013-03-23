package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.ParametersPageController;
import cbdt.control.validators.IntegerFormatChecker;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameters.SimpleParameterComposite;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.AbsoluteActionOccurancesSelectionListener;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.RelativeActionOccurancesSelectionListener;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.RequestedExpectedUtilityValuesModifyListener;

public class CommonConfigWidgetsWrapper implements Observer {

	private Label reqValuesLabel;
	private SimpleParameterComposite requiredExpectedUtilitiesComposite;
	private IntegerFormatChecker integerFormatChecker;

	private Label calcActionOcurrancesLabel;
	private Button absActionOcurrancesButton;
	private Button relActionOcurrancesButton;

	public CommonConfigWidgetsWrapper(Composite parent) {
		createRequestedExpectedUtilitiesWidgets(parent);
		createSaveActionOccurancesCheckBoxes(parent);
	}

	private void createSaveActionOccurancesCheckBoxes(Composite parent) {
		calcActionOcurrancesLabel = new Label(parent, SWT.NONE);
		calcActionOcurrancesLabel.setText("Save action occurances:");
		GridData labelGridData = new GridData();
		labelGridData.verticalSpan = 2;
		labelGridData.verticalAlignment = SWT.BEGINNING;
		calcActionOcurrancesLabel.setLayoutData(labelGridData);

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

	public void setEngineConfigModel(CommonEngineConfiguration config) {
		config.addObserver(this);
		update(config, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof CommonEngineConfiguration) {
			CommonEngineConfiguration config = (CommonEngineConfiguration) arg0;
			requiredExpectedUtilitiesComposite.getText().setText(
					String.valueOf(config
							.getNumberOfRequestedExpectedUtilityValues()));
			absActionOcurrancesButton.setSelection(config
					.isCalculateAbsoluteActionOccurances());
			relActionOcurrancesButton.setSelection(config
					.isCalculateRelativeActionOccurances());
		}
	}

	public void setController(ParametersPageController pageController) {
		requiredExpectedUtilitiesComposite.getText().addModifyListener(
				new RequestedExpectedUtilityValuesModifyListener(
						pageController, requiredExpectedUtilitiesComposite
								.getHintLabel(), integerFormatChecker));
		absActionOcurrancesButton
				.addSelectionListener(new AbsoluteActionOccurancesSelectionListener(
						pageController, absActionOcurrancesButton));
		relActionOcurrancesButton
				.addSelectionListener(new RelativeActionOccurancesSelectionListener(
						pageController, relActionOcurrancesButton));
	}

}