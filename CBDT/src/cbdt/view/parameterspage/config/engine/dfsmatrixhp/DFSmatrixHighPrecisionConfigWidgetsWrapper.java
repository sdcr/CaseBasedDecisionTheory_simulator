package cbdt.view.parameterspage.config.engine.dfsmatrixhp;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.parameterspage.config.engine.DFSmatrixHighPrecisionConfigController;
import cbdt.control.parameterspage.config.engine.IEngineConfigController;
import cbdt.control.validators.IntegerFormatChecker;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;
import cbdt.view.parameterspage.config.engine.AbstractEngineConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.engine.dfsmatrixhp.listeners.RequiredDecimalPlacesModifyListener;
import cbdt.view.parameterspage.parameters.SimpleParameterComposite;

/**
 * The {@link AbstractEngineConfigWidgetsWrapper} subclass for the
 * DFSmatrixHighPrecision algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSmatrixHighPrecisionConfigWidgetsWrapper extends
		AbstractEngineConfigWidgetsWrapper {

	private Label reqDecimalPlacesLabel;
	private SimpleParameterComposite requiredDecimalPlacesComposite;
	private IntegerFormatChecker integerFormatChecker;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 */
	public DFSmatrixHighPrecisionConfigWidgetsWrapper(Composite parent) {
		integerFormatChecker = new IntegerFormatChecker();

		DFSmatrixHighPrecisionConfigWidgetsFactory factory = new DFSmatrixHighPrecisionConfigWidgetsFactory();
		reqDecimalPlacesLabel = factory
				.createRequiredDecimalPlacesLabel(parent);
		requiredDecimalPlacesComposite = factory
				.createRequiredDecimalPlacesComposite(parent,
						integerFormatChecker);
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
	public void setEngineConfigController(
			IEngineConfigController configController) {
		DFSmatrixHighPrecisionConfigController customController = (DFSmatrixHighPrecisionConfigController) configController;
		requiredDecimalPlacesComposite.getText().addModifyListener(
				new RequiredDecimalPlacesModifyListener(customController,
						requiredDecimalPlacesComposite.getHintLabel(),
						integerFormatChecker));
	}

	@Override
	public boolean hasContentToShow() {
		return true;
	}

}
