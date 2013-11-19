package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.DFSmatrixHighPrecEngineConfig;

public class DFSmatrixHighPrecisionConfigController implements AbstractEngineConfigController {

	private DFSmatrixHighPrecEngineConfig config;

	@Override
	public void setEngineConfigModel(AbstractEngineConfig engineConfig) {
		config = (DFSmatrixHighPrecEngineConfig)engineConfig;
	}
	
	public void setRequiredDecimalPlaces(int numOfDecimalPlaces) {
		config.setNumberOfDecimalPlaces(numOfDecimalPlaces);
	}

}
