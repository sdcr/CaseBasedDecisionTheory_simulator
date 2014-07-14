package cbdt.model.config.common;

import java.util.Map;
import java.util.Observable;

import cbdt.model.parameters.ActorAction;

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

	/**
	 * Compute for each stage, a {@link Map} which maps from an
	 * {@link ActorAction} to how often that action is selected from the
	 * outcomes of the directly preceding stage.
	 */
	private boolean calculateAbsoluteActionOccurances;

	/**
	 * Compute for each stage, a {@link Map} which maps from an
	 * {@link ActorAction} to the probability that that action is the last
	 * action selected before that stage.
	 */
	private boolean calculateRelativeActionOccurances;

	/**
	 * Compute for each stage, the value of the lowest aspiration level of all
	 * possible outcomes leading up to that stage.
	 */
	private boolean calculateLowestAspirationLevels;

	/**
	 * @return Compute for each stage, the value of the lowest aspiration level
	 *         of all possible outcomes leading up to that stage.
	 */
	public boolean isCalculateLowestAspirationLevels() {
		return calculateLowestAspirationLevels;
	}

	/**
	 * @param calculateLowestAspirationLevels
	 *            Compute for each stage, the value of the lowest aspiration
	 *            level of all possible outcomes leading up to that stage.
	 */
	public void setCalculateLowestAspirationLevels(
			boolean calculateLowestAspirationLevels) {
		this.calculateLowestAspirationLevels = calculateLowestAspirationLevels;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return Compute for each stage, a {@link Map} which maps from an
	 *         {@link ActorAction} to how often that action is selected from the
	 *         outcomes of the directly preceding stage.
	 */
	public boolean isCalculateAbsoluteActionOccurances() {
		return calculateAbsoluteActionOccurances;
	}

	/**
	 * @param calculateAbsoluteActionOccurances
	 *            Compute for each stage, a {@link Map} which maps from an
	 *            {@link ActorAction} to how often that action is selected from
	 *            the outcomes of the directly preceding stage.
	 */
	public void setCalculateAbsoluteActionOccurances(
			boolean calculateAbsoluteActionOccurances) {
		this.calculateAbsoluteActionOccurances = calculateAbsoluteActionOccurances;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return Compute for each stage, a {@link Map} which maps from an
	 *         {@link ActorAction} to the probability that that action is the
	 *         last action selected before that stage.
	 */
	public boolean isCalculateRelativeActionOccurances() {
		return calculateRelativeActionOccurances;
	}

	/**
	 * @param calculateRelativeActionOccurances
	 *            Compute for each stage, a {@link Map} which maps from an
	 *            {@link ActorAction} to the probability that that action is the
	 *            last action selected before that stage.
	 */
	public void setCalculateRelativeActionOccurances(
			boolean calculateRelativeActionOccurances) {
		this.calculateRelativeActionOccurances = calculateRelativeActionOccurances;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return The number of expected utilities which should be computed.
	 */
	public int getNumberOfRequestedExpectedUtilityValues() {
		return numberOfRequestedExpectedUtilityValues;
	}

	/**
	 * @param numberOfRequestedExpectedUtilityValues
	 *            The number of expected utilities which should be computed.
	 */
	public void setNumberOfRequestedExpectedUtilityValues(
			int numberOfRequestedExpectedUtilityValues) {
		this.numberOfRequestedExpectedUtilityValues = numberOfRequestedExpectedUtilityValues;
	}

}
