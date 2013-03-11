package cbdt.control;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.model.Result;
import cbdt.view.analysis.AnalysisPageFactory;

public class AnalysisController extends AbstractPageController {

	@Override
	public AbstractPluginPageWrapper getPageWrapper() {
		return new AnalysisPageFactory();
	}

	public void showResult(Result simulationResult){
		
	}
}
