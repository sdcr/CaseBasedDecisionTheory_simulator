package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.Visitor;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public class VirtualNodeContentVisitor extends Visitor {

	private NodeContent[][] contentsMatrix;
	private double[] expectedUtilities;
	private int[] selectedActionsIndices;
	private ActorActionOutcome[][] outcomeMatrix;
	
	private int numberOfLeafs;

	private ActionSelector actionSelector;
	private AbstractEngineConfiguration config;
	private BigDecimal[][] absoluteActionOccurances;
	private ChildNodeContentGenerator childContentGenerator;
	private IProgressMonitor monitor;
	
	private Integer leafCountStage;
	private Integer progessStage;

	public VirtualNodeContentVisitor(Parameters parameters,
			AbstractEngineConfiguration config, NodeContent[][] contentsMatrix,
			InitFactory factory, double[] emptyExpectedUtilities,
			BigDecimal[][] absoluteActionOccurances, IProgressMonitor monitor) {
		this.config = config;
		this.contentsMatrix = contentsMatrix;
		this.expectedUtilities = emptyExpectedUtilities;
		this.absoluteActionOccurances = absoluteActionOccurances;
		this.monitor = monitor;

		int numberOfActions = parameters.getActorActions().size();
		selectedActionsIndices = new int[numberOfActions];
		actionSelector = new ActionSelector(numberOfActions);
		outcomeMatrix = factory.getOutcomeMatrix();
		childContentGenerator = new ChildNodeContentGenerator(outcomeMatrix,
				parameters.getWeightingFactorAlpha());
	}
	
	public int calculteNumberOfLeafs(Integer leafCountStage) throws InterruptedException{
		this.leafCountStage = leafCountStage;
		this.progessStage = null;
		numberOfLeafs = 0;
		
		visitRecursively(0, 0);
		return numberOfLeafs;
	}
	
	public void visitRecursively(int stage, int index, Integer progessStage) throws InterruptedException{
		this.progessStage = progessStage;
		this.leafCountStage = null;
		visitRecursively(stage, index);
	}
	
	private void visitRecursively(int stage, int index) throws InterruptedException {
		if(monitor.isCanceled())
			throw new InterruptedException();
		if(leafCountStage!=null && leafCountStage==stage)
			numberOfLeafs++;
		else if (stage < config.getNumberOfRequestedExpectedUtilityValues()) {
			NodeContent parentContent = contentsMatrix[stage][index];
			actionSelector.computeSelectedActions(selectedActionsIndices, parentContent);
			int numberOfSelectedActions = getNumberOfSelectedActions();
			double multiActionProbability = 1.0 / numberOfSelectedActions;

			double childrensExpectedUtilitySum = 0;
			int childIndex = 0;
			int childrenStage = stage + 1;
			for (int i = 0; i < numberOfSelectedActions; i++) {
				int selectedActionIndex = selectedActionsIndices[i];

				if (leafCountStage==null && (config.isCalculateAbsoluteActionOccurances()
						|| config.isCalculateRelativeActionOccurances()))
					absoluteActionOccurances[stage][selectedActionIndex] = absoluteActionOccurances[stage][selectedActionIndex]
							.add(big_one, mathContext);
				for (int outcomeIndex = 0; outcomeIndex < outcomeMatrix[selectedActionIndex].length; outcomeIndex++) {
					NodeContent childContent = contentsMatrix[childrenStage][childIndex];
					childContentGenerator.computeChildContent(parentContent,
							childContent, multiActionProbability,
							selectedActionIndex, outcomeIndex);

					if(leafCountStage==null){
						childrensExpectedUtilitySum += childContent.probabilityProduct
								* outcomeMatrix[selectedActionIndex][outcomeIndex].getUtility();
					}
					childIndex++;
				}
			}
			if(leafCountStage==null)
				expectedUtilities[stage] += childrensExpectedUtilitySum;

			for (int i = 0; i < childIndex; i++) {
				visitRecursively(childrenStage, i);
			}
			if(progessStage!=null && progessStage==stage)
				monitor.worked(1);
		}

	}

	private int getNumberOfSelectedActions() {
		int numberOfSelectedActions = 0;
		for (int i = 0; i < selectedActionsIndices.length; i++) {
			if (selectedActionsIndices[i] > -1)
				numberOfSelectedActions++;
		}
		return numberOfSelectedActions;
	}
}
