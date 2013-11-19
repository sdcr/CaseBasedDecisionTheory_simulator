package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;

public class EngineConfigChoiceFactory {

	public ConfigChoice getDefaultConfigChoice() {
		ConfigChoice defaultChoice = new ConfigChoice();

		List<AbstractEngineConfiguration> configs = new ArrayList<AbstractEngineConfiguration>();
		configs.add(new DFSkeepTreeEngineConfig());
		configs.add(new DFSmatrixEngineConfig());
		configs.add(new DFSmatrixHighPrecEngineConfig());
		
		defaultChoice.addAvailableConfigs(configs);

		defaultChoice.setCurrentlyChoosenConfig(configs.get(0));
		defaultChoice.setCommonSimulationConfig(new CommonSimulationConfiguration());

		return defaultChoice;
	}
}
