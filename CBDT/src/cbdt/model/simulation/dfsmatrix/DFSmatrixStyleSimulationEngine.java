package cbdt.model.simulation.dfsmatrix;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class DFSmatrixStyleSimulationEngine {

	public double[] computeExpectedUtilities(Parameters parameters){
//		List<List<NodeContent>> contents = new ArrayList<List<NodeContent>>();
		
		int numOfOutcomes = 0;
		for(ActorAction action : parameters.getActorActions()){
			for(ActorActionOutcome outcome : action.getActionOutcomes()){
				numOfOutcomes++;
			}
		}
		
		NodeContent[][] contentsMatrix = new NodeContent[parameters.getNumberOfRequestedExpectedUtilities()+1][numOfOutcomes];
		
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
		
		List<ActorAction> selectedActions = new ArrayList<ActorAction>();
		double[] expectedUtilities = new double[parameters.getNumberOfRequestedExpectedUtilities()];
		computeNextStage(contentsMatrix, 0, 0, parameters, selectedActions, expectedUtilities);
		
		return expectedUtilities;
	}

	private void computeNextStage(NodeContent[][] contentsMatrix, int stage,
			int index, Parameters parameters, List<ActorAction> selectedActions, double[] expectedUtilities) {
		if(stage < parameters.getNumberOfRequestedExpectedUtilities()){
			computeSelectedActions(contentsMatrix, parameters, selectedActions, stage, index);
			double multiActionProbability = 1.0 / selectedActions.size();
			
			double childrensExpectedUtilitySum = 0;
			int childIndex = 0;
			int childrenStage = stage+1;
			for(ActorAction selectedAction : selectedActions){
				for(ActorActionOutcome outcome : selectedAction.getActionOutcomes()){
					for(ActorAction action : parameters.getActorActions()){
						contentsMatrix[childrenStage][childIndex].getNumberOfOccurances().put(action, contentsMatrix[stage][index].getNumberOfOccurances().get(action));
						contentsMatrix[childrenStage][childIndex].getSumOfUtilities().put(action, contentsMatrix[stage][index].getSumOfUtilities().get(action));						
					}
					contentsMatrix[childrenStage][childIndex].getNumberOfOccurances().put(selectedAction, 
							contentsMatrix[childrenStage][childIndex].getNumberOfOccurances().get(selectedAction)+1);
					contentsMatrix[childrenStage][childIndex].getSumOfUtilities().put(selectedAction, 
							contentsMatrix[childrenStage][childIndex].getSumOfUtilities().get(selectedAction)+outcome.getUtility());
					contentsMatrix[childrenStage][childIndex].setProbabilityProduct(contentsMatrix[stage][index].getProbabilityProduct() * multiActionProbability * outcome.getProbability());
					contentsMatrix[childrenStage][childIndex].setAspirationLevel(contentsMatrix[stage][index].getAspirationLevel() * parameters.getWeightingFactorAlpha()
							+ computeChildsMaxAverageUtility(contentsMatrix, childrenStage, childIndex, parameters) * (1 - parameters.getWeightingFactorAlpha()));
					
					childrensExpectedUtilitySum += contentsMatrix[childrenStage][childIndex].getProbabilityProduct() * outcome.getUtility();
					childIndex++;
				}
			}
			expectedUtilities[stage] += childrensExpectedUtilitySum;

			initChildComputation(contentsMatrix, parameters, selectedActions, expectedUtilities,
					childIndex, childrenStage);
		}
	}

	private void initChildComputation(NodeContent[][] contents,
			Parameters parameters, List<ActorAction> selectedActions,
			double[] expectedUtilities, int childIndex, int childrenStage) {
		for(int i=0; i<childIndex; i++){
			computeNextStage(contents, childrenStage, i, parameters, selectedActions, expectedUtilities);
		}
	}

	private double computeChildsMaxAverageUtility(NodeContent[][] contents, int childStage,
			int childIndex, Parameters parameters) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(ActorAction existingAction : parameters.getActorActions()){
			Integer actionOccurances = contents[childStage][childIndex].getNumberOfOccurances().get(existingAction);
			if(actionOccurances>0){
				maxAverageUtility = Math.max(maxAverageUtility, contents[childStage][childIndex].getSumOfUtilities().get(existingAction) / actionOccurances);
			}
		}
		return maxAverageUtility;
	}

	
	private void computeSelectedActions(NodeContent[][] contentsMatrix, Parameters parameters, List<ActorAction> selectedActions, int stage, int index) {
		selectedActions.clear();
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(ActorAction actorAction : parameters.getActorActions()){
			double cumulativePerformance = computeCumulativePerformance(contentsMatrix, actorAction, stage, index);
			if(cumulativePerformance > maxCumulativePerformance){
				selectedActions.clear();
				selectedActions.add(actorAction);
				maxCumulativePerformance = cumulativePerformance;
			} else if(cumulativePerformance == maxCumulativePerformance) {
				selectedActions.add(actorAction);
			}
		}
	}

	private double computeCumulativePerformance(NodeContent[][] contentsMatrix, ActorAction actorAction, int stage, int index) {
		return contentsMatrix[stage][index].getSumOfUtilities().get(actorAction) - 
				contentsMatrix[stage][index].getAspirationLevel() * contentsMatrix[stage][index].getNumberOfOccurances().get(actorAction);
	}
	
	private HashMap<ActorAction, Integer> getInitNumberOfOccurences(Parameters parameters) {
		HashMap<ActorAction, Integer> hashMap = new HashMap<ActorAction,Integer>();
		for(ActorAction action : parameters.getActorActions()){
			hashMap.put(action, 0);
		}
		return hashMap;
	}

	private HashMap<ActorAction,Double> getInitSumOfUtilities(Parameters parameters) {
		HashMap<ActorAction, Double> hashMap = new HashMap<ActorAction,Double>();
		for(ActorAction action : parameters.getActorActions()){
			hashMap.put(action, 0.0);
		}
		return hashMap;
	}
}
