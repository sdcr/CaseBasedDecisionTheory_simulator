package cbdt.control.simulation.algorithm.dfskeeptree;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;

public class AspirationLevelGenerator {
	private Parameters parameters;

	public AspirationLevelGenerator(Parameters parameters) {
		this.parameters = parameters;
	}
	
	public double computeChildsAspirationLevel(double parentAspirationLevel, int indexOfChildrensStage, NodeContentKeepTree childsContent) {
		System.out.println(indexOfChildrensStage);
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(ActorAction existingAction : childsContent.getNumberOfOccurances().keySet()){
			Integer actionOccurances = childsContent.getNumberOfOccurances().get(existingAction);
			if(actionOccurances>0){
				double averageUtility = childsContent.getSumOfUtilities().get(existingAction) / actionOccurances;
				maxAverageUtility = Math.max(maxAverageUtility, averageUtility);
			}
		}				
		double childsAspirationLevel = parentAspirationLevel * parameters.getWeightingFactorAlpha()
				+ maxAverageUtility*(1-parameters.getWeightingFactorAlpha());
		return childsAspirationLevel;
	}
}
