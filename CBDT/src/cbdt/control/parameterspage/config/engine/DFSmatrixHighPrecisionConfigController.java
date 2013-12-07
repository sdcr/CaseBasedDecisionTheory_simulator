package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;

/**
 * The config controller for the {@link DFSmatrixHighPrecEngineConfig}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSmatrixHighPrecisionConfigController implements
		IEngineConfigController {

	private DFSmatrixHighPrecEngineConfig config;

	@Override
	public void setEngineConfigModel(AbstractEngineConfig engineConfig) {
		config = (DFSmatrixHighPrecEngineConfig) engineConfig;
	}

	/**
	 * Set the simulation precision in terms of the number of digits after the
	 * comma.
	 * 
	 * @param numOfDecimalPlaces
	 */
	public void setRequiredDecimalPlaces(int numOfDecimalPlaces) {
		config.setNumberOfDecimalPlaces(numOfDecimalPlaces);
	}

}
