package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.DFSkeepTreeEngineConfig;
import cbdt.model.config.DFSmatrixEngineConfig;
import cbdt.model.config.DFSmatrixHighPrecEngineConfig;

/**
 * This factory class can produce an engine config controller according to a
 * engine config object.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class EngineConfigControllerFactory {

	/**
	 * 
	 * @param config
	 *            The engine config object for which a controller shall be
	 *            produced.
	 * @return The engine config controller which can handle the passed config
	 *         object.
	 * @throws NoEngineConfigControllerException
	 *             Is thrown, when there is no enginge config controller for the
	 *             passed object.
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
