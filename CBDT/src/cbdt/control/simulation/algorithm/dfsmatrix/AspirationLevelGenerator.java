package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.model.parameters.Parameters;

public class AspirationLevelGenerator {
	
	int numberOfActorActions;

	private double aspirationLevelDiscountFactor;

	public AspirationLevelGenerator(Parameters parameters) {
		this.numberOfActorActions = parameters.getActorActions().size();
		aspirationLevelDiscountFactor = parameters.getWeightingFactorAlpha();
	}
	
	public double calculateChildsAspirationLevel(NodeContent parentContent, NodeContent childContent) {
		double childsAspirationLevel = parentContent.aspirationLevel * aspirationLevelDiscountFactor
				+ (computeChildsMaxAverageUtility(childContent) * (1 - aspirationLevelDiscountFactor));
		return childsAspirationLevel;
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
