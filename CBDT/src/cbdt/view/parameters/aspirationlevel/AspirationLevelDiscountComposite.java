package cbdt.view.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.ParametersController;
import cbdt.model.Parameters;
import cbdt.view.parameters.aspirationlevel.listeners.AspirationLevelDiscountModifyListener;

public class AspirationLevelDiscountComposite extends SimpleParameterComposite{

	public AspirationLevelDiscountComposite(Composite parent, ParametersController controller) {
		super(parent);
		getText().addModifyListener(new AspirationLevelDiscountModifyListener(controller, 
				getHintLabel()));
		getText().setText("0.9");
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Parameters){
			Parameters params = (Parameters)o;
			getText().setText(String.valueOf(params.getWeightingFactorAlpha()));
		}
	}
	
}
