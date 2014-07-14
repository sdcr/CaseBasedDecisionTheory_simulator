package cbdt.view.parameterspage.config.engine.dfsmatrixhp;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.validators.IntegerFormatChecker;
import cbdt.view.parameterspage.config.ConfigBlockTitleLabelFactory;
import cbdt.view.parameterspage.parameters.SimpleParameterComposite;
import cbdt.view.parameterspage.parameters.aspirationlevel.SimpleParameterHintLabelWrapper;

/**
 * A factory class which is able to create the widgets for the
 * DFSmatrixHighPrecision engine configuration.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSmatrixHighPrecisionConfigWidgetsFactory {

	/**
	 * Creates and returns a {@link SimpleParameterComposite} with which the
	 * number of required places after the comma can be specified by th user.
	 * 
	 * @param parent
	 * @param integerFormatChecker
	 * @return
	 */
	public SimpleParameterComposite createRequiredDecimalPlacesComposite(
			Composite parent, IntegerFormatChecker integerFormatChecker) {
		SimpleParameterComposite requiredDecimalPlacesComposite = new SimpleParameterComposite(
				parent);
		requiredDecimalPlacesComposite
				.setHintLabel(new SimpleParameterHintLabelWrapper(
						requiredDecimalPlacesComposite));
		requiredDecimalPlacesComposite
				.setToolTipText("The value must be a natural number");

		requiredDecimalPlacesComposite
				.setNumberFormatChecker(integerFormatChecker);
		return requiredDecimalPlacesComposite;
	}

	/**
	 * Creates and returns a {@link Label} for the configuration of required
	 * precision.
	 * 
	 * @param parent
	 * @return
	 */
	public Label createRequiredDecimalPlacesLabel(Composite parent) {
		Label reqDecimalPlacesLabel = new Label(parent, SWT.NONE);
		GridData labelLayoutData = new GridData();

		labelLayoutData.horizontalIndent = ConfigBlockTitleLabelFactory.CONFIG_BLOCK_H_INDENT;
		reqDecimalPlacesLabel.setLayoutData(labelLayoutData);
		reqDecimalPlacesLabel.setText("Precision in decimal places:");
		return reqDecimalPlacesLabel;
	}

}
