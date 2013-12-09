package cbdt.view.analysispage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.analysispage.AnalysisPageController;
import cbdt.view.CBDTHeaderComposite;

public class AnalysisPageWrapper extends AbstractPluginPageWrapper{

	private AnalysisPageComposite analysisPage;
	private AnalysisPageController controller;
	
	public AnalysisPageWrapper(AnalysisPageController controller) {
		this.controller = controller;
	}

	@Override
	public String getName() {
		return "Result analysis";
	}
	
	@Override
	public Composite getPageComposite(Composite parent) {
		Composite wrapperComposite = new CBDTHeaderComposite(parent, SWT.NONE);
		analysisPage = new AnalysisPageComposite(wrapperComposite, SWT.NONE, controller);
		wrapperComposite.pack();
		return wrapperComposite;
	}

	public AnalysisPageComposite getAnalysisPage() {
		return analysisPage;
	}



}
