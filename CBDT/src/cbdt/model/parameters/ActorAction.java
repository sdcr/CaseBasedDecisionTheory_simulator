package cbdt.model.parameters;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * ActorAction models one action alternative, the actor has under the CBDT
 * model. It consists of several {@link ActorActionOutcome}s.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ActorAction extends Observable {

	private String actionName;

	/**
	 * The set of {@link ActorActionOutcome}s which can occur when this
	 * {@link ActorAction} is chosen by the actor.
	 */
	private List<ActorActionOutcome> actionOutcomes;

	/**
	 * Constructor. It initializes the set of {@link ActorActionOutcome}s to an
	 * empty list.
	 * 
	 * @param actionName
	 */
	public ActorAction(String actionName) {
		this.actionName = actionName;
		this.actionOutcomes = new ArrayList<ActorActionOutcome>();
	}

	/**
	 * @return The name of this {@link ActorAction}.
	 */
	public String getActionName() {
		return actionName;
	}

	/**
	 * Sets the name of this {@link ActorAction}.
	 * 
	 * @param actionName
	 */
	public void setActionName(String actionName) {
		this.actionName = actionName;
	}

	/**
	 * @return The list of {@link ActorActionOutcome}s which can occur if this
	 *         action is chosen by the actor.
	 */
	public List<ActorActionOutcome> getActionOutcomes() {
		return actionOutcomes;
	}

	/**
	 * Sets the list of {@link ActorActionOutcome}s.
	 * 
	 * @param actionOutcomes
	 *            The list of {@link ActorActionOutcome}s which can occur if
	 *            this action is chosen by the actor.
	 */
	public void setActionOutcomes(List<ActorActionOutcome> actionOutcomes) {
		this.actionOutcomes = actionOutcomes;
		setChanged();
		notifyObservers();
	}

	/**
	 * Adds a {@link ActorActionOutcome} to the list of outcomes.
	 * 
	 * @param actionOutcome
	 *            An {@link ActorActionOutcome} which can occur if this action
	 *            is chosen by the actor.
	 */
	public void addActionOutcome(ActorActionOutcome actionOutcome) {
		actionOutcome.setAction(this);
		this.actionOutcomes.add(actionOutcome);
		setChanged();
		notifyObservers();
	}

	/**
	 * Removes an {@link ActorActionOutcome}.
	 * 
	 * @param actionOutcome
	 *            The ActorActionOutcome which is to be removed from this
	 *            ActorAction.
	 */
	public void removeActionOutcome(ActorActionOutcome actionOutcome) {
		this.actionOutcomes.remove(actionOutcome);
		setChanged();
		notifyObservers();
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
			if (actionOutcomes.size() != other.actionOutcomes.size())
				return false;
			for (int i = 0; i < actionOutcomes.size(); i++)
				if (!actionOutcomes.get(i).equals(other.actionOutcomes.get(i)))
					return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "[actionName: " + actionName + ", outcomes: " + actionOutcomes
				+ "]";
	}
}
