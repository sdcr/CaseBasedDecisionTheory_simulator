package cbdt.control.algorithm.dfsallmatrix;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class DFSallMatrixStyleSimulationEngine {

	int requestedNumberOfExpectedUtilities;
	int numberOfActorActions;
	
	public double[] computeExpectedUtilities(Parameters parameters){
//		List<List<NodeContent>> contents = new ArrayList<List<NodeContent>>();
		
		
		
		requestedNumberOfExpectedUtilities = parameters.getNumberOfRequestedExpectedUtilities();
		numberOfActorActions = parameters.getActorActions().size();
		
		int numOfOutcomes = 0;
		for(ActorAction action : parameters.getActorActions()){
			for(@SuppressWarnings("unused") ActorActionOutcome outcome : action.getActionOutcomes()){
				numOfOutcomes++;
			}
		}
		
		NodeContent[][] contentsMatrix = new NodeContent[requestedNumberOfExpectedUtilities+1][numOfOutcomes];
		
		for (int i = 0; i < contentsMatrix.length; i++) {
			for (int j = 0; j < contentsMatrix[i].length; j++) {
				contentsMatrix[i][j] = new NodeContent();
				contentsMatrix[i][j].setNumberOfOccurances(getInitNumberOfOccurences(parameters));
				contentsMatrix[i][j].setSumOfUtilities(getInitSumOfUtilities(parameters));
			}
		}
		
		//root element in contentsMatrix
		NodeContent rootContent = contentsMatrix[0][0];
		rootContent.setNumberOfOccurances(getInitNumberOfOccurences(parameters));
		rootContent.setSumOfUtilities(getInitSumOfUtilities(parameters));
		rootContent.setAspirationLevel(parameters.getInitialAspirationLevel());
		rootContent.setProbabilityProduct(1.0);		
		
		int[] selectedActions = new int[parameters.getActorActions().size()];
		double[] expectedUtilities = new double[requestedNumberOfExpectedUtilities];
		computeNextStage(contentsMatrix, 0, 0, parameters, selectedActions, expectedUtilities);
		
		return expectedUtilities;
	}

	private void computeNextStage(NodeContent[][] contentsMatrix, int stage,
			int index, Parameters parameters, int[] selectedActionsIndices, double[] expectedUtilities) {
		if(stage < requestedNumberOfExpectedUtilities){
			computeSelectedActions(contentsMatrix, selectedActionsIndices, stage, index);
			int numberOfSelectedActions = 0;
			for (int i = 0; i < selectedActionsIndices.length; i++) {
				if(selectedActionsIndices[i]>-1)
					numberOfSelectedActions++;
			}
			double multiActionProbability = 1.0 / numberOfSelectedActions;
			
			double childrensExpectedUtilitySum = 0;
			int childIndex = 0;
			int childrenStage = stage+1;
			for(int i=0; i<numberOfSelectedActions; i++){
				int selectedActionIndex = selectedActionsIndices[i];
				for(ActorActionOutcome outcome : parameters.getActorActions().get(selectedActionIndex).getActionOutcomes()){
					for(int actionIndex=0; actionIndex< numberOfActorActions; actionIndex++){
						contentsMatrix[childrenStage][childIndex].getNumberOfOccurances()[actionIndex] = contentsMatrix[stage][index].getNumberOfOccurances()[actionIndex];
						contentsMatrix[childrenStage][childIndex].getSumOfUtilities()[actionIndex] = contentsMatrix[stage][index].getSumOfUtilities()[actionIndex];
					}
					contentsMatrix[childrenStage][childIndex].getNumberOfOccurances()[selectedActionIndex] = 
							contentsMatrix[childrenStage][childIndex].getNumberOfOccurances()[selectedActionIndex]+1;
					contentsMatrix[childrenStage][childIndex].getSumOfUtilities()[selectedActionIndex] = 
							contentsMatrix[childrenStage][childIndex].getSumOfUtilities()[selectedActionIndex]+outcome.getUtility();
					contentsMatrix[childrenStage][childIndex].setProbabilityProduct(contentsMatrix[stage][index].getProbabilityProduct() * multiActionProbability * outcome.getProbability());
					contentsMatrix[childrenStage][childIndex].setAspirationLevel(contentsMatrix[stage][index].getAspirationLevel() * parameters.getWeightingFactorAlpha()
							+ computeChildsMaxAverageUtility(contentsMatrix, childrenStage, childIndex) * (1 - parameters.getWeightingFactorAlpha()));
					
					childrensExpectedUtilitySum += contentsMatrix[childrenStage][childIndex].getProbabilityProduct() * outcome.getUtility();
					childIndex++;
				}
			}
			expectedUtilities[stage] += childrensExpectedUtilitySum;

			initChildComputation(contentsMatrix, parameters, selectedActionsIndices, expectedUtilities,
					childIndex, childrenStage);
		}
	}

	private void initChildComputation(NodeContent[][] contents,
			Parameters parameters, int[] selectedActionsIndices,
			double[] expectedUtilities, int childIndex, int childrenStage) {
		for(int i=0; i<childIndex; i++){
			computeNextStage(contents, childrenStage, i, parameters, selectedActionsIndices, expectedUtilities);
		}
	}

	private double computeChildsMaxAverageUtility(NodeContent[][] contents, int childStage,
			int childIndex) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(int existingActionIndex=0; existingActionIndex<numberOfActorActions; existingActionIndex++){
			double actionOccurances = contents[childStage][childIndex].getNumberOfOccurances()[existingActionIndex];
			if(actionOccurances>0){
				maxAverageUtility = Math.max(maxAverageUtility, contents[childStage][childIndex].getSumOfUtilities()[existingActionIndex] / actionOccurances);
			}
		}
		return maxAverageUtility;
	}

	
	private void computeSelectedActions(NodeContent[][] contentsMatrix, int[] selectedActionsIndices, int stage, int index) {
		for(int i=0; i<selectedActionsIndices.length; i++)
			selectedActionsIndices[i]=-1;
		int j=0;
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(int actorActionIndex=0; actorActionIndex<numberOfActorActions; actorActionIndex++){
			double cumulativePerformance = computeCumulativePerformance(contentsMatrix, actorActionIndex, stage, index);
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

	private double computeCumulativePerformance(NodeContent[][] contentsMatrix, int actorActionIndex, int stage, int index) {
		return contentsMatrix[stage][index].getSumOfUtilities()[actorActionIndex] - 
				contentsMatrix[stage][index].getAspirationLevel() * contentsMatrix[stage][index].getNumberOfOccurances()[actorActionIndex];
	}
	
	private int[] getInitNumberOfOccurences(Parameters parameters) {
		int[] numberOfOccurances = new int[parameters.getActorActions().size()];
		for(int i=0; i<parameters.getActorActions().size(); i++){
			numberOfOccurances[i] = 0;
		}
		return numberOfOccurances;
	}

	private double[] getInitSumOfUtilities(Parameters parameters) {
		double[] sumOfUtilities = new double[parameters.getActorActions().size()];
		for(int i=0; i<parameters.getActorActions().size(); i++){
			sumOfUtilities[i] = 0.0;
		}
		return sumOfUtilities;
	}
}
