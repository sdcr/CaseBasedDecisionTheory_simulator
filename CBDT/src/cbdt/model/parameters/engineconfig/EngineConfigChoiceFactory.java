package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;

public class EngineConfigChoiceFactory {

	public EngineConfigChoice getDefaultConfigChoice(){
		EngineConfigChoice defaultChoice = new EngineConfigChoice();
		
		List<AbstractEngineConfiguration> configs = new ArrayList<AbstractEngineConfiguration>();  
		configs.add(new NaiveEngineConfig());
		defaultChoice.addAvailableConfigs(configs);
		return defaultChoice;
	}
}
