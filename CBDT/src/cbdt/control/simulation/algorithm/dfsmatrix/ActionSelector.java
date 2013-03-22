package cbdt.control.simulation.algorithm.dfsmatrix;

public class ActionSelector {

	private int numberOfActorActions;
	
	public ActionSelector(int numberOfActorActions) {
		this.numberOfActorActions = numberOfActorActions;
	}
	
	public void computeSelectedActions(int[] selectedActionsIndices, NodeContent content) {
		for(int i=0; i<selectedActionsIndices.length; i++)
			selectedActionsIndices[i]=-1;
		int j=0;
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(int actorActionIndex=0; actorActionIndex<numberOfActorActions; actorActionIndex++){
			double cumulativePerformance = computeCumulativePerformance(actorActionIndex, content);
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
