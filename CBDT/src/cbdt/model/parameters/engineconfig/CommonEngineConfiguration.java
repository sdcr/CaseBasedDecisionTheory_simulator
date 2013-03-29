package cbdt.model.parameters.engineconfig;

import java.util.Observable;

public class CommonEngineConfiguration extends Observable {

	/**
	 * The number of expected utilities which should be computed.
	 */
	private int numberOfRequestedExpectedUtilityValues;

	private boolean calculateAbsoluteActionOccurances;
	
	private boolean calculateRelativeActionOccurances;
	
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
