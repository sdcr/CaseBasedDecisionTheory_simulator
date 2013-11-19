package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This class models the configuration chosen for a simulation.
 * @author Stephan da Costa Ribeiro
 */
public class ConfigChoice extends Observable {

	/**
	 * The configuration data which is set for the simulation, independent from
	 * the engine used.
	 */
	private CommonSimulationConfiguration commonSimConfig;

	/**
	 * The engine configurations available.
	 */
	private List<AbstractEngineConfiguration> availableEngineConfigs;

	/**
	 * The engine configuration currently chosen by the user. 
	 * It must be one of the available engine configurations.
	 */
	private AbstractEngineConfiguration choosenEngineConfig;

	public CommonSimulationConfiguration getCommonSimulationConfig() {
		return commonSimConfig;
	}
	
	public void setCommonSimulationConfig(CommonSimulationConfiguration commonConfig) {
		this.commonSimConfig = commonConfig;
	}

	/**
	 * Constructor.
	 */
	public ConfigChoice() {
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
		return choosenEngineConfig;
	}

	public void setCurrentlyChoosenConfig(AbstractEngineConfiguration config) {
		choosenEngineConfig = config;
		setChanged();
		notifyObservers();
	}
}
