package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.IndexOfAspirationLevelIncrementStageProvider;
import cbdt.control.simulation.algorithm.NodeVisitor;

/**
 * This class encapsulates the computation of aspiration levels suited for {@link BigDecimal}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class BigDecimalAspirationLevelGenerator {

	int numberOfActorActions;

	private BigDecimal aspirationLevelDiscountFactor;

	private boolean isUsingAspirationLevelIncrement;

	private IndexOfAspirationLevelIncrementStageProvider increaseIndexProvider;

	private BigDecimal aspirationLevelIncrement;

	/**
	 * Constructor.
	 * 
	 * @param numberOfActorActions
	 * @param aspirationLevelDiscountFactor
	 * @param isUsingAspirationLevelIncrement
	 * @param aspirationLevelIncrement
	 */
	public BigDecimalAspirationLevelGenerator(int numberOfActorActions, BigDecimal aspirationLevelDiscountFactor, boolean isUsingAspirationLevelIncrement, BigDecimal aspirationLevelIncrement) {
		this.numberOfActorActions = numberOfActorActions;
		this.aspirationLevelDiscountFactor = aspirationLevelDiscountFactor;
		this.isUsingAspirationLevelIncrement = isUsingAspirationLevelIncrement;
		this.aspirationLevelIncrement = aspirationLevelIncrement;
		increaseIndexProvider = new IndexOfAspirationLevelIncrementStageProvider();
	}
	
	/**
	 * Computes the aspiration level of the child node using {@link BigDecimal}.
	 * 
	 * @param parentContent
	 * @param childContent
	 * @param indexOfChildrensStage
	 * @return
	 */
	public BigDecimal calculateChildsAspirationLevel(BigDecimalMatrixNodeContent parentContent,
			BigDecimalMatrixNodeContent childContent, int indexOfChildrensStage) {
		if(isUsingAspirationLevelIncrement && increaseIndexProvider.isStageToIncreaseAspirationLevel(indexOfChildrensStage))
			return computeChildsMaxAverageUtility(childContent).add(aspirationLevelIncrement, NodeVisitor.mathContext);
		else
			return computeDiscountAspirationLevel(parentContent, childContent);
	}

	private BigDecimal computeDiscountAspirationLevel(BigDecimalMatrixNodeContent parentContent,
			BigDecimalMatrixNodeContent childContent) {
		return parentContent.aspirationLevel.multiply(aspirationLevelDiscountFactor, NodeVisitor.mathContext).add(
				computeChildsMaxAverageUtility(childContent).multiply(NodeVisitor.big_one.subtract(aspirationLevelDiscountFactor,
						NodeVisitor.mathContext), NodeVisitor.mathContext), NodeVisitor.mathContext);
	}

	private BigDecimal computeChildsMaxAverageUtility(BigDecimalMatrixNodeContent childContent) {
		BigDecimal maxAverageUtility = new BigDecimal(Integer.MIN_VALUE);
		for(int existingActionIndex=0; existingActionIndex<numberOfActorActions; existingActionIndex++){
			BigDecimal actionOccurances = childContent.numberOfOccurances[existingActionIndex];
			if(actionOccurances.compareTo(new BigDecimal(0))==1){
				BigDecimal possiblyNewMax = childContent.sumOfUtilities[existingActionIndex].divide(actionOccurances, NodeVisitor.mathContext);
				if(maxAverageUtility.compareTo(possiblyNewMax)==-1)
				maxAverageUtility = possiblyNewMax;
			}
		}
		return maxAverageUtility;
	}
	
}
