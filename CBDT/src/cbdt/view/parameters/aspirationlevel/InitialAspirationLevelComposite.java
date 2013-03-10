package cbdt.view.parameters.aspirationlevel;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.Controller;
import cbdt.view.parameters.aspirationlevel.listeners.InitialAspirationLevelModifyListener;

public class InitialAspirationLevelComposite extends SimpleParameterComposite {

	public InitialAspirationLevelComposite(Composite parent, Controller controller) {
		super(parent);
		getText().addModifyListener(new InitialAspirationLevelModifyListener(controller, 
				getHintLabel()));
		getText().setText("100");
	}

}
