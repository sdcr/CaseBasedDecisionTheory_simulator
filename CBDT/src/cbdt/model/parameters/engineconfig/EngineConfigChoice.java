package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class EngineConfigChoice extends Observable {

	private CommonEngineConfiguration commonConfig;

	private List<AbstractEngineConfiguration> availableEngineConfigs;

	private AbstractEngineConfiguration choosenConfig;

	public CommonEngineConfiguration getCommonConfig() {
		return commonConfig;
	}
	
	public void setCommonConfig(CommonEngineConfiguration commonConfig) {
		this.commonConfig = commonConfig;
	}

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
