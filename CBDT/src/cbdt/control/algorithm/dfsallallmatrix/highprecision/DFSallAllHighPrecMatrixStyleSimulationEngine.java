package cbdt.control.algorithm.dfsallallmatrix.highprecision;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.List;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class DFSallAllHighPrecMatrixStyleSimulationEngine {

	private static final BigDecimal BIG_DECIMAL_1_0 = new BigDecimal(1.0);
	int requestedNumberOfExpectedUtilities;
	int numberOfActorActions;
	private ActorActionOutcome[][] outcomeMatrix;
	double alpha;
	private List<List<Character>> choosenActions;
	
	private int[][] choosenActionNumbers;
	
	public int[][] getChoosenActionNumbers() {
		return choosenActionNumbers;
	}


	public List<List<Character>> getChoosenActions() {
		return choosenActions;
	}

	MathContext mathContext;
	private BigDecimal multiActionProbability;
	private BigDecimal childrensExpectedUtilitySum;
	private BigDecimal big_one_minus_alpha;
	private BigDecimal big_alpha;
	private BigDecimal big_0_0 = new BigDecimal(0.0);;

	public BigDecimal[] computeExpectedUtilities(Parameters parameters){
//		List<List<NodeContent>> contents = new ArrayList<List<NodeContent>>();
		mathContext = new MathContext(30);
		
		requestedNumberOfExpectedUtilities = parameters.getNumberOfRequestedExpectedUtilities();
		numberOfActorActions = parameters.getActorActions().size();
		alpha = parameters.getWeightingFactorAlpha();
		big_one_minus_alpha = new BigDecimal(1 - alpha);
		big_alpha = new BigDecimal(alpha);
		
		choosenActionNumbers = new int[requestedNumberOfExpectedUtilities][numberOfActorActions];
		
		choosenActions = new ArrayList<List<Character>>();
		for(int i=0; i<requestedNumberOfExpectedUtilities; i++){
			choosenActions.add(new ArrayList<Character>());
		}
		
		int numOfOutcomes = 0;
		int maxNumberOfOutcomes = 0;
		for(ActorAction action : parameters.getActorActions()){
			maxNumberOfOutcomes = Math.max(action.getActionOutcomes().size(), maxNumberOfOutcomes);
			numOfOutcomes += action.getActionOutcomes().size();
		}

		outcomeMatrix = new ActorActionOutcome[numberOfActorActions][];
		for (int i = 0; i < outcomeMatrix.length; i++) {
			int numberOfOutcomes = parameters.getActorActions().get(i).getActionOutcomes().size();
			outcomeMatrix[i] = new ActorActionOutcome[numberOfOutcomes];
			for (int j = 0; j < numberOfOutcomes; j++) {
				outcomeMatrix[i][j] = parameters.getActorActions().get(i).getActionOutcomes().get(j);
			}
		}
		
		
		NodeContent[][] contentsMatrix = new NodeContent[requestedNumberOfExpectedUtilities+1][numOfOutcomes];		
		for (int i = 0; i < contentsMatrix.length; i++) {
			for (int j = 0; j < contentsMatrix[i].length; j++) {
				contentsMatrix[i][j] = new NodeContent();
				contentsMatrix[i][j].numberOfOccurances = getInitNumberOfOccurences(parameters);
				contentsMatrix[i][j].sumOfUtilities = getInitSumOfUtilities(parameters);
			}
		}
		
		//root element in contentsMatrix
		NodeContent rootContent = contentsMatrix[0][0];
		rootContent.numberOfOccurances = (getInitNumberOfOccurences(parameters));
		rootContent.sumOfUtilities = (getInitSumOfUtilities(parameters));
		rootContent.aspirationLevel = (new BigDecimal(parameters.getInitialAspirationLevel()));
		rootContent.probabilityProduct = BIG_DECIMAL_1_0;		
		
		int[] selectedActions = new int[parameters.getActorActions().size()];
		
		BigDecimal[] expectedUtilities = new BigDecimal[requestedNumberOfExpectedUtilities];
		for(int i=0; i<requestedNumberOfExpectedUtilities; i++)
			expectedUtilities[i] = new BigDecimal(0.0);
		computeNextStage(contentsMatrix, 0, 0, selectedActions, expectedUtilities);
		
		return expectedUtilities;
	}


	private void computeNextStage(NodeContent[][] contentsMatrix, int stage,
			int index, int[] selectedActionsIndices, BigDecimal[] expectedUtilities) {
		if(stage < requestedNumberOfExpectedUtilities){
			computeSelectedActions(contentsMatrix, selectedActionsIndices, stage, index);
			int numberOfSelectedActions = 0;
			for (int i = 0; i < selectedActionsIndices.length; i++) {
				if(selectedActionsIndices[i]>-1)
					numberOfSelectedActions++;
			}
			multiActionProbability = BIG_DECIMAL_1_0.divide(new BigDecimal(numberOfSelectedActions), mathContext);
			
			childrensExpectedUtilitySum = big_0_0;
			int childIndex = 0;
			int childrenStage = stage+1;
			for(int i=0; i<numberOfSelectedActions; i++){
				int selectedActionIndex = selectedActionsIndices[i];
				choosenActions.get(stage).add((char) selectedActionIndex);
				choosenActionNumbers[stage][selectedActionIndex]++;
				for(int outcomeIndex=0; outcomeIndex<outcomeMatrix[selectedActionIndex].length; outcomeIndex++){
					for(int actionIndex=0; actionIndex< numberOfActorActions; actionIndex++){
						contentsMatrix[childrenStage][childIndex].numberOfOccurances[actionIndex] = contentsMatrix[stage][index].numberOfOccurances[actionIndex];
						contentsMatrix[childrenStage][childIndex].sumOfUtilities[actionIndex] = contentsMatrix[stage][index].sumOfUtilities[actionIndex];
					}
					contentsMatrix[childrenStage][childIndex].numberOfOccurances[selectedActionIndex] = 
							contentsMatrix[childrenStage][childIndex].numberOfOccurances[selectedActionIndex]+1;
					contentsMatrix[childrenStage][childIndex].sumOfUtilities[selectedActionIndex] = 
							contentsMatrix[childrenStage][childIndex].sumOfUtilities[selectedActionIndex] + outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
					contentsMatrix[childrenStage][childIndex].probabilityProduct = contentsMatrix[stage][index].probabilityProduct
							.multiply(multiActionProbability, mathContext)
							.multiply(new BigDecimal(outcomeMatrix[selectedActionIndex][outcomeIndex].getProbability()), mathContext);
					contentsMatrix[childrenStage][childIndex].aspirationLevel = contentsMatrix[stage][index].aspirationLevel
							.multiply(big_alpha, mathContext)
							.add(computeChildsMaxAverageUtility(contentsMatrix, childrenStage, childIndex).multiply(big_one_minus_alpha,mathContext), mathContext);
					
					childrensExpectedUtilitySum = childrensExpectedUtilitySum
							.add(contentsMatrix[childrenStage][childIndex].probabilityProduct
									.multiply(new BigDecimal(outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility()),mathContext),mathContext);
					childIndex++;
				}
			}
			expectedUtilities[stage] = expectedUtilities[stage].add(childrensExpectedUtilitySum, mathContext);

			initChildComputation(contentsMatrix, selectedActionsIndices, expectedUtilities,
					childIndex, childrenStage);
		}
	}

	private void initChildComputation(NodeContent[][] contents,
			int[] selectedActionsIndices, BigDecimal[] expectedUtilities, int childIndex, int childrenStage) {
		for(int i=0; i<childIndex; i++){
			computeNextStage(contents, childrenStage, i, selectedActionsIndices, expectedUtilities);
		}
	}

	private BigDecimal computeChildsMaxAverageUtility(NodeContent[][] contents, int childStage,
			int childIndex) {
		BigDecimal maxAverageUtility = null;
		for(int existingActionIndex=0; existingActionIndex<numberOfActorActions; existingActionIndex++){
			BigDecimal actionOccurances = new BigDecimal(contents[childStage][childIndex].numberOfOccurances[existingActionIndex]);
			if(actionOccurances.compareTo(new BigDecimal(0)) == 1){
				BigDecimal possiblyNewMax = new BigDecimal(contents[childStage][childIndex].sumOfUtilities[existingActionIndex]).divide(actionOccurances, mathContext);
				if(maxAverageUtility == null)
					maxAverageUtility = possiblyNewMax;
				else{
					if(maxAverageUtility.compareTo(possiblyNewMax) == -1){
						maxAverageUtility = possiblyNewMax;
					}
				}
			}
		}
		return maxAverageUtility;
	}

	
	private void computeSelectedActions(NodeContent[][] contentsMatrix, int[] selectedActionsIndices, int stage, int index) {
		for(int i=0; i<selectedActionsIndices.length; i++)
			selectedActionsIndices[i]=-1;
		int j=0;
		BigDecimal maxCumulativePerformance = null;//Double.NEGATIVE_INFINITY;
		
		for(int actorActionIndex=0; actorActionIndex<numberOfActorActions; actorActionIndex++){
			BigDecimal cumulativePerformance = computeCumulativePerformance(contentsMatrix, actorActionIndex, stage, index);
			if(maxCumulativePerformance == null || cumulativePerformance.compareTo(maxCumulativePerformance)==1){
				for(int i=0; i<selectedActionsIndices.length; i++)
					selectedActionsIndices[i]=-1;
				j=0;
				selectedActionsIndices[j] = actorActionIndex;
				maxCumulativePerformance = cumulativePerformance;
			} else if(cumulativePerformance.compareTo(maxCumulativePerformance)==0) {
				j++;
				selectedActionsIndices[j] = actorActionIndex;
			}
		}
	}

	private BigDecimal computeCumulativePerformance(NodeContent[][] contentsMatrix, int actorActionIndex, int stage, int index) {
		return new BigDecimal(contentsMatrix[stage][index].sumOfUtilities[actorActionIndex])
		.subtract(contentsMatrix[stage][index].aspirationLevel
					.multiply(new BigDecimal(contentsMatrix[stage][index].numberOfOccurances[actorActionIndex]), mathContext), mathContext);
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
