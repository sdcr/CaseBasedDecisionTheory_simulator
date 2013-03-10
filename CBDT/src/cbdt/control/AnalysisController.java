package cbdt.control;

import cbdt.view.analysis.AnalysisPageFactory;
import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;
import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;

public class AnalysisController implements IPageController {

	@SuppressWarnings("unused")
	private IForegroundManager foregroundManager;

	public AnalysisController(IForegroundManager foregroundManager) {
		this.foregroundManager = foregroundManager;
	}

	@Override
	public AbstractPluginPageCompositeWrapper getPageWrapper() {
		return new AnalysisPageFactory();
	}

	
}
