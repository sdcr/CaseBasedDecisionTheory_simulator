package cbdt.model.config;

//GREEN
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * This class models the complete configuration for a simulation. It encompasses
 * a {@link CommonConfig}, the available {@link AbstractEngineConfig}s, and a
 * reference to the currently chosen {@link AbstractEngineConfig}.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class SimulationConfig extends Observable {

	/**
	 * The {@link CommonConfig} which is set for the simulation. It is
	 * independent from the engine used.
	 */
	private CommonConfig commonConfig;

	/**
	 * The {@link AbstractEngineConfig}s available.
	 */
	private List<AbstractEngineConfig> availableEngineConfigs;

	/**
	 * The {@link AbstractEngineConfig} currently chosen by the user. It must be
	 * one of the available engine configurations.
	 */
	private AbstractEngineConfig choosenEngineConfig;

	/**
	 * Constructor.
	 */
	public SimulationConfig() {
		availableEngineConfigs = new ArrayList<AbstractEngineConfig>();
	}

	/**
	 * @return The current {@link CommonConfig} object.
	 */
	public CommonConfig getCommonConfig() {
		return commonConfig;
	}

	/**
	 * @param commonConfig
	 */
	public void setCommonConfig(CommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}

	/**
	 * @return A list of {@link AbstractEngineConfig}s which are available to
	 *         choose from.
	 */
	public List<AbstractEngineConfig> getAvailableEngineConfigs() {
		return availableEngineConfigs;
	}

	/**
	 * @param additionalConfigs
	 *            The elements of the passed list are added to the list of
	 *            {@link AbstractEngineConfig}s.
	 */
	public void addAvailableEngineConfigs(
			List<AbstractEngineConfig> additionalConfigs) {
		availableEngineConfigs.addAll(additionalConfigs);
		setChanged();
		notifyObservers();
	}

	/**
	 * @return Returns the {@link AbstractEngineConfig} which is currently
	 *         chosen by the user.
	 */
	public AbstractEngineConfig getCurrentlyChosenEngineConfig() {
		return choosenEngineConfig;
	}

	/**
	 * Sets the currently chosen engine configuration.
	 * 
	 * @param config
	 *            Should be one of the objects in the list of available
	 *            {@link AbstractEngineConfig}s.
	 */
	public void setCurrentlyChosenEngineConfig(AbstractEngineConfig config) {
		choosenEngineConfig = config;
		setChanged();
		notifyObservers();
	}
}
