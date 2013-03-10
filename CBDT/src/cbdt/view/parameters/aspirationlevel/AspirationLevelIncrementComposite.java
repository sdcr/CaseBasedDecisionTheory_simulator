package cbdt.view.parameters.aspirationlevel;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.ParametersController;
import cbdt.view.parameters.aspirationlevel.listeners.AspirationLevelIncrementModifyListener;

public class AspirationLevelIncrementComposite extends SimpleParameterComposite {

	public AspirationLevelIncrementComposite (Composite parent, ParametersController controller) {
		super(parent);
		getText().addModifyListener(new AspirationLevelIncrementModifyListener(controller, 
				getHintLabel()));
		getText().setText("10");
	}


}
