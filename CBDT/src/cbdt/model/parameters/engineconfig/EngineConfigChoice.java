package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class EngineConfigChoice extends Observable {

	List<AbstractEngineConfiguration> availableEngineConfigs;

	AbstractEngineConfiguration choosenConfig;

	public EngineConfigChoice() {
		availableEngineConfigs = new ArrayList<AbstractEngineConfiguration>();
	}

	public List<AbstractEngineConfiguration> getAvailableEngineConfigs() {
		return availableEngineConfigs;
	}

	public void addAvailableConfigs(
			List<AbstractEngineConfiguration> additionalConfigs) {
		availableEngineConfigs.addAll(additionalConfigs);
		setChanged();
		notifyObservers();
	}

	public AbstractEngineConfiguration getCurrentlyChoosenConfig() {
		return choosenConfig;
	}

	public void setCurrentlyChoosenConfig(AbstractEngineConfiguration config) {
		choosenConfig = config;
		setChanged();
		notifyObservers();
	}
}
