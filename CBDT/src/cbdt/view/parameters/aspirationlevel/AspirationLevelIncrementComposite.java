package cbdt.view.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.ParametersController;
import cbdt.model.Parameters;
import cbdt.view.parameters.aspirationlevel.listeners.AspirationLevelIncrementModifyListener;

public class AspirationLevelIncrementComposite extends SimpleParameterComposite {

	public AspirationLevelIncrementComposite (Composite parent, ParametersController controller) {
		super(parent);
		getText().addModifyListener(new AspirationLevelIncrementModifyListener(controller, 
				getHintLabel()));
		getText().setText("10");
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Parameters){
			Parameters params = (Parameters)o;
			getText().setText(String.valueOf(params.getAspirationLevelIncrement()));
		}
	}
}
