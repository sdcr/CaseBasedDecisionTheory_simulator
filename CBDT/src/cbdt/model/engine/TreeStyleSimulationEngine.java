package cbdt.model.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import cbdt.model.Result;
import cbdt.model.TreeNode;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;



public class TreeStyleSimulationEngine implements ISimulationEngine {

	/**
	 * By default limit the simulation to a maximum number of steps.
	 */
	private int maxSimulationSteps = 10;

	/**
	 * The parameters 
	 */
	private Parameters parameters;
	
	@Override
	public Result computeSimulation(Parameters parameters) {
		//test this before casting
		this.parameters = (Parameters) parameters;
		
		//initialize the rootNode
		TreeNode simulationRootNode = new TreeNode();		
		simulationRootNode.setCurrentAstirationLevel(this.parameters.getInitialAspirationLevel());
		simulationRootNode.setActionSelectionProbability(1.0);

		Map<ActorAction,Double> initialCumulativePerformance = new HashMap<ActorAction,Double>();
		for(ActorAction action : this.parameters.getActorActions())
			initialCumulativePerformance.put(action, 0.0);
		simulationRootNode.setCumulativePerformance(initialCumulativePerformance);
		
		//compute the tree
		
		HashMap<ActorAction, List<Double>> pastUtilities = new HashMap<ActorAction, List<Double>>();
		for(ActorAction action : this.parameters.getActorActions())
			pastUtilities.put(action, new ArrayList<Double>());
		computeTree(0, simulationRootNode, pastUtilities);
		Result result = new Result();
		result.setRootNode(simulationRootNode);
		return result;
	}

//	public static int iterationNum = 0;
	
	private void computeTree(int currentTreeHeight, TreeNode sourceNode, Map<ActorAction,List<Double>> pastUtilities){
/*		iterationNum++;
		if(sourceNode.getLastActorActionOutcome() != null)
			System.out.println("iterationNum: "+iterationNum+", Height: "+currentTreeHeight+", lastAction: "+sourceNode.getLastActorActionOutcome().getAction().getActionName()+", aspirationLevel: "+sourceNode.getAstirationLevel());
		else
			System.out.println("iterationNum: "+iterationNum+", Height: "+currentTreeHeight+", lastAction: null, aspirationLevel: "+sourceNode.getAstirationLevel());
*/		
		
		if(currentTreeHeight >= maxSimulationSteps)
			return;
		
		//compute which actions to take
		Set<ActorAction> selectedActions = null;
		Double currentMaxCumulativePerformance = null;
		for(Entry<ActorAction, Double> entry : sourceNode.getCumulativePerformance().entrySet()){
			if(currentMaxCumulativePerformance == null || entry.getValue()>currentMaxCumulativePerformance){
				selectedActions = new HashSet<ActorAction>();
				selectedActions.add(entry.getKey());
				currentMaxCumulativePerformance = entry.getValue();
			} else if(entry.getValue().equals(currentMaxCumulativePerformance)) {
				selectedActions.add(entry.getKey());
			}
		}
		
		//create all childNodes
		for(ActorAction action : selectedActions){
			for(ActorActionOutcome outcome : action.getActionOutcomes()){
				//create new child
				TreeNode newChild = new TreeNode();
				newChild.setParentNode(sourceNode);
				sourceNode.addChildNode(newChild);
				
				//set tree probability resulting from having several possible actions
				newChild.setActionSelectionProbability(1.0/selectedActions.size());
				
				//set last action outcome
				newChild.setLastActorActionOutcome(outcome);
				
				//calculate average utilities
				/*Map<ActorAction,List<Double>> pastUtilities = new HashMap<ActorAction, List<Double>>();
				TreeNode currentNode = newChild;
				while(currentNode.getParentNode()!=null){
					ActorActionOutcome lastOutcome = currentNode.getLastActorActionOutcome();
					if(lastOutcome!=null){
						if(!pastUtilities.containsKey(lastOutcome.getAction()))
							pastUtilities.put(lastOutcome.getAction(), new ArrayList<Double>());
						pastUtilities.get(lastOutcome.getAction()).add(lastOutcome.getUtility());
						currentNode = currentNode.getParentNode();
					}
				}
				*/
				Map<ActorAction,List<Double>> extendedPastUtilities = new HashMap<ActorAction,List<Double>>();
				for(Entry<ActorAction, List<Double>> entry : pastUtilities.entrySet()){
					ArrayList<Double> utilitiesListCopy = new ArrayList<Double>();
					for(Double utility : entry.getValue()){
						utilitiesListCopy.add(new Double(utility));
					}
					extendedPastUtilities.put(entry.getKey(), utilitiesListCopy);
				}
				if(!extendedPastUtilities.containsKey(action))
					extendedPastUtilities.put(action, new ArrayList<Double>());
				extendedPastUtilities.get(action).add(outcome.getUtility());
				
				Map<ActorAction,Double> pastAverageUtilities = new HashMap<ActorAction, Double>();
				Map<ActorAction,Double> pastCumulativeUtilities = new HashMap<ActorAction, Double>();
				for(Entry<ActorAction, List<Double>> entry : extendedPastUtilities.entrySet()){
					double utilitySum = 0;
					for(Double utility : entry.getValue()){
						utilitySum += utility;
					}
					double averageUtility = 0;
					if(entry.getValue().size() > 0)
						averageUtility = utilitySum / entry.getValue().size();
					pastAverageUtilities.put(entry.getKey(), averageUtility);
					pastCumulativeUtilities.put(entry.getKey(), utilitySum);
				}
				
				//calculate maximal average utilities
				double maximalAverageUtility = Double.MIN_VALUE;
				for(Entry<ActorAction, Double> entry : pastAverageUtilities.entrySet()){
					maximalAverageUtility = Math.max(maximalAverageUtility, entry.getValue());
				}
				
				//calculate and set new aspiration level
				double newAspirationLevel = sourceNode.getAstirationLevel() * parameters.getWeightingFactorAlpha()
											+ maximalAverageUtility * (1 - parameters.getWeightingFactorAlpha());
				newChild.setCurrentAstirationLevel(newAspirationLevel);
				
				//calculate and set cumulative performances
				//adjust the cumulative performances with the new aspiration level
				for(Entry<ActorAction, Double> entry : pastCumulativeUtilities.entrySet()){
					entry.setValue(entry.getValue() - newAspirationLevel * extendedPastUtilities.get(entry.getKey()).size());
				}
				newChild.setCumulativePerformance(pastCumulativeUtilities);
				
				//recursively calculate the rest of the tree
				computeTree(currentTreeHeight + 1, newChild, extendedPastUtilities);
			}
		}
	}
	
	public int getMaxSimulationSteps() {
		return maxSimulationSteps;
	}

	public void setMaxSimulationSteps(int maxSimulationSteps) {
		this.maxSimulationSteps = maxSimulationSteps;
	}
	
}
