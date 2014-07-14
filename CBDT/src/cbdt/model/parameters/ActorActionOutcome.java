package cbdt.model.parameters;

import java.util.Observable;

/**
 * This class models one outcome that is possible, when the actor chooses the
 * respective action. One {@link ActorActionOutcome} belongs to one an
 * {@link ActorAction}.
 * 
 * @author S
 */
public class ActorActionOutcome extends Observable {

	/** The {@link ActorAction} this {@link ActorActionOutcome} belongs to. */
	private ActorAction action;

	/**
	 * The probability with which this outcome occurs if the {@link ActorAction}
	 * this outcome belongs to is chosen by the actor.
	 */
	private double probability;

	/** The utility incurred by the actor if this outcome occurs. */
	private double utility;

	/**
	 * The constructor.
	 * 
	 * @param probability
	 * @param utility
	 */
	public ActorActionOutcome(double probability, double utility) {
		this.probability = probability;
		this.utility = utility;
	}

	/**
	 * @return The utility incurred by the actor, if this outcome occurs.
	 */
	public double getUtility() {
		return utility;
	}

	/**
	 * Sets the utility incurred by the actor, if this outcome occurs.
	 * 
	 * @param utility
	 */
	public void setUtility(double utility) {
		this.utility = utility;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return The probability with which this outcome occurs, if the
	 *         {@link ActorAction} this outcome belongs to is chosen by the
	 *         actor.
	 */
	public double getProbability() {
		return probability;
	}

	/**
	 * Sets the probability with which this outcome occurs, if the
	 * {@link ActorAction} this outcome belongs to is chosen by the actor.
	 * 
	 * @param probability
	 */
	public void setProbability(double probability) {
		this.probability = probability;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return The {@link ActorAction} this {@link ActorActionOutcome} belongs
	 *         to.
	 */
	public ActorAction getAction() {
		return action;
	}

	/**
	 * Sets the {@link ActorAction} this {@link ActorActionOutcome} belongs to.
	 * 
	 * @param action
	 */
	public void setAction(ActorAction action) {
		this.action = action;
		setChanged();
		notifyObservers();
	}

	@Override
	public String toString() {
		return "(ActorActionOutcome; probability: " + probability
				+ ", utility: " + utility + ")";
	}

	public boolean equals(ActorActionOutcome obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActorActionOutcome other = (ActorActionOutcome) obj;
		if (Double.doubleToLongBits(probability) != Double
				.doubleToLongBits(other.probability))
			return false;
		if (Double.doubleToLongBits(utility) != Double
				.doubleToLongBits(other.utility))
			return false;
		return true;
	}

}
