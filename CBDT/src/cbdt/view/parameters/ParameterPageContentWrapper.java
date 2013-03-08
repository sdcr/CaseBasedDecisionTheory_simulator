package cbdt.view.parameters;


import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;
import cbdt.controller.Controller;
import cbdt.view.CBDTHeaderComposite;

/**
 * This class allows the connection between the simulation frame and the parameters view of the CBDT simulation plugin.
 * It manages the creation of the view content for the parameter input.
 * 
 * @author S-lenovo
 */
public class ParameterPageContentWrapper implements ISimulationPluginPageFactory{

	private ParametersComposite parameterComposite;
	private Controller controller;
	
	public ParameterPageContentWrapper(Controller controller) {
		this.controller = controller;
	}
	
	@Override
	public String getName() {
		return "Parameter-Eingabe";
	}

	@Override
	public Composite getPageComposite(Composite parent) {
		Composite cbdtFrameComposite = new CBDTHeaderComposite(parent, SWT.NONE| SWT.BORDER);		
		parameterComposite = new ParametersComposite(cbdtFrameComposite, SWT.NONE, controller);
		parameterComposite.setParametersModel(controller.getParametersModel());
		
		return cbdtFrameComposite;
	}

}
