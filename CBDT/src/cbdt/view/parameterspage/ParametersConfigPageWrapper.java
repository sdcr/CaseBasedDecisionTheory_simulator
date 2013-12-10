package cbdt.view.parameterspage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.view.CBDTHeaderComposite;

/**
 * This class wraps the {@link ParametersConfigPageComposite}.
 * 
 * @author S-lenovo
 */
public class ParametersConfigPageWrapper extends AbstractPluginPageWrapper {

	/**
	 * The wrapped {@link ParametersConfigPageComposite}.
	 */
	private ParametersConfigPageComposite parametersConfigPageComposite;

	/**
	 * The controller for the wrapped {@link ParametersConfigPageComposite}.
	 */
	private ParametersConfigPageController parametersConfigPageController;

	/**
	 * The constructor.
	 * 
	 * @param controller
	 *            The controller for the wrapped
	 *            {@link ParametersConfigPageComposite}.
	 */
	public ParametersConfigPageWrapper(ParametersConfigPageController controller) {
		this.parametersConfigPageController = controller;
	}

	@Override
	public String getName() {
		return "Parameter values";
	}

	@Override
	protected Composite getPageComposite(Composite parent) {
		Composite cbdtHeaderComposite = new CBDTHeaderComposite(parent,
				SWT.NONE);
		parametersConfigPageComposite = new ParametersConfigPageComposite(
				cbdtHeaderComposite, SWT.NONE, parametersConfigPageController);

		// initialize with current model
		parametersConfigPageComposite
				.setParametersModel(parametersConfigPageController
						.getParametersController().getParametersModel());
		parametersConfigPageComposite
				.setSimulationConfigModel(parametersConfigPageController
						.getSimulationConfig());

		cbdtHeaderComposite.layout();
		return cbdtHeaderComposite;
	}

	/**
	 * @return Returns the wrapped {@link ParametersConfigPageComposite}.
	 */
	public ParametersConfigPageComposite getParametersPage() {
		return parametersConfigPageComposite;
	}
}
