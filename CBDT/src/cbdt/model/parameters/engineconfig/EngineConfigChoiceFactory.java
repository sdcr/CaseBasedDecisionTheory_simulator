package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;

public class EngineConfigChoiceFactory {

	public SimulationConfig getDefaultConfigChoice() {
		SimulationConfig defaultChoice = new SimulationConfig();

		List<AbstractEngineConfig> configs = new ArrayList<AbstractEngineConfig>();
		configs.add(new DFSkeepTreeEngineConfig());
		configs.add(new DFSmatrixEngineConfig());
		configs.add(new DFSmatrixHighPrecEngineConfig());
		
		defaultChoice.addAvailableConfigs(configs);

		defaultChoice.setCurrentlyChoosenEngineConfig(configs.get(0));
		defaultChoice.setCommonSimulationConfig(new CommonConfig());

		return defaultChoice;
	}
}
