package cbdt.view.parameters;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.ParametersPageController;

public abstract class AbstractControllerAccessComposite extends Composite {

	private ParametersPageController controller;

	public AbstractControllerAccessComposite(Composite parent, int style,
			ParametersPageController controller) {
		super(parent, style);
		this.controller = controller;
	}

	public ParametersPageController getController() {
		return controller;
	}

}
