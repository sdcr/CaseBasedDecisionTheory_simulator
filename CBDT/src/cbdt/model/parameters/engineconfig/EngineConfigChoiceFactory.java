package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;

public class EngineConfigChoiceFactory {

	public EngineConfigChoice getDefaultConfigChoice() {
		EngineConfigChoice defaultChoice = new EngineConfigChoice();

		List<AbstractEngineConfiguration> configs = new ArrayList<AbstractEngineConfiguration>();
		configs.add(new DFSkeepTreeEngineConfig());
		configs.add(new DFSmatrixEngineConfig());
		configs.add(new DFSmatrixHighPrecEngineConfig());
		
		defaultChoice.addAvailableConfigs(configs);

		defaultChoice.choosenConfig = configs.get(0);

		return defaultChoice;
	}
}
