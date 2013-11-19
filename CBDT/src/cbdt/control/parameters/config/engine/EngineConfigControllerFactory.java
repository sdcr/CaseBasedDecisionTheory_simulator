package cbdt.control.parameters.config.engine;

import cbdt.model.parameters.engineconfig.AbstractEngineConfig;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.parameters.engineconfig.DFSmatrixEngineConfig;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;

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
