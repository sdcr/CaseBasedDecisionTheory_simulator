package cbdt.view.analysispage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.control.analysispage.AnalysisPageController;
import cbdt.view.CBDTHeaderComposite;

/**
 * This class wraps the analysis page.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AnalysisPageWrapper extends AbstractPluginPageWrapper {

	/**
	 * The wrapped {@link AnalysisPageComposite}.
	 */
	private AnalysisPageComposite analysisPage;

	/**
	 * The {@link AnalysisPageController}, which handles the requests for the
	 * analysis page.
	 */
	private AnalysisPageController controller;

	/**
	 * The constructor.
	 * @param controller
	 */
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
		analysisPage = new AnalysisPageComposite(wrapperComposite, SWT.NONE,
				controller);
		wrapperComposite.pack();
		return wrapperComposite;
	}

	/**
	 * @return Returns the {@link AnalysisPageComposite} this object wraps.
	 */
	public AnalysisPageComposite getAnalysisPage() {
		return analysisPage;
	}

}
