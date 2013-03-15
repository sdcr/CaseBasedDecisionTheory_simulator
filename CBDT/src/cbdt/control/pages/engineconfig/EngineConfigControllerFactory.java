package cbdt.control.pages.engineconfig;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;

public class EngineConfigControllerFactory {

	public AbstractEngineConfigController getConfigController(
			AbstractEngineConfiguration config) {
		if (config instanceof DFSkeepTreeEngineConfig)
			return new NaiveConfigController();
		return null;
	}
}
