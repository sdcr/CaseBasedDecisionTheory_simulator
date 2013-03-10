package cbdt.model;

import java.util.Observable;

/**
 * An ActorActionOutcome belongs to an ActorAction. The ActorActionOutcome 
 * models one outcome that is possible, when the actor chooses the respective 
 * action.
 * @author S
 */
public class ActorActionOutcome extends Observable {

	/** The ActorAction this ActorActionOutcome belongs to. */
	private ActorAction action;
	
	/** The probability with which this outcome occurs if the 
	 * action this outcome belongs to is chosen by the actor. */
	private double probability;
	
	/** The utility incurred by the actor if this outcome occurs. */
	private double utility;
	
	public ActorActionOutcome(double probability, double utility) {
		this.probability = probability;
		this.utility = utility;
	}
	
	/**
	 * @return The utility incurred by the actor if this outcome occurs.
	 */
	public double getUtility() {
		return utility;
	}

	public void setUtility(double utility) {
		this.utility = utility;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return The probability with which this outcome occurs if the 
	 * action this outcome belongs to is chosen by the actor.
	 */
	public double getProbability() {
		return probability;
	}

	public void setProbability(double probability) {
		this.probability = probability;
		setChanged();
		notifyObservers();
	}

	/**
	 * @return The ActorAction this ActorActionOutcome belongs to.
	 */
	public ActorAction getAction() {
		return action;
	}

	public void setAction(ActorAction action) {
		this.action = action;
		setChanged();
		notifyObservers();
	}
	
	@Override
	public String toString() {
		return "(ActorActionOutcome; probability: "+probability+", utility: "+utility+")";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(probability);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(utility);
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
