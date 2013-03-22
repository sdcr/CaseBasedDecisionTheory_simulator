package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.model.parameters.ActorActionOutcome;

public class ChildNodeContentGenerator {

	private ActorActionOutcome[][] outcomeMatrix;
	private int numberOfActorActions;
	private double aspirationLevelDiscount;

	public ChildNodeContentGenerator(ActorActionOutcome[][] outcomeMatrix, double aspirationLevelDiscount) {
		this.outcomeMatrix = outcomeMatrix;
		this.aspirationLevelDiscount = aspirationLevelDiscount;
		numberOfActorActions = outcomeMatrix.length;
	}
	
	public void computeChildContent(NodeContent parentContent, NodeContent childContent, double multiActionProbability,
			int selectedActionIndex, int outcomeIndex) {
		for(int actionIndex=0; actionIndex< numberOfActorActions; actionIndex++){
			childContent.numberOfOccurances[actionIndex] = parentContent.numberOfOccurances[actionIndex];
			childContent.sumOfUtilities[actionIndex] = parentContent.sumOfUtilities[actionIndex];
		}
		childContent.numberOfOccurances[selectedActionIndex] = 
				childContent.numberOfOccurances[selectedActionIndex]+1;
		childContent.sumOfUtilities[selectedActionIndex] = 
				childContent.sumOfUtilities[selectedActionIndex] + outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
		childContent.probabilityProduct = parentContent.probabilityProduct * multiActionProbability * outcomeMatrix[selectedActionIndex][outcomeIndex].getProbability();
		childContent.aspirationLevel = parentContent.aspirationLevel * aspirationLevelDiscount
				+ (computeChildsMaxAverageUtility(childContent) * (1 - aspirationLevelDiscount));
	}

	private double computeChildsMaxAverageUtility(NodeContent childContent) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(int existingActionIndex=0; existingActionIndex<numberOfActorActions; existingActionIndex++){
			double actionOccurances = childContent.numberOfOccurances[existingActionIndex];
			if(actionOccurances>0){
				maxAverageUtility = Math.max(maxAverageUtility, childContent.sumOfUtilities[existingActionIndex] / actionOccurances);
			}
		}
		return maxAverageUtility;
	}


}
