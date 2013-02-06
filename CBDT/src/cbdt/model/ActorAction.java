package cbdt.model;

import java.util.HashSet;
import java.util.Set;

/**
 * ActorAction models one action alternative, the actor has under the CBDT
 * model. It consists of several ActorActionOutcomes.
 * 
 * @author S
 */
public class ActorAction {

	private String actionName;

	/**
	 * The set of ActorActionOutcomes which can occur when this ActorAction is
	 * chosen by the actor.
	 */
	private Set<ActorActionOutcome> actionOutcomes;

	/**
	 * Constructor which initializes the set of ActorActionOutcomes which can
	 * occur if this action is chosen to an empty set.
	 * 
	 * @param actionName
	 */
	public ActorAction(String actionName) {
		this.actionName = actionName;
		this.actionOutcomes = new HashSet<ActorActionOutcome>();
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
	public Set<ActorActionOutcome> getActionOutcomes() {
		return actionOutcomes;
	}

	/**
	 * @param actionOutcomes
	 *            The set of ActorActionOutcomes which can occur if this action
	 *            is chosen by the actor.
	 */
	public void setActionOutcomes(Set<ActorActionOutcome> actionOutcomes) {
		this.actionOutcomes = actionOutcomes;
	}

	/**
	 * @param actionOutcome
	 *            An ActorActionOutcome which can occur if this action
	 *            is chosen by the actor.
	 */
	public void addActionOutcome(ActorActionOutcome actionOutcome) {
		actionOutcome.setAction(this);
		this.actionOutcomes.add(actionOutcome);
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
}
