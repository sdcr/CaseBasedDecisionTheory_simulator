package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.model.parameters.ActorActionOutcome;

public class BigDecimalChildNodeContentGenerator {

	private BigDecimalActorActionOutcome[][] outcomeMatrix;
	private int numberOfActorActions;
	private BigDecimalAspirationLevelGenerator aspirationLevelGenerator;

	public BigDecimalChildNodeContentGenerator(ActorActionOutcome[][] outcomeMatrix, BigDecimal aspirationLevelDiscount) {
		this.outcomeMatrix = (BigDecimalActorActionOutcome[][])outcomeMatrix;
		numberOfActorActions = outcomeMatrix.length;
		aspirationLevelGenerator = new BigDecimalAspirationLevelGenerator(numberOfActorActions, aspirationLevelDiscount);
	}
	
	public void computeChildContent(BigDecimalNodeContent parentContent, BigDecimalNodeContent childContent, BigDecimal multiActionProbability,
			int selectedActionIndex, int outcomeIndex, int indexOfChildrensStage) {
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
		childContent.aspirationLevel = aspirationLevelGenerator.calculateChildsAspirationLevel(parentContent, childContent, indexOfChildrensStage);
	}



}
