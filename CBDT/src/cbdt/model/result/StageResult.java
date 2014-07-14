package cbdt.model.result;

import java.math.BigDecimal;
import java.util.Map;

import cbdt.model.parameters.ActorAction;

/**
 * This class models the computed results which refer to a certain stage of the
 * computation sequence.<br>
 * <br>
 * In particular, the first {@link StageResult} object is supposed to model the
 * state before any computation happens. This object is represented as stage 0.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class StageResult {

	/**
	 * The number of this stage. The first stage should have the number 0, and
	 * represent the state before any computation.
	 */
	private int stage;

	/**
	 * The computed expected utility value.
	 */
	private double expectedUtility;

	/**
	 * A {@link Map} which maps from an {@link ActorAction} to how often that
	 * action is selected from the outcomes of the directly preceding stage.
	 */
	private Map<ActorAction, BigDecimal> absoluteActionOccurances;

	/**
	 * A {@link Map} which maps from an {@link ActorAction} to the probability
	 * that that action is the last action selected before this stage.
	 */
	private Map<ActorAction, Double> relativeActionOccurances;

	/**
	 * Of all possible outcomes leading up to this stage, this is the value of
	 * the lowest aspiration level.
	 */
	private double lowestAspirationLevel;

	/**
	 * @return the expected utility value.
	 */
	public double getExpectedUtility() {
		return expectedUtility;
	}

	/**
	 * @param expectedUtility
	 *            the expected utility value.
	 */
	public void setExpectedUtility(double expectedUtility) {
		this.expectedUtility = expectedUtility;
	}

	/**
	 * @return A {@link Map} which maps from an {@link ActorAction} to how often
	 *         that action is selected from the outcomes of the directly
	 *         preceding stage.
	 */
	public Map<ActorAction, BigDecimal> getAbsoluteActionOccurances() {
		return absoluteActionOccurances;
	}

	/**
	 * @param absoluteActionOccurances
	 *            A {@link Map} which maps from an {@link ActorAction} to how
	 *            often that action is selected from the outcomes of the
	 *            directly preceding stage.
	 */
	public void setAbsoluteActionOccurances(
			Map<ActorAction, BigDecimal> absoluteActionOccurances) {
		this.absoluteActionOccurances = absoluteActionOccurances;
	}

	/**
	 * @return A {@link Map} which maps from an {@link ActorAction} to the
	 *         probability that that action is the last action selected before
	 *         this stage.
	 */
	public Map<ActorAction, Double> getRelativeActionOccurances() {
		return relativeActionOccurances;
	}

	/**
	 * @param relativeActionOccurances
	 *            A {@link Map} which maps from an {@link ActorAction} to the
	 *            probability that that action is the last action selected
	 *            before this stage.
	 */
	public void setRelativeActionOccurances(
			Map<ActorAction, Double> relativeActionOccurances) {
		this.relativeActionOccurances = relativeActionOccurances;
	}

	/**
	 * @return The number of this stage. The first stage should have the number
	 *         0, and represent the state before any computation.
	 */
	public int getStage() {
		return stage;
	}

	/**
	 * @param stage
	 *            The number of this stage. The first stage should have the
	 *            number 0, and represent the state before any computation.
	 */
	public void setStage(int stage) {
		this.stage = stage;
	}

	/**
	 * @return Of all possible outcomes leading up to this stage, this is the
	 *         value of the lowest aspiration level.
	 */
	public double getLowestAspirationLevel() {
		return lowestAspirationLevel;
	}

	/**
	 * @param lowestAspirationLevel
	 *            Of all possible outcomes leading up to this stage, this is the
	 *            value of the lowest aspiration level.
	 */
	public void setLowestAspirationLevel(double lowestAspirationLevel) {
		this.lowestAspirationLevel = lowestAspirationLevel;
	}
}
