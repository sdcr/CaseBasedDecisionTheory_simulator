package cbdt.view.parameterspage.config.widgetswrapper;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.control.validators.IntegerFormatChecker;
import cbdt.model.config.common.CommonConfig;
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameterspage.config.ConfigBlockTitleLabelWrapper;
import cbdt.view.parameterspage.config.widgetswrapper.listeners.AbsoluteActionOccurancesSelectionListener;
import cbdt.view.parameterspage.config.widgetswrapper.listeners.LowestAspirationLevelsSelectionListener;
import cbdt.view.parameterspage.config.widgetswrapper.listeners.RelativeActionOccurancesSelectionListener;
import cbdt.view.parameterspage.config.widgetswrapper.listeners.RequestedExpectedUtilityValuesModifyListener;
import cbdt.view.parameterspage.parameters.SimpleParameterComposite;

/**
 * This class is a wrapper class for the widgets for the {@link CommonConfig}
 * model class.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class CommonConfigWidgetsWrapper implements Observer {

	private Label reqValuesLabel;
	private SimpleParameterComposite requiredExpectedUtilitiesComposite;
	private IntegerFormatChecker integerFormatChecker;

	private Label calcActionOcurrancesLabel;
	private Button absActionOcurrancesButton;
	private Button relActionOcurrancesButton;
	private Label aspirationLevelsLabel;
	private Button lowestAspirationLevelsButton;

	/**
	 * Constructor.
	 * 
	 * @param parent
	 */
	public CommonConfigWidgetsWrapper(Composite parent) {
		ConfigBlockTitleLabelWrapper algoIndependentTitleWrapper = new ConfigBlockTitleLabelWrapper(
				parent);
		algoIndependentTitleWrapper.getLabel().setText(
				"Algorithm-independent configurations:");

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
		lowestAspirationLevelsButton.setText("lowest aspiration levels");
	}

	private void createSaveActionOccurancesCheckBoxes(Composite parent) {
		calcActionOcurrancesLabel = new Label(parent, SWT.NONE);
		calcActionOcurrancesLabel.setText("Save action occurrences:");
		GridData labelGridData = new GridData();
		labelGridData.verticalSpan = 2;
		labelGridData.verticalAlignment = SWT.BEGINNING;
		labelGridData.horizontalIndent = ConfigBlockTitleLabelWrapper.CONFIG_BLOCK_H_INDENT;
		calcActionOcurrancesLabel.setLayoutData(labelGridData);

		absActionOcurrancesButton = new Button(parent, SWT.CHECK);
		absActionOcurrancesButton.setText("absolute occurrences");

		relActionOcurrancesButton = new Button(parent, SWT.CHECK);
		relActionOcurrancesButton.setText("relative occurrences");
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

	/**
	 * Set the {@link CommonConfig} model object to be displayed.
	 * 
	 * @param config
	 */
	public void setCommonConfigModel(CommonConfig config) {
		config.addObserver(this);
		update(config, null);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if (arg0 instanceof CommonConfig) {
			CommonConfig config = (CommonConfig) arg0;
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

	/**
	 * Set the {@link CommonConfigController}. Creates listeners for the common
	 * config widgets which pass events to the {@link CommonConfigController}.
	 * 
	 * @param commonConfigController
	 */
	public void setCommonConfigController(
			CommonConfigController commonConfigController) {
		requiredExpectedUtilitiesComposite.getText().addModifyListener(
				new RequestedExpectedUtilityValuesModifyListener(
						commonConfigController,
						requiredExpectedUtilitiesComposite.getHintLabel(),
						integerFormatChecker));
		absActionOcurrancesButton
				.addSelectionListener(new AbsoluteActionOccurancesSelectionListener(
						commonConfigController, absActionOcurrancesButton));
		relActionOcurrancesButton
				.addSelectionListener(new RelativeActionOccurancesSelectionListener(
						commonConfigController, relActionOcurrancesButton));
		lowestAspirationLevelsButton
				.addSelectionListener(new LowestAspirationLevelsSelectionListener(
						commonConfigController, lowestAspirationLevelsButton));

	}

}
