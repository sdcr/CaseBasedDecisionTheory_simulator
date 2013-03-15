package cbdt.view.parameters;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.ParametersController;

public abstract class AbstractControllerAccessComposite extends Composite {

	private ParametersController controller;

	public AbstractControllerAccessComposite(Composite parent, int style,
			ParametersController controller) {
		super(parent, style);
		this.controller = controller;
	}

	public ParametersController getController() {
		return controller;
	}

}
