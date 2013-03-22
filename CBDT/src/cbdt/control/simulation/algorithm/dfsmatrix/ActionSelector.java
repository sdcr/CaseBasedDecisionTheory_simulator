package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.control.simulation.algorithm.INodeContent;

public class ActionSelector {

	protected int numberOfActorActions;
	
	public ActionSelector(int numberOfActorActions) {
		this.numberOfActorActions = numberOfActorActions;
	}
	
	public void computeSelectedActions(int[] selectedActionsIndices, INodeContent content) {
		for(int i=0; i<selectedActionsIndices.length; i++)
			selectedActionsIndices[i]=-1;
		int j=0;
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(int actorActionIndex=0; actorActionIndex<numberOfActorActions; actorActionIndex++){
			double cumulativePerformance = computeCumulativePerformance(actorActionIndex, (NodeContent)content);
			if(cumulativePerformance > maxCumulativePerformance){
				for(int i=0; i<selectedActionsIndices.length; i++)
					selectedActionsIndices[i]=-1;
				j=0;
				selectedActionsIndices[j] = actorActionIndex;
				maxCumulativePerformance = cumulativePerformance;
			} else if(cumulativePerformance == maxCumulativePerformance) {
				j++;
				selectedActionsIndices[j] = actorActionIndex;
			}
		}
	}

	private double computeCumulativePerformance(int actorActionIndex, NodeContent content) {
		return content.sumOfUtilities[actorActionIndex] - content.aspirationLevel * content.numberOfOccurances[actorActionIndex];
	}
	
}
