package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.control.simulation.algorithm.dfsmatrix.ActionSelector;
import cbdt.control.simulation.algorithm.dfsmatrix.IAlgoNodeContent;

/**
 * A subclass of {@link ActionSelector} suited for computation with {@link BigDecimal}.
 * 
 * @author Stephan da Costa Ribeiro
 *
 */
public class BigDecimalActionSelector extends ActionSelector {

	/**
	 * Constructor.
	 * 
	 * @param numberOfActorActions
	 */
	public BigDecimalActionSelector(int numberOfActorActions) {
		super(numberOfActorActions);
	}

	@Override
	public void computeSelectedActions(int[] selectedActionsIndices, IAlgoNodeContent content) {
		for(int i=0; i<selectedActionsIndices.length; i++)
			selectedActionsIndices[i]=-1;
		int j=0;
		BigDecimal maxCumulativePerformance = new BigDecimal(Integer.MIN_VALUE);
		
		for(int actorActionIndex=0; actorActionIndex<numberOfActorActions; actorActionIndex++){
			BigDecimal cumulativePerformance = computeCumulativePerformance(actorActionIndex, (BigDecimalMatrixNodeContent)content);
			if(cumulativePerformance.compareTo(maxCumulativePerformance)==1){
				for(int i=0; i<selectedActionsIndices.length; i++)
					selectedActionsIndices[i]=-1;
				j=0;
				selectedActionsIndices[j] = actorActionIndex;
				maxCumulativePerformance = cumulativePerformance;
			} else if(cumulativePerformance.equals(maxCumulativePerformance)) {
				j++;
				selectedActionsIndices[j] = actorActionIndex;
			}
		}
	}
	
	private BigDecimal computeCumulativePerformance(int actorActionIndex,
			IAlgoNodeContent icontent) {
		BigDecimalMatrixNodeContent content = (BigDecimalMatrixNodeContent)icontent;
		return content.sumOfUtilities[actorActionIndex].subtract(content.aspirationLevel.multiply(content.numberOfOccurances[actorActionIndex],NodeVisitor.mathContext), NodeVisitor.mathContext);
	}
}
