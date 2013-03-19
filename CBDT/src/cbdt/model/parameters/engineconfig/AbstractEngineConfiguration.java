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

	/**
	 * The number of expected utilities which should be computed.
	 */
	private int numberOfRequestedExpectedUtilityValues;

	private boolean calculateAbsoluteActionOccurances;
	
	private boolean calculateRelativeActionOccurances;
	
	public abstract String getName();

	public boolean isCalculateAbsoluteActionOccurances() {
		return calculateAbsoluteActionOccurances;
	}

	public void setCalculateAbsoluteActionOccurances(
			boolean calculateAbsoluteActionOccurances) {
		this.calculateAbsoluteActionOccurances = calculateAbsoluteActionOccurances;
		setChanged();
		notifyObservers();
	}

	public boolean isCalculateRelativeActionOccurances() {
		return calculateRelativeActionOccurances;
	}

	public void setCalculateRelativeActionOccurances(
			boolean calculateRelativeActionOccurances) {
		this.calculateRelativeActionOccurances = calculateRelativeActionOccurances;
		setChanged();
		notifyObservers();
	}

	public int getNumberOfRequestedExpectedUtilityValues() {
		return numberOfRequestedExpectedUtilityValues;
	}

	public void setNumberOfRequestedExpectedUtilityValues(
			int numberOfRequestedExpectedUtilityValues) {
		this.numberOfRequestedExpectedUtilityValues = numberOfRequestedExpectedUtilityValues;
	}


}
