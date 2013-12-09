package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.config.engine.DFSmatrixEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;

/**
 * This factory class produces the appropriate {@link IEngineConfigController}
 * for an {@link AbstractEngineConfig} object.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class EngineConfigControllerFactory {

	/**
	 * @param config
	 *            The {@link AbstractEngineConfig} object for which a controller
	 *            shall be produced.
	 * @return The {@link IEngineConfigController} which can handle requests for
	 *         the passed config object.
	 * @throws NoEngineConfigControllerException
	 *             Is thrown, when there is no {@link IEngineConfigController}
	 *             for the passed object.
	 */
	public IEngineConfigController getConfigController(
			AbstractEngineConfig config)
			throws NoEngineConfigControllerException {
		if (config instanceof DFSkeepTreeEngineConfig)
			return new DFSkeepTreeConfigController();
		if (config instanceof DFSmatrixEngineConfig)
			return new DFSmatrixConfigController();
		if (config instanceof DFSmatrixHighPrecEngineConfig)
			return new DFSmatrixHighPrecisionConfigController();
		throw new NoEngineConfigControllerException(
				"There is no controller defined for this engine configuration in the engine config controller factory.");
	}
}
