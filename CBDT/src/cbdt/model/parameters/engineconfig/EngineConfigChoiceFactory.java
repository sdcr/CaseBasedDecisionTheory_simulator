package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;

public class EngineConfigChoiceFactory {

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
