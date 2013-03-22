package cbdt.control.simulation.algorithm.dfskeeptree;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;

public class ChildNodeContentGenerator {

	private NodeContentKeepTreeFactory factory;
	private double aspirationLevelDiscountFactor;
	
	public ChildNodeContentGenerator(double aspirationLevelDiscountFactor, NodeContentKeepTreeFactory factory) {
		this.aspirationLevelDiscountFactor = aspirationLevelDiscountFactor;
		this.factory = factory;
	}
	
	public NodeContentKeepTree computeChildContent(NodeContentKeepTree parentContent,
			double multiActionProbability, ActorActionOutcome outcome) {
		NodeContentKeepTree childsContent = factory.getCopy(parentContent);
		
		ActorAction selectedAction = outcome.getAction();
		childsContent.setProbabilityProduct(parentContent.getProbabilityProduct() 
				* multiActionProbability * outcome.getProbability());
		childsContent.getNumberOfOccurances().put(selectedAction, parentContent.getNumberOfOccurances().get(selectedAction) + 1);
		childsContent.getSumOfUtilities().put(selectedAction, parentContent.getSumOfUtilities().get(selectedAction) + outcome.getUtility());
		childsContent.setLastAction(selectedAction);
		
		double childsAspirationLevel = computeChildsAspirationLevel(parentContent.getAspirationLevel(), childsContent);
		childsContent.setAspirationLevel(childsAspirationLevel);
		return childsContent;
	}

	private double computeChildsAspirationLevel(double parentAspirationLevel, NodeContentKeepTree childsContent) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(ActorAction existingAction : childsContent.getNumberOfOccurances().keySet()){
			Integer actionOccurances = childsContent.getNumberOfOccurances().get(existingAction);
			if(actionOccurances>0){
				double averageUtility = childsContent.getSumOfUtilities().get(existingAction) / actionOccurances;
				maxAverageUtility = Math.max(maxAverageUtility, averageUtility);
			}
		}				
		double childsAspirationLevel = parentAspirationLevel * aspirationLevelDiscountFactor
				+ maxAverageUtility*(1-aspirationLevelDiscountFactor);
		return childsAspirationLevel;
	}
}
