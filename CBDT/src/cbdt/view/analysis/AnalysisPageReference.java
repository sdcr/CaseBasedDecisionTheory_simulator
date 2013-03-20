package cbdt.view.analysis;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.view.CBDTHeaderComposite;

public class AnalysisPageReference extends AbstractPluginPageWrapper{

	private AnalysisPage analysisPage;
	

	@Override
	public String getName() {
		return "Result analysis";
	}
	
	@Override
	public Composite getPageComposite(Composite parent) {
		Composite wrapperComposite = new CBDTHeaderComposite(parent, SWT.NONE);
		analysisPage = new AnalysisPage(wrapperComposite, SWT.NONE);
		wrapperComposite.pack();
		return wrapperComposite;
	}

	public AnalysisPage getAnalysisPage() {
		return analysisPage;
	}



}
