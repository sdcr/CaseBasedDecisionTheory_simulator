package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.HashMap;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;

public class NodeContentKeepTreeFactory {
	
	public NodeContentKeepTree getInitRootContent(Parameters parameters) {
		NodeContentKeepTree rootContent = new NodeContentKeepTree();
		rootContent.setProbabilityProduct(1);
		Map<ActorAction, Integer> numberOfOccurances = new HashMap<ActorAction, Integer>();
		Map<ActorAction, Double> sumOfUtilities = new HashMap<ActorAction, Double>();
		
		for(ActorAction action : parameters.getActorActions()){
			numberOfOccurances.put(action, 0);
			sumOfUtilities.put(action, 0.0);
		}
		rootContent.setNumberOfOccurances(numberOfOccurances);
		rootContent.setSumOfUtilities(sumOfUtilities);
		rootContent.setAspirationLevel(parameters.getInitialAspirationLevel());
		return rootContent;
	}

	public NodeContentKeepTree getCopy(NodeContentKeepTree copee){
		NodeContentKeepTree copy = new NodeContentKeepTree();
		copy.setAspirationLevel(copee.getAspirationLevel());

		HashMap<ActorAction,Integer> copysNumberOfOccurances = new HashMap<ActorAction,Integer>();
		HashMap<ActorAction,Double> copysSumOfUtilities = new HashMap<ActorAction,Double>();
		for(ActorAction actorAction : copee.getNumberOfOccurances().keySet()){
			copysNumberOfOccurances.put(actorAction, copee.getNumberOfOccurances().get(actorAction));
			copysSumOfUtilities.put(actorAction, copee.getSumOfUtilities().get(actorAction));
		}
		
		copy.setNumberOfOccurances(copysNumberOfOccurances);
		copy.setSumOfUtilities(copysSumOfUtilities);
		copy.setProbabilityProduct(copee.getProbabilityProduct());
		return copy;
	}
}
