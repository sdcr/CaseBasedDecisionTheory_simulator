package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.pages.ParametersController;
import cbdt.view.CBDTHeaderComposite;

/**
 * This class allows the connection between the simulation frame and the
 * parameters view of the CBDT simulation plugin. It manages the creation of the
 * view content for the parameter input.
 * 
 * @author S-lenovo
 */
public class ParametersPageWrapper extends AbstractPluginPageWrapper {

	private ParametersPage parameterComposite;
	private ParametersController controller;

	public ParametersPageWrapper(ParametersController controller) {
		this.controller = controller;
	}

	@Override
	public String getName() {
		return "Parameter-Eingabe";
	}

	@Override
	protected Composite getPageComposite(Composite parent) {
		Composite parametersPluginPage = new CBDTHeaderComposite(parent,
				SWT.NONE | SWT.BORDER);
		parameterComposite = new ParametersPage(parametersPluginPage, SWT.NONE,
				controller);
		parameterComposite.setParametersModel(controller.getParametersModel());
		parameterComposite.setConfigChoiceModel(controller
				.getConfigChoiceModel());
		return parametersPluginPage;
	}

	public ParametersPage getParametersPage() {
		return parameterComposite;
	}
}
