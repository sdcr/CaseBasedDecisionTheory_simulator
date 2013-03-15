package cbdt.control.algorithm.dfslists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class DFSlistsStyleSimulationEngine {

	public double[] computeExpectedUtilities(Parameters parameters){
		List<List<NodeContent>> contents = new ArrayList<List<NodeContent>>();
		
		//root element in contens
		ArrayList<NodeContent> firstList = new ArrayList<NodeContent>();
		NodeContent rootContent = new NodeContent();
		rootContent.setNumberOfOccurances(getInitNumberOfOccurences(parameters));
		rootContent.setSumOfUtilities(getInitSumOfUtilities(parameters));
		rootContent.setAspirationLevel(parameters.getInitialAspirationLevel());
		rootContent.setProbabilityProduct(1.0);		
		firstList.add(rootContent);
		contents.add(firstList);
		//children elements in contents
		initContents(parameters, contents);
		
		List<ActorAction> selectedActions = new ArrayList<ActorAction>();
		double[] expectedUtilities = new double[parameters.getNumberOfRequestedExpectedUtilities()];
		computeNextStage(contents, 0, 0, parameters, selectedActions, expectedUtilities);
		
		return expectedUtilities;
	}

	private void initContents(Parameters parameters,
			List<List<NodeContent>> contents) {
		for(int i=0; i<parameters.getNumberOfRequestedExpectedUtilities(); i++){
			ArrayList<NodeContent> stageList = new ArrayList<NodeContent>();
			for(ActorAction action : parameters.getActorActions()){
				for(ActorActionOutcome outcome : action.getActionOutcomes()){
					NodeContent nodeContent = new NodeContent();
					nodeContent.setNumberOfOccurances(getInitNumberOfOccurences(parameters));
					nodeContent.setSumOfUtilities(getInitSumOfUtilities(parameters));
					stageList.add(nodeContent);
				}
			}
			contents.add(stageList);
		}
	}
	
	private void computeNextStage(List<List<NodeContent>> contents, int stage,
			int index, Parameters parameters, List<ActorAction> selectedActions, double[] expectedUtilities) {
		if(stage < parameters.getNumberOfRequestedExpectedUtilities()){
			computeSelectedActions(contents, parameters, selectedActions, stage, index);
			double multiActionProbability = 1.0 / selectedActions.size();
			
			double childrensExpectedUtilitySum = 0;
			int childIndex = 0;
			int childrenStage = stage+1;
			for(ActorAction selectedAction : selectedActions){
				for(ActorActionOutcome outcome : selectedAction.getActionOutcomes()){
					for(ActorAction action : parameters.getActorActions()){
						contents.get(childrenStage).get(childIndex).getNumberOfOccurances().put(action, contents.get(stage).get(index).getNumberOfOccurances().get(action));
						contents.get(childrenStage).get(childIndex).getSumOfUtilities().put(action, contents.get(stage).get(index).getSumOfUtilities().get(action));						
					}
					contents.get(childrenStage).get(childIndex).getNumberOfOccurances().put(selectedAction, 
							contents.get(childrenStage).get(childIndex).getNumberOfOccurances().get(selectedAction)+1);
					contents.get(childrenStage).get(childIndex).getSumOfUtilities().put(selectedAction, 
							contents.get(childrenStage).get(childIndex).getSumOfUtilities().get(selectedAction)+outcome.getUtility());
					contents.get(childrenStage).get(childIndex).setProbabilityProduct(contents.get(stage).get(index).getProbabilityProduct() * multiActionProbability * outcome.getProbability());
					contents.get(childrenStage).get(childIndex).setAspirationLevel(contents.get(stage).get(index).getAspirationLevel() * parameters.getWeightingFactorAlpha()
							+ (computeChildsMaxAverageUtility(contents, childrenStage, childIndex, parameters) * (1 - parameters.getWeightingFactorAlpha())));
					
					childrensExpectedUtilitySum += contents.get(childrenStage).get(childIndex).getProbabilityProduct() * outcome.getUtility();
					childIndex++;
				}
			}
			expectedUtilities[stage] += childrensExpectedUtilitySum;

			initChildComputation(contents, parameters, selectedActions, expectedUtilities,
					childIndex, childrenStage);
		}
	}

	private void initChildComputation(List<List<NodeContent>> contents,
			Parameters parameters, List<ActorAction> selectedActions,
			double[] expectedUtilities, int childIndex, int childrenStage) {
		for(int i=0; i<childIndex; i++){
			computeNextStage(contents, childrenStage, i, parameters, selectedActions, expectedUtilities);
		}
	}

	private double computeChildsMaxAverageUtility(List<List<NodeContent>> contents, int childStage,
			int childIndex, Parameters parameters) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(ActorAction existingAction : parameters.getActorActions()){
			Integer actionOccurances = contents.get(childStage).get(childIndex).getNumberOfOccurances().get(existingAction);
			if(actionOccurances>0){
				maxAverageUtility = Math.max(maxAverageUtility, contents.get(childStage).get(childIndex).getSumOfUtilities().get(existingAction) / actionOccurances);
			}
		}
		return maxAverageUtility;
	}

	
	private void computeSelectedActions(List<List<NodeContent>> contents, Parameters parameters, List<ActorAction> selectedActions, int stage, int index) {
		selectedActions.clear();
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(ActorAction actorAction : parameters.getActorActions()){
			double cumulativePerformance = computeCumulativePerformance(contents, actorAction, stage, index);
			if(cumulativePerformance > maxCumulativePerformance){
				selectedActions.clear();
				selectedActions.add(actorAction);
				maxCumulativePerformance = cumulativePerformance;
			} else if(cumulativePerformance == maxCumulativePerformance) {
				selectedActions.add(actorAction);
			}
		}
	}

	private double computeCumulativePerformance(List<List<NodeContent>> contents, ActorAction actorAction, int stage, int index) {
		return contents.get(stage).get(index).getSumOfUtilities().get(actorAction) - 
				contents.get(stage).get(index).getAspirationLevel() * contents.get(stage).get(index).getNumberOfOccurances().get(actorAction);
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
