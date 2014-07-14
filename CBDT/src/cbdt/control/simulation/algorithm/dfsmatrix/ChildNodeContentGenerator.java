package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

/**
 * This class is able to set the values of children's {@link MatrixNodeContent} object
 * appropriately, according to the parent's {@link MatrixNodeContent}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ChildNodeContentGenerator {

	private ActorActionOutcome[][] outcomeMatrix;
	private int numberOfActorActions;
	private AspirationLevelGenerator aspirationLevelGenerator;

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 * @param outcomeMatrix
	 */
	public ChildNodeContentGenerator(Parameters parameters,
			ActorActionOutcome[][] outcomeMatrix) {
		this.outcomeMatrix = outcomeMatrix;
		numberOfActorActions = outcomeMatrix.length;
		aspirationLevelGenerator = new AspirationLevelGenerator(parameters);
	}

	/**
	 * Sets the child's {@link MatrixNodeContent} object with the correct values.
	 * 
	 * @param parentContent
	 * @param childContent
	 * @param multiActionProbability
	 * @param selectedActionIndex
	 * @param outcomeIndex
	 * @param indexOfChildrensStage
	 */
	public void computeChildContent(MatrixNodeContent parentContent,
			MatrixNodeContent childContent, double multiActionProbability,
			int selectedActionIndex, int outcomeIndex, int indexOfChildrensStage) {
		for (int actionIndex = 0; actionIndex < numberOfActorActions; actionIndex++) {
			childContent.numberOfOccurances[actionIndex] = parentContent.numberOfOccurances[actionIndex];
			childContent.sumOfUtilities[actionIndex] = parentContent.sumOfUtilities[actionIndex];
		}
		childContent.numberOfOccurances[selectedActionIndex] = childContent.numberOfOccurances[selectedActionIndex] + 1;
		childContent.sumOfUtilities[selectedActionIndex] = childContent.sumOfUtilities[selectedActionIndex]
				+ outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
		childContent.probabilityProduct = parentContent.probabilityProduct
				* multiActionProbability
				* outcomeMatrix[selectedActionIndex][outcomeIndex]
						.getProbability();
		childContent.aspirationLevel = aspirationLevelGenerator
				.calculateChildsAspirationLevel(parentContent, childContent,
						indexOfChildrensStage);
	}

}
