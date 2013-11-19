package cbdt.model.parameters.engineconfig;

import java.util.Observable;

/**
 * The subclasses of this class model the configuration
 * how the simulation is computed. 
 * This class is part of a strategy pattern. 
 * 
 * @author S-lenovo
 */
public abstract class AbstractEngineConfiguration extends Observable {

	/**
	 * @return The name of the engine modeled.
	 */
	public abstract String getName();
}
