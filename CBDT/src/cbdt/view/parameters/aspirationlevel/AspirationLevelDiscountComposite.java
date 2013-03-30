package cbdt.view.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.ParametersPageController;
import cbdt.control.validators.DoubleFormatChecker;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameters.aspirationlevel.listeners.AspirationLevelDiscountModifyListener;

public class AspirationLevelDiscountComposite extends
		AbstractAspirationLevelParameterComposite {

	public static final String TOOL_TIP_TEXT = "Determines weighting\nbetween prev. asp. level\nand max. aver. utility.";
	
	public AspirationLevelDiscountComposite(Composite parent,
			ParametersPageController controller) {
		super(parent);
		DoubleFormatChecker doubleFormatChecker = new DoubleFormatChecker();
		this.setNumberFormatChecker(doubleFormatChecker);
		getText().addModifyListener(
				new AspirationLevelDiscountModifyListener(controller,
						getHintLabel(), doubleFormatChecker));
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof Parameters) {
			Parameters params = (Parameters) o;
			getText().setText(String.valueOf(params.getWeightingFactorAlpha()));
		}
	}

}
