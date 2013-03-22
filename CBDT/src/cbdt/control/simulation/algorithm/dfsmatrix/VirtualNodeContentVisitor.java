package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.Visitor;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;


public class VirtualNodeContentVisitor extends Visitor{

	private NodeContent[][] contentsMatrix;
	private double[] expectedUtilities;
	private int[] selectedActionsIndices;

	private ActorActionOutcome[][] outcomeMatrix;
	
	private ActionSelector actionSelector;
	private AbstractEngineConfiguration config;
	private BigDecimal[][] absoluteActionOccurances;
	private ChildNodeContentGenerator childContentGenerator;
	
	public VirtualNodeContentVisitor(Parameters parameters, AbstractEngineConfiguration config, NodeContent[][] contentsMatrix, InitFactory factory, 
			double[] emptyExpectedUtilities, BigDecimal[][] absoluteActionOccurances) {
		this.config = config;
		this.contentsMatrix = contentsMatrix;
		this.expectedUtilities = emptyExpectedUtilities;
		this.absoluteActionOccurances = absoluteActionOccurances;
		
		actionSelector = new ActionSelector(parameters.getActorActions().size());
		outcomeMatrix = factory.getOutcomeMatrix();
		childContentGenerator = new ChildNodeContentGenerator(outcomeMatrix, parameters.getWeightingFactorAlpha());
	}

	public void visitRecursively(int stage, int index) {
		if(stage < config.getNumberOfRequestedExpectedUtilityValues()){
			NodeContent parentContent = contentsMatrix[stage][index];
			actionSelector.computeSelectedActions(selectedActionsIndices, parentContent);
			int numberOfSelectedActions = getNumberOfSelectedActions();
			double multiActionProbability = 1.0 / numberOfSelectedActions;
			
			double childrensExpectedUtilitySum = 0;
			int childIndex = 0;
			int childrenStage = stage+1;
			for(int i=0; i<numberOfSelectedActions; i++){
				int selectedActionIndex = selectedActionsIndices[i];

				absoluteActionOccurances[stage][selectedActionIndex] = absoluteActionOccurances[stage][selectedActionIndex].add(big_one, mathContext);
				for(int outcomeIndex=0; outcomeIndex<outcomeMatrix[selectedActionIndex].length; outcomeIndex++){
					NodeContent childContent = contentsMatrix[childrenStage][childIndex];
					childContentGenerator.computeChildContent(parentContent, childContent, multiActionProbability, selectedActionIndex, outcomeIndex);
					
					childrensExpectedUtilitySum += childContent.probabilityProduct * outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
					childIndex++;
				}
			}
			expectedUtilities[stage] += childrensExpectedUtilitySum;

			for(int i=0; i<childIndex; i++){
				visitRecursively(childrenStage, i);
			}
		}
		
	}

	private int getNumberOfSelectedActions() {
		int numberOfSelectedActions = 0;
		for (int i = 0; i < selectedActionsIndices.length; i++) {
			if(selectedActionsIndices[i]>-1)
				numberOfSelectedActions++;
		}
		return numberOfSelectedActions;
	}
}
