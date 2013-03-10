package cbdt.view.parameters.aspirationlevel;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.ParametersController;
import cbdt.view.parameters.aspirationlevel.listeners.AspirationLevelDiscountModifyListener;

public class AspirationLevelDiscountComposite extends SimpleParameterComposite{

	public AspirationLevelDiscountComposite(Composite parent, ParametersController controller) {
		super(parent);
		getText().addModifyListener(new AspirationLevelDiscountModifyListener(controller, 
				getHintLabel()));
		getText().setText("0.9");
	}


}
