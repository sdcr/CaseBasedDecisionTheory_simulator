package cbdt.model.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

//YELLOW
/**
 * The class modeling the parameters with which a simulation should be
 * performed. This model class is to be distinguished from classes modeling
 * configurations. In contrast to config model classes, Parameters contains data
 * which are prescribed by the mathematical theory of CBDT.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class Parameters extends Observable {

	/**
	 * The weighting factor with which the old aspiration level is calculated
	 * into the new aspiration level in each simulation step.
	 */
	private double weightingFactorAlpha;

	/**
	 * The aspiration level at the beginning of the simulation.
	 */
	private double initialAspirationLevel;

	/**
	 * The value with which the aspiration level is increased at a sparse set of
	 * simulation steps.
	 */
	private double aspirationLevelIncrement;

	/**
	 * Denotes whether the aspiration level should be incremented at sparse
	 * simulation steps instead of calculating it by weighting between the
	 * previous aspiration level and the new maximal average utility.
	 */
	private boolean incrementAspLevelSparsely;

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
	 * @return The value with which the aspiration level is increased at a
	 *         sparse set of simulation steps.
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
	 * 
	 * @param actorAction
	 */
	public void addActorAction(ActorAction actorAction) {
		this.actorActions.add(actorAction);
		setChanged();
		notifyObservers(actorActions);
	}

	/**
	 * Removes an ActorAction object from the model.
	 * 
	 * @param actorAction
	 */
	public void removeActorAction(ActorAction actorAction) {
		this.actorActions.remove(actorAction);
		setChanged();
		notifyObservers();
	}

	// TODO: update to accommodate for number of expected utilities.
	public boolean equals(Parameters obj) {
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
		} else {
			if (actorActions.size() != other.actorActions.size())
				return false;
			for (int i = 0; i < actorActions.size(); i++)
				if (!actorActions.get(i).equals(other.actorActions.get(i)))
					return false;
		}
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

	public boolean isIcrementingAspirationLevelSparsely() {
		return incrementAspLevelSparsely;
	}

	public void setIncrementAspirationLevelSparsely(
			boolean incrementAspirationLevelSparsely) {
		this.incrementAspLevelSparsely = incrementAspirationLevelSparsely;
		setChanged();
		notifyObservers();
	}

}
