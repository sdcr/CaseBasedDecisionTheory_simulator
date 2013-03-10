package cbdt.model.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * ActorAction models one action alternative, the actor has under the CBDT
 * model. It consists of several ActorActionOutcomes.
 * 
 * @author S
 */
public class ActorAction extends Observable{


	private String actionName;

	/**
	 * The set of ActorActionOutcomes which can occur when this ActorAction is
	 * chosen by the actor.
	 */
	private List<ActorActionOutcome> actionOutcomes;

	/**
	 * Constructor which initializes the set of ActorActionOutcomes which can
	 * occur if this action is chosen to an empty set.
	 * 
	 * @param actionName
	 */
	public ActorAction(String actionName) {
		this.actionName = actionName;
		this.actionOutcomes = new ArrayList<ActorActionOutcome>();
	}

	/**
	 * @return The name of this ActorAction.
	 */
	public String getActionName() {
		return actionName;
	}

	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return The set of ActorActionOutcomes which can occur if this action is
	 *         chosen by the actor.
	 */
	public List<ActorActionOutcome> getActionOutcomes() {
		return actionOutcomes;
	}

	/**
	 * @param actionOutcomes
	 *            The set of ActorActionOutcomes which can occur if this action
	 *            is chosen by the actor.
	 */
	public void setActionOutcomes(List<ActorActionOutcome> actionOutcomes) {
		this.actionOutcomes = actionOutcomes;
		setChanged();
		notifyObservers();
	}

	/**
	 * @param actionOutcome
	 *            An ActorActionOutcome which can occur if this action
	 *            is chosen by the actor.
	 */
	public void addActionOutcome(ActorActionOutcome actionOutcome) {
		actionOutcome.setAction(this);
		this.actionOutcomes.add(actionOutcome);
		setChanged();
		notifyObservers();
	}

	/**
	 * @param actionOutcome
	 * 			The ActorActionOutcome which is to be removed from this ActorAction.
	 */
	public void removeActionOutcome(ActorActionOutcome actionOutcome){
		this.actionOutcomes.remove(actionOutcome);
		setChanged();
		notifyObservers();
	}
	
	/**
	 * @return Returns whether the probabilities of the ActorActionOutcomes
	 *         associated with this ActorAction form a valid probability
	 *         distribution.
	 * 
	 *         Validity is assumed if the sum of the probabilities equals one.
	 */
	public boolean hasValidProbabilityDistribution() {
		double totalProbability = 0;

		for (ActorActionOutcome pair : actionOutcomes) {
			totalProbability += pair.getProbability();
		}

		return totalProbability == 1;
	}
	
	public boolean equals(ActorAction obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActorAction other = (ActorAction) obj;
		if (actionName == null) {
			if (other.actionName != null)
				return false;
		} else if (!actionName.equals(other.actionName))
			return false;
		if (actionOutcomes == null) {
			if (other.actionOutcomes != null)
				return false;
		} else {
			if(actionOutcomes.size() != other.actionOutcomes.size())
				return false;
			for(int i=0; i<actionOutcomes.size(); i++)
				if(!actionOutcomes.get(i).equals(other.actionOutcomes.get(i)))
					return false;
		}
		return true;
	}
	
	@Override
	public String toString() {
		return "[actionName: "+actionName+", outcomes: "+actionOutcomes+"]";
	}
}
