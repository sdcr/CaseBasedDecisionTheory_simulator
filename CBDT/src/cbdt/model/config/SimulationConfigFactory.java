package cbdt.model.config;

//GREEN
import java.util.ArrayList;
import java.util.List;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.config.engine.DFSmatrixEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;

/**
 * A factory class which is able to produce a default {@link SimulationConfig}
 * object.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SimulationConfigFactory {

	/**
	 * Instantiates a default {@link SimulationConfig} object, as well as the
	 * three implemented subclasses of {@link AbstractEngineConfig}s and a
	 * default {@link CommonConfig} object.
	 * 
	 * @return The instantiated {@link SimulationConfig} object.
	 * 
	 */
	public SimulationConfig getDefaultSimulationConfig() {
		SimulationConfig defaultSimConfig = new SimulationConfig();

		List<AbstractEngineConfig> availableEngineConfigs = new ArrayList<AbstractEngineConfig>();
		availableEngineConfigs.add(new DFSkeepTreeEngineConfig());
		availableEngineConfigs.add(new DFSmatrixEngineConfig());
		availableEngineConfigs.add(new DFSmatrixHighPrecEngineConfig());
		defaultSimConfig.addAvailableEngineConfigs(availableEngineConfigs);

		defaultSimConfig.setCurrentlyChosenEngineConfig(availableEngineConfigs
				.get(0));
		defaultSimConfig.setCommonConfig(new CommonConfig());
		return defaultSimConfig;
	}
}
