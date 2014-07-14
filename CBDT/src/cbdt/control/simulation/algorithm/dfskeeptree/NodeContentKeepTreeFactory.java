package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.HashMap;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.tree.NodeContent;

/**
 * This class provides convenient methods for the creation of
 * {@link NodeContent} objects.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class NodeContentKeepTreeFactory {

	/**
	 * Produces a {@link NodeContent} object which can serve as a root node.
	 * 
	 * @param parameters
	 * @return
	 */
	public NodeContent getInitRootContent(Parameters parameters) {
		NodeContent rootContent = new NodeContent();
		rootContent.setProbabilityProduct(1);
		Map<ActorAction, Integer> numberOfOccurances = new HashMap<ActorAction, Integer>();
		Map<ActorAction, Double> sumOfUtilities = new HashMap<ActorAction, Double>();

		for (ActorAction action : parameters.getActorActions()) {
			numberOfOccurances.put(action, 0);
			sumOfUtilities.put(action, 0.0);
		}
		rootContent.setNumberOfOccurances(numberOfOccurances);
		rootContent.setSumOfUtilities(sumOfUtilities);
		rootContent.setAspirationLevel(parameters.getInitialAspirationLevel());
		return rootContent;
	}

	/**
	 * Produces a copy of a {@link NodeContent} object.
	 * 
	 * @param copee
	 * @return
	 */
	public NodeContent getCopy(NodeContent copee) {
		NodeContent copy = new NodeContent();
		copy.setAspirationLevel(copee.getAspirationLevel());

		HashMap<ActorAction, Integer> copysNumberOfOccurances = new HashMap<ActorAction, Integer>();
		HashMap<ActorAction, Double> copysSumOfUtilities = new HashMap<ActorAction, Double>();
		for (ActorAction actorAction : copee.getNumberOfOccurances().keySet()) {
			copysNumberOfOccurances.put(actorAction, copee
					.getNumberOfOccurances().get(actorAction));
			copysSumOfUtilities.put(actorAction,
					copee.getSumOfUtilities().get(actorAction));
		}

		copy.setNumberOfOccurances(copysNumberOfOccurances);
		copy.setSumOfUtilities(copysSumOfUtilities);
		copy.setProbabilityProduct(copee.getProbabilityProduct());
		return copy;
	}
}
