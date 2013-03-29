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
import cbdt.view.parameters.engineconfig.ConfigBlockTitleLabelWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.AbsoluteActionOccurancesSelectionListener;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.LowestAspirationLevelsSelectionListener;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.RelativeActionOccurancesSelectionListener;
import cbdt.view.parameters.engineconfig.widgetswrapper.listener.RequestedExpectedUtilityValuesModifyListener;

public class CommonConfigWidgetsWrapper implements Observer {

	private Label reqValuesLabel;
	private SimpleParameterComposite requiredExpectedUtilitiesComposite;
	private IntegerFormatChecker integerFormatChecker;

	private Label calcActionOcurrancesLabel;
	private Button absActionOcurrancesButton;
	private Button relActionOcurrancesButton;
	private Label aspirationLevelsLabel;
	private Button lowestAspirationLevelsButton;

	public CommonConfigWidgetsWrapper(Composite parent) {
		ConfigBlockTitleLabelWrapper algoIndependentTitleWrapper = 
				new ConfigBlockTitleLabelWrapper(parent);
		algoIndependentTitleWrapper.getLabel().setText("Algorithm-independent configurations:");
		
		createRequestedExpectedUtilitiesWidgets(parent);
		createSaveActionOccurancesCheckBoxes(parent);
		createCalcLowestAspirationLevelsCheckBox(parent);
	}

	private void createCalcLowestAspirationLevelsCheckBox(Composite parent) {
		aspirationLevelsLabel = new Label(parent, SWT.NONE);
		aspirationLevelsLabel.setText("Save aspiration levels:");

		GridData labelGridData = new GridData();
		labelGridData.horizontalIndent = ConfigBlockTitleLabelWrapper.CONFIG_BLOCK_H_INDENT;
		aspirationLevelsLabel.setLayoutData(labelGridData);
		
		lowestAspirationLevelsButton = new Button(parent, SWT.CHECK);
		lowestAspirationLevelsButton .setText("lowest aspiration levels");
	}

	private void createSaveActionOccurancesCheckBoxes(Composite parent) {
		calcActionOcurrancesLabel = new Label(parent, SWT.NONE);
		calcActionOcurrancesLabel.setText("Save action occurances:");
		GridData labelGridData = new GridData();
		labelGridData.verticalSpan = 2;
		labelGridData.verticalAlignment = SWT.BEGINNING;
		labelGridData.horizontalIndent = ConfigBlockTitleLabelWrapper.CONFIG_BLOCK_H_INDENT;
		calcActionOcurrancesLabel.setLayoutData(labelGridData);

		absActionOcurrancesButton = new Button(parent, SWT.CHECK);
		absActionOcurrancesButton.setText("absolute occurances");

		relActionOcurrancesButton = new Button(parent, SWT.CHECK);
		relActionOcurrancesButton.setText("relative occurances");
	}

	private void createRequestedExpectedUtilitiesWidgets(Composite parent) {
		reqValuesLabel = new Label(parent, SWT.NONE);
		reqValuesLabel.setText("Expected utility values:");
		GridData labelGridData = new GridData();
		labelGridData.horizontalIndent = ConfigBlockTitleLabelWrapper.CONFIG_BLOCK_H_INDENT;
		reqValuesLabel.setLayoutData(labelGridData);

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
			lowestAspirationLevelsButton.setSelection(config
					.isCalculateLowestAspirationLevels());
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
		lowestAspirationLevelsButton
				.addSelectionListener(new LowestAspirationLevelsSelectionListener(
						pageController, lowestAspirationLevelsButton));
		
	}

}
