package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.DFSkeepTreeEngineConfig;
import cbdt.model.config.DFSmatrixEngineConfig;
import cbdt.model.config.DFSmatrixHighPrecEngineConfig;

public class EngineConfigControllerFactory {

	public AbstractEngineConfigController getConfigController(
			AbstractEngineConfig config) throws NoEngineConfigControllerException {
		if (config instanceof DFSkeepTreeEngineConfig)
			return new DFSkeepTreeConfigController();
		if (config instanceof DFSmatrixEngineConfig)
			return new DFSmatrixConfigController();
		if (config instanceof DFSmatrixHighPrecEngineConfig)
			return new DFSmatrixHighPrecisionConfigController();
		throw new NoEngineConfigControllerException("There is no controller defined for this engine configuration in the engine config controller factory.");
	}
}
