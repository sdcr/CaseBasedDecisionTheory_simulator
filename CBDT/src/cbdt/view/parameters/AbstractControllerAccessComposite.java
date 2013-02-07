package cbdt.view.parameters;

import org.eclipse.swt.widgets.Composite;

import cbdt.controller.Controller;

public abstract class AbstractControllerAccessComposite extends Composite {

	private Controller controller;

	public AbstractControllerAccessComposite(Composite parent, int style, Controller controller) {
		super(parent, style);
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}
	
}
