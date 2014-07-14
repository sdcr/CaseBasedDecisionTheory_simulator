package cbdt.view.parameterspage.config.common;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.validators.IntegerFormatChecker;
import cbdt.view.HintLabelWrapper;
import cbdt.view.parameterspage.config.ConfigBlockTitleLabelFactory;
import cbdt.view.parameterspage.parameters.SimpleParameterComposite;

/**
 * This factory class is able to create the widgets for the common
 * configuration.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class CommonConfigWidgetsFactory {

	private Label calcActionOccurrancesLabel;

	/**
	 * Creates the checkbox for determining if the lowest aspiration level in a
	 * stage should be saved during the simulation.
	 * 
	 * @param parent
	 * @return
	 */
	public Button createCalcLowestAspirationLevelsCheckBox(Composite parent) {
		createLabel(parent, "Save aspiration levels:");
		return createCheckbox(parent, "lowest aspiration levels");
	}

	/**
	 * Creates a {@link Label} with a certain label text.
	 * 
	 * @param parent
	 * @param labelText
	 * @return
	 */
	private Label createLabel(Composite parent, String labelText) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(labelText);

		GridData labelGridData = new GridData();
		labelGridData.horizontalIndent = ConfigBlockTitleLabelFactory.CONFIG_BLOCK_H_INDENT;
		label.setLayoutData(labelGridData);
		return label;
	}

	/**
	 * Creates a checkbox {@link Button} for determining if the number of
	 * absolute action occurrences should besaved during the simulation.
	 * 
	 * @param parent
	 * @return
	 */
	public Button createAbsActionOccurancesCheckBox(Composite parent) {
		if (calcActionOccurrancesLabel == null)
			createActionOccurrencesLabel(parent);
		return createCheckbox(parent, "absolute occurrences");
	}

	/**
	 * Creates a checkbox {@link Button} for determining if the number of
	 * relative action occurrences should besaved during the simulation.
	 * 
	 * @param parent
	 * @return
	 */
	public Button createRelActionOccurancesCheckBox(Composite parent) {
		if (calcActionOccurrancesLabel == null)
			createActionOccurrencesLabel(parent);
		return createCheckbox(parent, "relative occurrences");
	}

	/**
	 * Creates a checkbox {@link Button} with a certain text.
	 * 
	 * @param parent
	 * @param checkboxText
	 * @return
	 */
	private Button createCheckbox(Composite parent, String checkboxText) {
		Button checkbox = new Button(parent, SWT.CHECK);
		checkbox.setText(checkboxText);
		return checkbox;
	}

	/**
	 * Creates the {@link Label} for the checkbox concerning action occurrences.
	 * 
	 * @param parent
	 */
	private void createActionOccurrencesLabel(Composite parent) {
		calcActionOccurrancesLabel = createLabel(parent,
				"Save action occurrences:");
		((GridData) calcActionOccurrancesLabel.getLayoutData()).verticalSpan = 2;
		((GridData) calcActionOccurrancesLabel.getLayoutData()).verticalAlignment = SWT.BEGINNING;
	}

	/**
	 * Creates a {@link SimpleParameterComposite} to allow the input of how many
	 * expected utility values are required.
	 * 
	 * @param parent
	 * @param integerFormatChecker
	 * @return
	 */
	public SimpleParameterComposite createRequestedExpectedUtilitiesWidgets(
			Composite parent, IntegerFormatChecker integerFormatChecker) {
		createLabel(parent, "Expected utility values:");

		SimpleParameterComposite requiredExpectedUtilitiesComposite = new SimpleParameterComposite(
				parent);
		requiredExpectedUtilitiesComposite.setHintLabel(new HintLabelWrapper(
				requiredExpectedUtilitiesComposite));
		requiredExpectedUtilitiesComposite
				.setToolTipText("The value must be a natural number");
		requiredExpectedUtilitiesComposite
				.setNumberFormatChecker(integerFormatChecker);
		return requiredExpectedUtilitiesComposite;
	}
}
