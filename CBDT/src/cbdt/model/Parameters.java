package cbdt.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;



public class Parameters extends Observable {


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

	/**
	 * Adds an ActorAcion object to the model.
	 * @param actorAction
	 */
	public void addActorAction(ActorAction actorAction){
		this.actorActions.add(actorAction);
		setChanged();
		notifyObservers(actorActions);
	}

	/**
	 * Removes an ActorAction object from the model.
	 * @param actorAction
	 */
	public void removeActorAction(ActorAction actorAction){
		this.actorActions.remove(actorAction);
		setChanged();
		notifyObservers();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((actorActions == null) ? 0 : actorActions.hashCode());
		long temp;
		temp = Double.doubleToLongBits(aspirationLevelIncrement);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(initialAspirationLevel);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weightingFactorAlpha);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parameters other = (Parameters) obj;
		if (actorActions == null) {
			if (other.actorActions != null)
				return false;
		} else if (!actorActions.equals(other.actorActions))
			return false;
		if (Double.doubleToLongBits(aspirationLevelIncrement) != Double
				.doubleToLongBits(other.aspirationLevelIncrement))
			return false;
		if (Double.doubleToLongBits(initialAspirationLevel) != Double
				.doubleToLongBits(other.initialAspirationLevel))
			return false;
		if (Double.doubleToLongBits(weightingFactorAlpha) != Double
				.doubleToLongBits(other.weightingFactorAlpha))
			return false;
		return true;
	}
}
