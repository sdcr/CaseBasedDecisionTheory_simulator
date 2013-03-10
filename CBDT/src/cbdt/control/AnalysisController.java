package cbdt.control;

import cbdt.view.analysis.AnalysisPageFactory;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;

public class AnalysisController implements IPageController {

	@Override
	public ISimulationPluginPageFactory getPageFactory() {
		return new AnalysisPageFactory();
	}

	
}
