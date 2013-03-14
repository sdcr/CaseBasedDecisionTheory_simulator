package cbdt.control.engineconfig;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.NaiveEngineConfig;

public class EngineConfigControllerFactory {

	public AbstractEngineConfigController getConfigController(AbstractEngineConfiguration config){
		if(config instanceof NaiveEngineConfig)
			return new NaiveConfigController();
		return null;
	}
}
