package cbdt.control.simulation.algorithm.dfskeeptree;

import cbdt.control.simulation.algorithm.IndexOfAspirationLevelIncrementStageProvider;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.tree.NodeContent;

/**
 * This class encapsulates the computation of a child node's aspiration level.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class AspirationLevelGenerator {
	private Parameters parameters;
	private IndexOfAspirationLevelIncrementStageProvider increaseAspirationLevelProvider;

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 */
	public AspirationLevelGenerator(Parameters parameters) {
		this.parameters = parameters;
		increaseAspirationLevelProvider = new IndexOfAspirationLevelIncrementStageProvider();
	}

	/**
	 * Compute the aspiration level of a child node.
	 * 
	 * @param parentAspirationLevel
	 * @param indexOfChildrensStage
	 * @param childsContent
	 * @return
	 */
	public double computeChildsAspirationLevel(double parentAspirationLevel,
			int indexOfChildrensStage, NodeContent childsContent) {
		if (parameters.isIncrementingAspirationLevelSparsely()
				&& increaseAspirationLevelProvider
						.isStageToIncreaseAspirationLevel(indexOfChildrensStage))
			return calculateMaximalAverageUtility(childsContent)
					+ parameters.getAspirationLevelIncrement();
		else
			return computeDiscountedAspirationLevel(parentAspirationLevel,
					childsContent);
	}

	private double computeDiscountedAspirationLevel(
			double parentAspirationLevel, NodeContent childsContent) {
		double maxAverageUtility = calculateMaximalAverageUtility(childsContent);
		return parentAspirationLevel
				* parameters.getAspirationLevelDecrementFactor()
				+ maxAverageUtility
				* (1 - parameters.getAspirationLevelDecrementFactor());
	}

	private double calculateMaximalAverageUtility(NodeContent childsContent) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for (ActorAction existingAction : childsContent.getNumberOfOccurances()
				.keySet()) {
			Integer actionOccurances = childsContent.getNumberOfOccurances()
					.get(existingAction);
			if (actionOccurances > 0) {
				double averageUtility = childsContent.getSumOfUtilities().get(
						existingAction)
						/ actionOccurances;
				maxAverageUtility = Math.max(maxAverageUtility, averageUtility);
			}
		}
		return maxAverageUtility;
	}

}
