package cbdt.view.parameterspage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.view.CBDTHeaderComposite;

/**
 * This class wraps the {@link ParametersAndConfigPageComposite}. It is an
 * interaction point with the simulation frame software.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ParametersAndConfigPageWrapper extends AbstractPluginPageWrapper {

	/**
	 * The wrapped {@link ParametersAndConfigPageComposite}.
	 */
	private ParametersAndConfigPageComposite parametersConfigPageComposite;

	/**
	 * The controller for the wrapped {@link ParametersAndConfigPageComposite}.
	 */
	private ParametersConfigPageController parametersConfigPageController;

	/**
	 * The constructor.
	 * 
	 * @param controller
	 *            The controller for the wrapped
	 *            {@link ParametersAndConfigPageComposite}.
	 */
	public ParametersAndConfigPageWrapper(
			ParametersConfigPageController controller) {
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
		parametersConfigPageComposite = new ParametersAndConfigPageComposite(
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
	 * @return Returns the wrapped {@link ParametersAndConfigPageComposite}.
	 */
	public ParametersAndConfigPageComposite getParametersPage() {
		return parametersConfigPageComposite;
	}
}
