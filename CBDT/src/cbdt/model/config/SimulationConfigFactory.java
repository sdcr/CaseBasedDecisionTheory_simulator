package cbdt.model.config;

import java.util.ArrayList;
import java.util.List;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.config.engine.DFSmatrixEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;

public class SimulationConfigFactory {

	public SimulationConfig getDefaultSimulationConfig() {
		SimulationConfig defaultSimConfig = new SimulationConfig();

		List<AbstractEngineConfig> availableEngineConfigs = new ArrayList<AbstractEngineConfig>();
		availableEngineConfigs.add(new DFSkeepTreeEngineConfig());
		availableEngineConfigs.add(new DFSmatrixEngineConfig());
		availableEngineConfigs.add(new DFSmatrixHighPrecEngineConfig());
		defaultSimConfig.addAvailableEngineConfigs(availableEngineConfigs);

		defaultSimConfig.setCurrentlyChosenEngineConfig(availableEngineConfigs.get(0));
		defaultSimConfig.setCommonConfig(new CommonConfig());
		return defaultSimConfig;
	}
}
