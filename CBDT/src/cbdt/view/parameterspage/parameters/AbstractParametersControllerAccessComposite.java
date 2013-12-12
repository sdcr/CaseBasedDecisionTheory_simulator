package cbdt.view.parameterspage.parameters;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.parameters.ParametersController;

/**
 * An abstract {@link Composite} subclass which allows all extending classes to
 * easily access the {@link ParametersController}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public abstract class AbstractParametersControllerAccessComposite extends
		Composite {

	private ParametersController controller;

	/**
	 * The constructor.
	 * 
	 * @param parent
	 * @param style
	 * @param controller
	 */
	public AbstractParametersControllerAccessComposite(Composite parent,
			int style, ParametersController controller) {
		super(parent, style);
		this.controller = controller;
	}

	/**
	 * @return Returns the {@link ParametersController}.
	 */
	public ParametersController getController() {
		return controller;
	}

}
