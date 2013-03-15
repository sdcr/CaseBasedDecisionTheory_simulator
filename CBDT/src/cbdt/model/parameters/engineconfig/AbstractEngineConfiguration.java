package cbdt.model.parameters.engineconfig;

import java.util.Observable;

/**
 * The objects the subclasses of AstractConfiguration hold the information which
 * determines how the engine computes the simulation result. This class is part
 * of an implementation of the strategy pattern. Its subclasses, together with
 * EngineConfigchoice form the policy upon which the appropriate simulation
 * algorithm is chosen.
 * 
 * @author S-lenovo
 */
public abstract class AbstractEngineConfiguration extends Observable {

	private int requestedExpectedUtilityValues;

	public abstract String getName();

	public int getRequestedExpectedUtilityValues() {
		return requestedExpectedUtilityValues;
	}

	public void setRequestedExpectedUtilityValues(
			int requestedExpectedUtilityValues) {
		this.requestedExpectedUtilityValues = requestedExpectedUtilityValues;
	}

}
