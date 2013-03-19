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

	private boolean calculateAbsoluteActionOccurances;
	
	private boolean calculateRelativeActionOccurances;
	
	public abstract String getName();

	public boolean isCalculateAbsoluteActionOccurances() {
		return calculateAbsoluteActionOccurances;
	}

	public void setCalculateAbsoluteActionOccurances(
			boolean calculateAbsoluteActionOccurances) {
		this.calculateAbsoluteActionOccurances = calculateAbsoluteActionOccurances;
	}

	public boolean isCalculateRelativeActionOccurances() {
		return calculateRelativeActionOccurances;
	}

	public void setCalculateRelativeActionOccurances(
			boolean calculateRelativeActionOccurances) {
		this.calculateRelativeActionOccurances = calculateRelativeActionOccurances;
	}

	public int getRequestedExpectedUtilityValues() {
		return requestedExpectedUtilityValues;
	}

	public void setRequestedExpectedUtilityValues(
			int requestedExpectedUtilityValues) {
		this.requestedExpectedUtilityValues = requestedExpectedUtilityValues;
	}

}
