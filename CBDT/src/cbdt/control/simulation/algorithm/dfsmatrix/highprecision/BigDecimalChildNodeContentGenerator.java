package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.result.tree.NodeContent;

/**
 * This class encapsulates the computation of a child's {@link NodeContent}
 * object suited for {@link BigDecimal} computation.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class BigDecimalChildNodeContentGenerator {

	private BigDecimalActorActionOutcome[][] outcomeMatrix;
	private int numberOfActorActions;
	private BigDecimalAspirationLevelGenerator aspirationLevelGenerator;

	/**
	 * Constructor.
	 * 
	 * @param outcomeMatrix
	 * @param aspirationLevelDiscount
	 * @param isUsingAspirationLevelIncrement
	 * @param aspirationLevelIncrement
	 */
	public BigDecimalChildNodeContentGenerator(
			ActorActionOutcome[][] outcomeMatrix,
			BigDecimal aspirationLevelDiscount,
			boolean isUsingAspirationLevelIncrement,
			BigDecimal aspirationLevelIncrement) {
		this.outcomeMatrix = (BigDecimalActorActionOutcome[][]) outcomeMatrix;
		numberOfActorActions = outcomeMatrix.length;
		aspirationLevelGenerator = new BigDecimalAspirationLevelGenerator(
				numberOfActorActions, aspirationLevelDiscount,
				isUsingAspirationLevelIncrement, aspirationLevelIncrement);
	}

	/**
	 * Computes the content of a child node.
	 * 
	 * @param parentContent
	 * @param childContent
	 * @param multiActionProbability
	 * @param selectedActionIndex
	 * @param outcomeIndex
	 * @param indexOfChildrensStage
	 */
	public void computeChildContent(BigDecimalMatrixNodeContent parentContent,
			BigDecimalMatrixNodeContent childContent,
			BigDecimal multiActionProbability, int selectedActionIndex,
			int outcomeIndex, int indexOfChildrensStage) {
		for (int actionIndex = 0; actionIndex < numberOfActorActions; actionIndex++) {
			childContent.numberOfOccurances[actionIndex] = parentContent.numberOfOccurances[actionIndex];
			childContent.sumOfUtilities[actionIndex] = parentContent.sumOfUtilities[actionIndex];
		}
		childContent.numberOfOccurances[selectedActionIndex] = childContent.numberOfOccurances[selectedActionIndex]
				.add(NodeVisitor.big_one, NodeVisitor.mathContext);
		childContent.sumOfUtilities[selectedActionIndex] = childContent.sumOfUtilities[selectedActionIndex]
				.add(outcomeMatrix[selectedActionIndex][outcomeIndex].utility,
						NodeVisitor.mathContext);
		childContent.probabilityProduct = parentContent.probabilityProduct
				.multiply(
						multiActionProbability
								.multiply(
										outcomeMatrix[selectedActionIndex][outcomeIndex].probabilty,
										NodeVisitor.mathContext),
						NodeVisitor.mathContext);
		childContent.aspirationLevel = aspirationLevelGenerator
				.calculateChildsAspirationLevel(parentContent, childContent,
						indexOfChildrensStage);
	}

}
