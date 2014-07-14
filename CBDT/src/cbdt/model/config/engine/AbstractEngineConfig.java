package cbdt.model.config.engine;

import java.util.Observable;

/**
 * The subclasses of this class model the engine configuration how the
 * simulation is computed. This class is part of a strategy pattern. According
 * to the implementing class the correct algorithm for simulation computation is
 * to be selected.
 * 
 * @author Stephan da Costa Ribeiro
 */
public abstract class AbstractEngineConfig extends Observable {

	/**
	 * @return The name of the modeled engine configuration.
	 */
	public abstract String getName();
}
