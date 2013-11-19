package cbdt.model.parameters.engineconfig;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * This class models the configuration chosen for a simulation.
 * It encompasses a CommonSimulationConfiguration, the available AbstractEngineConfigurations,
 * and a reference to the currently chosen engine configuration.
 * @author Stephan da Costa Ribeiro
 */
public class SimulationConfig extends Observable {

	/**
	 * The configuration data which is set for the simulation, independent from
	 * the engine used.
	 */
	private CommonConfig commonSimConfig;

	/**
	 * The engine configurations available.
	 */
	private List<AbstractEngineConfig> availableEngineConfigs;

	/**
	 * The engine configuration currently chosen by the user. 
	 * It must be one of the available engine configurations.
	 */
	private AbstractEngineConfig choosenEngineConfig;

	public CommonConfig getCommonSimulationConfig() {
		return commonSimConfig;
	}
	
	public void setCommonSimulationConfig(CommonConfig commonConfig) {
		this.commonSimConfig = commonConfig;
	}

	/**
	 * Constructor.
	 */
	public SimulationConfig() {
		availableEngineConfigs = new ArrayList<AbstractEngineConfig>();
	}

	public List<AbstractEngineConfig> getAvailableEngineConfigs() {
		return availableEngineConfigs;
	}

	public void addAvailableConfigs(
			List<AbstractEngineConfig> additionalConfigs) {
		availableEngineConfigs.addAll(additionalConfigs);
		setChanged();
		notifyObservers();
	}

	public AbstractEngineConfig getCurrentlyEngineChoosenConfig() {
		return choosenEngineConfig;
	}

	public void setCurrentlyChoosenEngineConfig(AbstractEngineConfig config) {
		choosenEngineConfig = config;
		setChanged();
		notifyObservers();
	}
}
