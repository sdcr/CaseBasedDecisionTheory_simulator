package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;

import cbdt.model.parameters.ActorAction;
import cbdt.model.result.tree.NodeContent;

/**
 * This class computes the {@link ActorAction}s which are chosen according to
 * CBDT in a certain node.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ActionSelector {

	/**
	 * The possible {@link ActorAction}s.
	 */
	private List<ActorAction> actorActions;

	/**
	 * The constructor.
	 * 
	 * @param actorActions
	 */
	public ActionSelector(List<ActorAction> actorActions) {
		this.actorActions = actorActions;
	}

	/**
	 * Computes the {@link ActorAction}s which are selected at a given node.
	 * 
	 * @param content
	 * @return
	 */
	public List<ActorAction> computeSelectedActions(NodeContent content) {
		List<ActorAction> selectedActions = new ArrayList<ActorAction>();
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;

		for (ActorAction actorAction : actorActions) {
			double cumulativePerformance = computeCumulativePerformance(
					content, actorAction);
			if (cumulativePerformance > maxCumulativePerformance) {
				selectedActions.clear();
				selectedActions.add(actorAction);
				maxCumulativePerformance = cumulativePerformance;
			} else if (cumulativePerformance == maxCumulativePerformance) {
				selectedActions.add(actorAction);
			}
		}
		return selectedActions;
	}

	private double computeCumulativePerformance(NodeContent content,
			ActorAction actorAction) {
		return content.getSumOfUtilities().get(actorAction)
				- content.getAspirationLevel()
				* content.getNumberOfOccurances().get(actorAction);
	}
}
