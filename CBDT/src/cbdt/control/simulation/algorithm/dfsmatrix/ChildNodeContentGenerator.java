package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class ChildNodeContentGenerator {

	private ActorActionOutcome[][] outcomeMatrix;
	private int numberOfActorActions;
	private AspirationLevelGenerator aspirationLevelGenerator;

	public ChildNodeContentGenerator(Parameters parameters, ActorActionOutcome[][] outcomeMatrix) {
		this.outcomeMatrix = outcomeMatrix;
		numberOfActorActions = outcomeMatrix.length;
		aspirationLevelGenerator = new AspirationLevelGenerator(parameters);
	}
	
	public void computeChildContent(NodeContent parentContent, NodeContent childContent, double multiActionProbability,
			int selectedActionIndex, int outcomeIndex, int indexOfChildrensStage) {
		for(int actionIndex=0; actionIndex< numberOfActorActions; actionIndex++){
			childContent.numberOfOccurances[actionIndex] = parentContent.numberOfOccurances[actionIndex];
			childContent.sumOfUtilities[actionIndex] = parentContent.sumOfUtilities[actionIndex];
		}
		childContent.numberOfOccurances[selectedActionIndex] = 
				childContent.numberOfOccurances[selectedActionIndex]+1;
		childContent.sumOfUtilities[selectedActionIndex] = 
				childContent.sumOfUtilities[selectedActionIndex] + outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
		childContent.probabilityProduct = parentContent.probabilityProduct * multiActionProbability * outcomeMatrix[selectedActionIndex][outcomeIndex].getProbability();
		childContent.aspirationLevel = aspirationLevelGenerator.calculateChildsAspirationLevel(parentContent, childContent, indexOfChildrensStage);
	}




}
