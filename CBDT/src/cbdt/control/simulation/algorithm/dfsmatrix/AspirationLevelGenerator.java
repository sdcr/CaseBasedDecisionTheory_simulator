package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.control.simulation.algorithm.IndexOfAspirationLevelIncrementStageProvider;
import cbdt.model.parameters.Parameters;

public class AspirationLevelGenerator {
	
	int numberOfActorActions;

	private double aspirationLevelDiscountFactor;

	private Parameters parameters;

	private IndexOfAspirationLevelIncrementStageProvider increaseAspirationLevelProvider;

	public AspirationLevelGenerator(Parameters parameters) {
		this.parameters = parameters;
		this.numberOfActorActions = parameters.getActorActions().size();
		aspirationLevelDiscountFactor = parameters.getWeightingFactorAlpha();
		increaseAspirationLevelProvider = new IndexOfAspirationLevelIncrementStageProvider();
	}
	
	public double calculateChildsAspirationLevel(NodeContent parentContent, NodeContent childContent, int indexOfChildrensStage) {
		if(parameters.isIcrementingAspirationLevelSparsely() && increaseAspirationLevelProvider.isStageToIncreaseAspirationLevel(indexOfChildrensStage))
			return computeChildsMaxAverageUtility(childContent) + parameters.getAspirationLevelIncrement();
		else
			return computeDiscountedAspirationLevel(parentContent.getAspirationLevel(), childContent);
	}

	private double computeDiscountedAspirationLevel(double parentAspirationLevel,
			NodeContent childsContent) {
		double maxAverageUtility = computeChildsMaxAverageUtility(childsContent);				
		return parentAspirationLevel * aspirationLevelDiscountFactor
				+ maxAverageUtility*(1-aspirationLevelDiscountFactor);
	}
	
	private double computeChildsMaxAverageUtility(NodeContent childContent) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(int existingActionIndex=0; existingActionIndex<numberOfActorActions; existingActionIndex++){
			double actionOccurances = childContent.numberOfOccurances[existingActionIndex];
			if(actionOccurances>0){
				maxAverageUtility = Math.max(maxAverageUtility, childContent.sumOfUtilities[existingActionIndex] / actionOccurances);
			}
		}
		return maxAverageUtility;
	}

}
