package cbdt.model;

import java.util.ArrayList;
import java.util.List;



public class Parameters {

	/**
	 * The weighting factor with which the old aspiration level is calculated
	 * into the new aspiration level in each simulation step.
	 */
	private double weightingFactorAlpha;

	private double initialAspirationLevel;

	//TODO implement the sparse increment mechanism
	/**
	 * The value with which the aspiration level is increased at a sparse set of
	 * simulation steps.
	 */
	private double aspirationLevelIncrement;

	/**
	 * A list of ActorActions from which the actor can choose.
	 */
	private List<ActorAction> actorActions;

	public Parameters() {
		actorActions = new ArrayList<ActorAction>();
	}

	/**
	 * @return The weighting factor with which the old aspiration level is
	 *         calculated into the new aspiration level in each simulation step.
	 */
	public double getWeightingFactorAlpha() {
		return weightingFactorAlpha;
	}

	/**
	 * @param weightingFactorAlpha
	 *            The weighting factor with which the old aspiration level is
	 *            calculated into the new aspiration level in each simulation
	 *            step.
	 */
	public void setWeightingFactorAlpha(double weightingFactorAlpha) {
		this.weightingFactorAlpha = weightingFactorAlpha;
	}

	/**
	 * @return The initial aspiration level.
	 */
	public double getInitialAspirationLevel() {
		return initialAspirationLevel;
	}

	/**
	 * @param initialAspirationLevel
	 *            The initial aspiration level.
	 */
	public void setInitialAspirationLevel(double initialAspirationLevel) {
		this.initialAspirationLevel = initialAspirationLevel;
	}
	
	/**
	 * @return The value with which the aspiration level is increased at a sparse set of
	 * simulation steps.
	 */
	public double getAspirationLevelIncrement() {
		return aspirationLevelIncrement;
	}

	public void setAspirationLevelIncrement(double aspirationLevelIncrement) {
		this.aspirationLevelIncrement = aspirationLevelIncrement;
	}

	public List<ActorAction> getActorActions() {
		return actorActions;
	}

	public void setActorActions(List<ActorAction> actorActions) {
		this.actorActions = actorActions;
	}

}
