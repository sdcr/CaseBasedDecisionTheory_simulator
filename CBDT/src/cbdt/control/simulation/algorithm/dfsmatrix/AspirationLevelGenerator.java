package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.control.simulation.algorithm.IndexOfAspirationLevelIncrementStageProvider;
import cbdt.model.parameters.Parameters;

/**
 * This class encapsulates the computation of aspiration levels.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class AspirationLevelGenerator {

	int numberOfActorActions;

	private double aspirationLevelDiscountFactor;

	private Parameters parameters;

	private IndexOfAspirationLevelIncrementStageProvider increaseAspirationLevelProvider;

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 */
	public AspirationLevelGenerator(Parameters parameters) {
		this.parameters = parameters;
		this.numberOfActorActions = parameters.getActorActions().size();
		aspirationLevelDiscountFactor = parameters
				.getAspirationLevelDecrementFactor();
		increaseAspirationLevelProvider = new IndexOfAspirationLevelIncrementStageProvider();
	}

	/**
	 * Computes the aspiration level a child node, given the parent and the
	 * child node.
	 * 
	 * @param parentContent
	 * @param childContent
	 * @param indexOfChildrensStage
	 * @return
	 */
	public double calculateChildsAspirationLevel(MatrixNodeContent parentContent,
			MatrixNodeContent childContent, int indexOfChildrensStage) {
		if (parameters.isIncrementingAspirationLevelSparsely()
				&& increaseAspirationLevelProvider
						.isStageToIncreaseAspirationLevel(indexOfChildrensStage))
			return computeChildsMaxAverageUtility(childContent)
					+ parameters.getAspirationLevelIncrement();
		else
			return computeDiscountedAspirationLevel(
					parentContent.aspirationLevel, childContent);
	}

	private double computeDiscountedAspirationLevel(
			double parentAspirationLevel, MatrixNodeContent childsContent) {
		double maxAverageUtility = computeChildsMaxAverageUtility(childsContent);
		return parentAspirationLevel * aspirationLevelDiscountFactor
				+ maxAverageUtility * (1 - aspirationLevelDiscountFactor);
	}

	private double computeChildsMaxAverageUtility(MatrixNodeContent childContent) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for (int existingActionIndex = 0; existingActionIndex < numberOfActorActions; existingActionIndex++) {
			double actionOccurances = childContent.numberOfOccurances[existingActionIndex];
			if (actionOccurances > 0) {
				maxAverageUtility = Math.max(maxAverageUtility,
						childContent.sumOfUtilities[existingActionIndex]
								/ actionOccurances);
			}
		}
		return maxAverageUtility;
	}

}
