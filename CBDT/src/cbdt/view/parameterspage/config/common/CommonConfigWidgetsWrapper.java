package cbdt.view.parameterspage.config.common;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.control.validators.IntegerFormatChecker;
import cbdt.model.config.common.CommonConfig;
import cbdt.view.parameterspage.config.common.listeners.AbsoluteActionOccurancesSelectionListener;
import cbdt.view.parameterspage.config.common.listeners.LowestAspirationLevelsSelectionListener;
import cbdt.view.parameterspage.config.common.listeners.RelativeActionOccurancesSelectionListener;
import cbdt.view.parameterspage.config.common.listeners.RequestedExpectedUtilityValuesModifyListener;
import cbdt.view.parameterspage.parameters.SimpleParameterComposite;

/**
 * This class is a wrapper class for the widgets for the {@link CommonConfig}
 * model class.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class CommonConfigWidgetsWrapper implements Observer {

	private SimpleParameterComposite requestedExpectedUtilitiesComposite;
	private IntegerFormatChecker integerFormatChecker;

	private Button absActionOcurrancesCheckbox;
	private Button relActionOcurrancesCheckbox;
	private Button lowestAspirationLevelsCheckbox;

	/**
	 * Constructor. Creates all widgets for the common configuration with help
	 * of the {@link CommonConfigWidgetsFactory}.
	 * 
	 * @param parent
	 */
	public CommonConfigWidgetsWrapper(Composite parent) {
		integerFormatChecker = new IntegerFormatChecker();

		CommonConfigWidgetsFactory factory = new CommonConfigWidgetsFactory();

		requestedExpectedUtilitiesComposite = factory
				.createRequestedExpectedUtilitiesWidgets(parent,
						integerFormatChecker);
		absActionOcurrancesCheckbox = factory
				.createAbsActionOccurancesCheckBox(parent);
		relActionOcurrancesCheckbox = factory
				.createRelActionOccurancesCheckBox(parent);
		lowestAspirationLevelsCheckbox = factory
				.createCalcLowestAspirationLevelsCheckBox(parent);
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
			requestedExpectedUtilitiesComposite.getText().setText(
					String.valueOf(config
							.getNumberOfRequestedExpectedUtilityValues()));
			absActionOcurrancesCheckbox.setSelection(config
					.isCalculateAbsoluteActionOccurances());
			relActionOcurrancesCheckbox.setSelection(config
					.isCalculateRelativeActionOccurances());
			lowestAspirationLevelsCheckbox.setSelection(config
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
		requestedExpectedUtilitiesComposite.getText().addModifyListener(
				new RequestedExpectedUtilityValuesModifyListener(
						commonConfigController,
						requestedExpectedUtilitiesComposite.getHintLabel(),
						integerFormatChecker));
		absActionOcurrancesCheckbox
				.addSelectionListener(new AbsoluteActionOccurancesSelectionListener(
						commonConfigController, absActionOcurrancesCheckbox));
		relActionOcurrancesCheckbox
				.addSelectionListener(new RelativeActionOccurancesSelectionListener(
						commonConfigController, relActionOcurrancesCheckbox));
		lowestAspirationLevelsCheckbox
				.addSelectionListener(new LowestAspirationLevelsSelectionListener(
						commonConfigController, lowestAspirationLevelsCheckbox));

	}

}
