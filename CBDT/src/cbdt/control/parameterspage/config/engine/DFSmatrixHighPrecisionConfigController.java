package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;

public class DFSmatrixHighPrecisionConfigController implements IEngineConfigController {

	private DFSmatrixHighPrecEngineConfig config;

	@Override
	public void setEngineConfigModel(AbstractEngineConfig engineConfig) {
		config = (DFSmatrixHighPrecEngineConfig)engineConfig;
	}
	
	public void setRequiredDecimalPlaces(int numOfDecimalPlaces) {
		config.setNumberOfDecimalPlaces(numOfDecimalPlaces);
	}

}
