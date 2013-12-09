package cbdt.control.simulation.algorithm.dfskeeptree;

import cbdt.control.simulation.algorithm.IndexOfAspirationLevelIncrementStageProvider;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;

public class AspirationLevelGenerator {
	private Parameters parameters;
	private IndexOfAspirationLevelIncrementStageProvider increaseAspirationLevelProvider;

	public AspirationLevelGenerator(Parameters parameters) {
		this.parameters = parameters;
		increaseAspirationLevelProvider = 
				new IndexOfAspirationLevelIncrementStageProvider();
	}
	
	public double computeChildsAspirationLevel(double parentAspirationLevel, int indexOfChildrensStage, NodeContentKeepTree childsContent) {
		if(parameters.isIncrementingAspirationLevelSparsely() && increaseAspirationLevelProvider.isStageToIncreaseAspirationLevel(indexOfChildrensStage))
			return calculateMaximalAverageUtility(childsContent) + parameters.getAspirationLevelIncrement();
		else
			return computeDiscountedAspirationLevel(parentAspirationLevel, childsContent);
	}

	private double computeDiscountedAspirationLevel(double parentAspirationLevel,
			NodeContentKeepTree childsContent) {
		double maxAverageUtility = calculateMaximalAverageUtility(childsContent);				
		return parentAspirationLevel * parameters.getAspirationLevelDecrementFactor()
				+ maxAverageUtility*(1-parameters.getAspirationLevelDecrementFactor());
	}

	private double calculateMaximalAverageUtility(NodeContentKeepTree childsContent) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(ActorAction existingAction : childsContent.getNumberOfOccurances().keySet()){
			Integer actionOccurances = childsContent.getNumberOfOccurances().get(existingAction);
			if(actionOccurances>0){
				double averageUtility = childsContent.getSumOfUtilities().get(existingAction) / actionOccurances;
				maxAverageUtility = Math.max(maxAverageUtility, averageUtility);
			}
		}
		return maxAverageUtility;
	}
	
	
}
