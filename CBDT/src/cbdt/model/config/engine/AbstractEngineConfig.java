package cbdt.model.config.engine;

import java.util.Observable;

/**
 * The subclasses of this class model the configuration
 * how the simulation is computed. This class is part of a strategy 
 * pattern to select the algorithm for simulation computation. 
 * 
 * @author S-lenovo
 */
public abstract class AbstractEngineConfig extends Observable {

	/**
	 * @return The name of the engine modeled.
	 */
	public abstract String getName();
}
