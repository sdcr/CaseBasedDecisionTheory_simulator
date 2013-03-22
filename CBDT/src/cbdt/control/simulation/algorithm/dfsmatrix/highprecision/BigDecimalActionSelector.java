package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.INodeContent;
import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.control.simulation.algorithm.dfsmatrix.ActionSelector;

public class BigDecimalActionSelector extends ActionSelector {

	public BigDecimalActionSelector(int numberOfActorActions) {
		super(numberOfActorActions);
	}

	@Override
	public void computeSelectedActions(int[] selectedActionsIndices, INodeContent content) {
		for(int i=0; i<selectedActionsIndices.length; i++)
			selectedActionsIndices[i]=-1;
		int j=0;
		BigDecimal maxCumulativePerformance = new BigDecimal(Integer.MIN_VALUE);
		
		for(int actorActionIndex=0; actorActionIndex<numberOfActorActions; actorActionIndex++){
			BigDecimal cumulativePerformance = computeCumulativePerformance(actorActionIndex, (BigDecimalNodeContent)content);
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
			INodeContent icontent) {
		BigDecimalNodeContent content = (BigDecimalNodeContent)icontent;
		return content.sumOfUtilities[actorActionIndex].subtract(content.aspirationLevel.multiply(content.numberOfOccurances[actorActionIndex],NodeVisitor.mathContext), NodeVisitor.mathContext);
	}
}
