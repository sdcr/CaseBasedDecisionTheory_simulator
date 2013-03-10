package cbdt.view.parameters.aspirationlevel;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.ParametersController;
import cbdt.model.Parameters;
import cbdt.view.parameters.aspirationlevel.listeners.InitialAspirationLevelModifyListener;

public class InitialAspirationLevelComposite extends SimpleParameterComposite {

	public InitialAspirationLevelComposite(Composite parent, ParametersController controller) {
		super(parent);
		getText().addModifyListener(new InitialAspirationLevelModifyListener(controller, 
				getHintLabel()));
		getText().setText("100");
	}
	
	@Override
	public void update(Observable o, Object arg) {
		if(o instanceof Parameters){
			Parameters params = (Parameters)o;
			getText().setText(String.valueOf(params.getInitialAspirationLevel()));
		}
	}

}
