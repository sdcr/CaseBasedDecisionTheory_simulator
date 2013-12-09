package cbdt.view.parameterspage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.view.CBDTHeaderComposite;

/**
 * This class allows the connection between the simulation frame and the
 * parameters view of the CBDT simulation plugin. It manages the creation of the
 * view content for the parameter input.
 * 
 * @author S-lenovo
 */
public class ParametersConfigPageWrapper extends AbstractPluginPageWrapper {

	private ParametersConfigPageComposite parametersConfigPageComposite;
	private ParametersConfigPageController parametersConfigPageController;

	public ParametersConfigPageWrapper(ParametersConfigPageController controller) {
		this.parametersConfigPageController = controller;
	}

	@Override
	public String getName() {
		return "Parameter values";
	}

	@Override
	protected Composite getPageComposite(Composite parent) {
		Composite cbdtHeaderComposite = new CBDTHeaderComposite(parent, SWT.NONE);
		parametersConfigPageComposite = new ParametersConfigPageComposite(cbdtHeaderComposite, SWT.NONE,
				parametersConfigPageController);
		
		//initialize with current model
		parametersConfigPageComposite.setParametersModel(parametersConfigPageController.
				getParametersController().getParametersModel());
		parametersConfigPageComposite.setSimulationConfigModel(parametersConfigPageController
				.getSimulationConfig());

		cbdtHeaderComposite.layout();
		return cbdtHeaderComposite;
	}

	public ParametersConfigPageComposite getParametersPage() {
		return parametersConfigPageComposite;
	}
}
