package cbdt.control.pages;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.view.analysis.AnalysisPageFactory;

public class AnalysisPageController extends AbstractPageController {

	@Override
	public AbstractPluginPageWrapper getPageWrapper() {
		return new AnalysisPageFactory();
	}

}
