package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;

import cbdt.model.parameters.ActorAction;

public class ActionSelector {
	
	private List<ActorAction> actorActions;

	public ActionSelector(List<ActorAction> actorActions) {
		this.actorActions = actorActions;
	}
	
	public List<ActorAction> computeSelectedActions(NodeContentKeepTree content) {
		List<ActorAction> selectedActions = new ArrayList<ActorAction>(); 
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(ActorAction actorAction : actorActions){
			double cumulativePerformance = computeCumulativePerformance(content, actorAction);
			if(cumulativePerformance > maxCumulativePerformance){
				selectedActions.clear();
				selectedActions.add(actorAction);
				maxCumulativePerformance = cumulativePerformance;
			} else if(cumulativePerformance == maxCumulativePerformance) {
				selectedActions.add(actorAction);
			}
		}
		return selectedActions;
	}

	private double computeCumulativePerformance(NodeContentKeepTree content, ActorAction actorAction) {
		return content.getSumOfUtilities().get(actorAction) - 
				content.getAspirationLevel() * content.getNumberOfOccurances().get(actorAction);
	}
}
