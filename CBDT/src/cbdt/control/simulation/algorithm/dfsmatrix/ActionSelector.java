package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.model.parameters.ActorAction;

/**
 * This class computes the {@link ActorAction}s which are chosen according to
 * CBDT in a certain node.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ActionSelector {

	protected int numberOfActorActions;
	
	/**
	 * Constructor.
	 * 
	 * @param numberOfActorActions
	 */
	public ActionSelector(int numberOfActorActions) {
		this.numberOfActorActions = numberOfActorActions;
	}
	
	/**
	 * Compute the indices of actions to be chosen in the given node.
	 * 
	 * @param selectedActionsIndices
	 * @param content
	 */
	public void computeSelectedActions(int[] selectedActionsIndices, IAlgoNodeContent content) {
		for(int i=0; i<selectedActionsIndices.length; i++)
			selectedActionsIndices[i]=-1;
		int j=0;
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(int actorActionIndex=0; actorActionIndex<numberOfActorActions; actorActionIndex++){
			double cumulativePerformance = computeCumulativePerformance(actorActionIndex, (MatrixNodeContent)content);
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

	private double computeCumulativePerformance(int actorActionIndex, MatrixNodeContent content) {
		return content.sumOfUtilities[actorActionIndex] - content.aspirationLevel * content.numberOfOccurances[actorActionIndex];
	}
	
}
