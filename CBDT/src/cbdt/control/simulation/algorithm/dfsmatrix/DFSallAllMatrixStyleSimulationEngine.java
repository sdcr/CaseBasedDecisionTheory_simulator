package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import cbdt.control.simulation.algorithm.NodeContent;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class DFSallAllMatrixStyleSimulationEngine {

	int requestedNumberOfExpectedUtilities;
	int numberOfActorActions;
	private ActorActionOutcome[][] outcomeMatrix;
	double alpha;
	private List<List<Character>> choosenActions;
	
	private BigDecimal[][] choosenActionNumbers;
	private MathContext mathContext;
	private BigDecimal big_one;
	
	public BigDecimal[][] getChoosenActionNumbers() {
		return choosenActionNumbers;
	}


	public List<List<Character>> getChoosenActions() {
		return choosenActions;
	}


	public double[] computeExpectedUtilities(Parameters parameters){
//		List<List<NodeContent>> contents = new ArrayList<List<NodeContent>>();
		
		requestedNumberOfExpectedUtilities = parameters.getNumberOfRequestedExpectedUtilities();
		numberOfActorActions = parameters.getActorActions().size();
		alpha = parameters.getWeightingFactorAlpha();
				
		choosenActionNumbers = new BigDecimal[requestedNumberOfExpectedUtilities][numberOfActorActions];
		for(int i=0; i<requestedNumberOfExpectedUtilities; i++){
			for (int j=0; j<numberOfActorActions; j++) {
				choosenActionNumbers[i][j] = new BigDecimal(0);
			}
		}
		big_one = new BigDecimal(1);
		mathContext = new MathContext(30);
		
//		choosenActions = new ArrayList<List<Character>>();
//		for(int i=0; i<requestedNumberOfExpectedUtilities; i++){
//			choosenActions.add(new ArrayList<Character>());
//		}
		
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
		rootContent.aspirationLevel = (parameters.getInitialAspirationLevel());
		rootContent.probabilityProduct = (1.0);		
		
		int[] selectedActions = new int[parameters.getActorActions().size()];
		double[] expectedUtilities = new double[requestedNumberOfExpectedUtilities];
		computeNextStage(contentsMatrix, 0, 0, selectedActions, expectedUtilities);
		
		return expectedUtilities;
	}


	private void computeNextStage(NodeContent[][] contentsMatrix, int stage,
			int index, int[] selectedActionsIndices, double[] expectedUtilities) {
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

				choosenActionNumbers[stage][selectedActionIndex] = choosenActionNumbers[stage][selectedActionIndex].add(big_one, mathContext);
				for(int outcomeIndex=0; outcomeIndex<outcomeMatrix[selectedActionIndex].length; outcomeIndex++){
					for(int actionIndex=0; actionIndex< numberOfActorActions; actionIndex++){
						contentsMatrix[childrenStage][childIndex].numberOfOccurances[actionIndex] = contentsMatrix[stage][index].numberOfOccurances[actionIndex];
						contentsMatrix[childrenStage][childIndex].sumOfUtilities[actionIndex] = contentsMatrix[stage][index].sumOfUtilities[actionIndex];
					}
					contentsMatrix[childrenStage][childIndex].numberOfOccurances[selectedActionIndex] = 
							contentsMatrix[childrenStage][childIndex].numberOfOccurances[selectedActionIndex]+1;
					contentsMatrix[childrenStage][childIndex].sumOfUtilities[selectedActionIndex] = 
							contentsMatrix[childrenStage][childIndex].sumOfUtilities[selectedActionIndex] + outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
					contentsMatrix[childrenStage][childIndex].probabilityProduct = contentsMatrix[stage][index].probabilityProduct * multiActionProbability * outcomeMatrix[selectedActionIndex][outcomeIndex].getProbability();
					contentsMatrix[childrenStage][childIndex].aspirationLevel = contentsMatrix[stage][index].aspirationLevel * alpha
							+ (computeChildsMaxAverageUtility(contentsMatrix, childrenStage, childIndex) * (1 - alpha));
					
					childrensExpectedUtilitySum += contentsMatrix[childrenStage][childIndex].probabilityProduct * outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
					childIndex++;
				}
			}
			expectedUtilities[stage] += childrensExpectedUtilitySum;

			initChildComputation(contentsMatrix, selectedActionsIndices, expectedUtilities,
					childIndex, childrenStage);
		}
	}

	private void initChildComputation(NodeContent[][] contents,
			int[] selectedActionsIndices, double[] expectedUtilities, int childIndex, int childrenStage) {
		for(int i=0; i<childIndex; i++){
			computeNextStage(contents, childrenStage, i, selectedActionsIndices, expectedUtilities);
		}
	}

	private double computeChildsMaxAverageUtility(NodeContent[][] contents, int childStage,
			int childIndex) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(int existingActionIndex=0; existingActionIndex<numberOfActorActions; existingActionIndex++){
			double actionOccurances = contents[childStage][childIndex].numberOfOccurances[existingActionIndex];
			if(actionOccurances>0){
				maxAverageUtility = Math.max(maxAverageUtility, contents[childStage][childIndex].sumOfUtilities[existingActionIndex] / actionOccurances);
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
		return contentsMatrix[stage][index].sumOfUtilities[actorActionIndex] - 
				contentsMatrix[stage][index].aspirationLevel * contentsMatrix[stage][index].numberOfOccurances[actorActionIndex];
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
