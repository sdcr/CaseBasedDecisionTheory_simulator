package cbdt.model.config.common;

import java.util.Observable;

//YELLOW
/**
 * This class models the configuration parameters which refer to how the
 * simulation results are to be computed and which are independent from
 * parameters which refer to a specific simulation engine. For example, it
 * contains the information how many simulation steps are to be performed.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class CommonConfig extends Observable {

	/**
	 * The number of expected utilities which should be computed.
	 */
	private int numberOfRequestedExpectedUtilityValues;

	// TODO what does it mean again
	private boolean calculateAbsoluteActionOccurances;

	// TODO what does it mean again
	private boolean calculateRelativeActionOccurances;

	// TODO what does it mean again
	private boolean calculateLowestAspirationLevels;

	public boolean isCalculateLowestAspirationLevels() {
		return calculateLowestAspirationLevels;
	}

	public void setCalculateLowestAspirationLevels(
			boolean calculateLowestAspirationLevels) {
		this.calculateLowestAspirationLevels = calculateLowestAspirationLevels;
		setChanged();
		notifyObservers();
	}

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
