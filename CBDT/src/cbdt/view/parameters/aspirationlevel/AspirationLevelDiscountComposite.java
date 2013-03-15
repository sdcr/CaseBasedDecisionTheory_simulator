package cbdt.view.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.ParametersPageController;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameters.aspirationlevel.listeners.AspirationLevelDiscountModifyListener;

public class AspirationLevelDiscountComposite extends
		AbstractAspirationLevelParameterComposite {

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
