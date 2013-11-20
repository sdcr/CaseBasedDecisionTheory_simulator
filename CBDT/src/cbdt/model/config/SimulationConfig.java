package cbdt.model.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * This class models the configuration chosen for a simulation.
 * It encompasses a CommonConfig, the available AbstractEngineConfigs,
 * and a reference to the currently chosen engine configuration.
 * @author Stephan da Costa Ribeiro
 */
public class SimulationConfig extends Observable {

	/**
	 * The configuration data which is set for the simulation, independent from
	 * the engine used.
	 */
	private CommonConfig commonConfig;

	/**
	 * The engine configurations available.
	 */
	private List<AbstractEngineConfig> availableEngineConfigs;

	/**
	 * The engine configuration currently chosen by the user. 
	 * It must be one of the available engine configurations.
	 */
	private AbstractEngineConfig choosenEngineConfig;

	/**
	 * Constructor.
	 */
	public SimulationConfig() {
		availableEngineConfigs = new ArrayList<AbstractEngineConfig>();
	}

	public CommonConfig getCommonConfig() {
		return commonConfig;
	}	
	
	public void setCommonConfig(CommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}

	public List<AbstractEngineConfig> getAvailableEngineConfigs() {
		return availableEngineConfigs;
	}

	public void addAvailableEngineConfigs(
			List<AbstractEngineConfig> additionalConfigs) {
		availableEngineConfigs.addAll(additionalConfigs);
		setChanged();
		notifyObservers();
	}

	public AbstractEngineConfig getCurrentlyChosenEngineConfig() {
		return choosenEngineConfig;
	}

	/**
	 * Sets the currently chosen engine configuration.
	 * @param config Should be one of the available engine config objects.
	 */
	public void setCurrentlyChosenEngineConfig(AbstractEngineConfig config) {
		choosenEngineConfig = config;
		setChanged();
		notifyObservers();
	}
}
