package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.HashMap;
import java.util.Map;

import cbdt.model.parameters.ActorAction;

public class NodeContent {
	
	private double probabilityProduct;
	
	private Map<ActorAction,Integer> numberOfOccurances;
	
	private Map<ActorAction,Double> sumOfUtilities;
	
	private double aspirationLevel;

	private ActorAction lastAction;
	
	public NodeContent getCopy(){
		NodeContent copy = new NodeContent();
		copy.setAspirationLevel(aspirationLevel);

		HashMap<ActorAction,Integer> copysNumberOfOccurances = new HashMap<ActorAction,Integer>();
		HashMap<ActorAction,Double> copysSumOfUtilities = new HashMap<ActorAction,Double>();
		for(ActorAction actorAction : numberOfOccurances.keySet()){
			copysNumberOfOccurances.put(actorAction, numberOfOccurances.get(actorAction));
			copysSumOfUtilities.put(actorAction, sumOfUtilities.get(actorAction));
		}
		
		copy.setNumberOfOccurances(copysNumberOfOccurances);
		copy.setSumOfUtilities(copysSumOfUtilities);
		copy.setProbabilityProduct(probabilityProduct);
		return copy;
	}
	
	public double getProbabilityProduct() {
		return probabilityProduct;
	}

	public void setProbabilityProduct(double probabilityProduct) {
		this.probabilityProduct = probabilityProduct;
	}

	public Map<ActorAction, Integer> getNumberOfOccurances() {
		return numberOfOccurances;
	}

	public void setNumberOfOccurances(Map<ActorAction, Integer> numberOfOccurances) {
		this.numberOfOccurances = numberOfOccurances;
	}

	public Map<ActorAction, Double> getSumOfUtilities() {
		return sumOfUtilities;
	}

	public void setSumOfUtilities(Map<ActorAction, Double> sumOfUtilities) {
		this.sumOfUtilities = sumOfUtilities;
	}

	public double getAspirationLevel() {
		return aspirationLevel;
	}

	public void setAspirationLevel(double aspirationLevel) {
		this.aspirationLevel = aspirationLevel;
	}

	public ActorAction getLastAction() {
		return lastAction;
	}

	public void setLastAction(ActorAction lastAction) {
		this.lastAction = lastAction;
	}
}
