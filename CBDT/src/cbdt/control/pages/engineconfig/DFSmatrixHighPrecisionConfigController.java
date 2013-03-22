package cbdt.control.pages.engineconfig;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;

public class DFSmatrixHighPrecisionConfigController implements AbstractEngineConfigController {

	private DFSmatrixHighPrecEngineConfig config;

	@Override
	public void setEngineConfigModel(AbstractEngineConfiguration engineConfig) {
		config = (DFSmatrixHighPrecEngineConfig)engineConfig;
	}
	
	public void setRequiredDecimalPlaces(int numOfDecimalPlaces) {
		config.setNumberOfDecimalPlaces(numOfDecimalPlaces);
	}

}
