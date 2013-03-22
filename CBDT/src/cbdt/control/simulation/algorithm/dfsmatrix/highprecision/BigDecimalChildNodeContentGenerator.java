package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.model.parameters.ActorActionOutcome;

public class BigDecimalChildNodeContentGenerator {

	private BigDecimalActorActionOutcome[][] outcomeMatrix;
	private int numberOfActorActions;
	private BigDecimal aspirationLevelDiscount;

	public BigDecimalChildNodeContentGenerator(ActorActionOutcome[][] outcomeMatrix, BigDecimal aspirationLevelDiscount) {
		this.outcomeMatrix = (BigDecimalActorActionOutcome[][])outcomeMatrix;
		this.aspirationLevelDiscount = aspirationLevelDiscount;
		numberOfActorActions = outcomeMatrix.length;
	}
	
	public void computeChildContent(BigDecimalNodeContent parentContent, BigDecimalNodeContent childContent, BigDecimal multiActionProbability,
			int selectedActionIndex, int outcomeIndex) {
		for(int actionIndex=0; actionIndex< numberOfActorActions; actionIndex++){
			childContent.numberOfOccurances[actionIndex] = parentContent.numberOfOccurances[actionIndex];
			childContent.sumOfUtilities[actionIndex] = parentContent.sumOfUtilities[actionIndex];
		}
		childContent.numberOfOccurances[selectedActionIndex] = 
				childContent.numberOfOccurances[selectedActionIndex].add(NodeVisitor.big_one, NodeVisitor.mathContext);
		childContent.sumOfUtilities[selectedActionIndex] = 
				childContent.sumOfUtilities[selectedActionIndex].add(outcomeMatrix[selectedActionIndex][outcomeIndex].utility,NodeVisitor.mathContext);
		childContent.probabilityProduct = parentContent.probabilityProduct.multiply(
				multiActionProbability.multiply(
						outcomeMatrix[selectedActionIndex][outcomeIndex].probabilty, NodeVisitor.mathContext), NodeVisitor.mathContext);
		childContent.aspirationLevel = parentContent.aspirationLevel.multiply(aspirationLevelDiscount, NodeVisitor.mathContext).add(
				computeChildsMaxAverageUtility(childContent).multiply(NodeVisitor.big_one.subtract(aspirationLevelDiscount,
						NodeVisitor.mathContext), NodeVisitor.mathContext), NodeVisitor.mathContext);
	}

	private BigDecimal computeChildsMaxAverageUtility(BigDecimalNodeContent childContent) {
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
