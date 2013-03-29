package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;

public class VirtualNodeContentVisitor extends NodeVisitor {

	private NodeContent[][] contentsMatrix;
	private Double[] expectedUtilities;
	protected int[] selectedActionsIndices;
	protected ActorActionOutcome[][] outcomeMatrix;

	protected int numberOfLeafs;

	protected ActionSelector actionSelector;
	protected BigDecimal[][] absoluteActionOccurances;
	private Double[][] relativeActionOccurances;
	private ChildNodeContentGenerator childContentGenerator;
	protected IProgressMonitor monitor;

	protected Integer leafStage;
	protected Integer progessStage;
	protected CommonEngineConfiguration commonConfig;
	private Double[] lowestAspirationLevels;

	public VirtualNodeContentVisitor(Parameters parameters,
			CommonEngineConfiguration commonConfig,
			NodeContent[][] contentsMatrix, 
			AbstractInitFactory factory,
			Double[] emptyExpectedUtilities,
			BigDecimal[][] absoluteActionOccurances, 
			Double[][] relativeActionOccurances, 
			IProgressMonitor monitor,
			Double[] lowestAspirationLevels) {
		this.commonConfig = commonConfig;
		this.contentsMatrix = contentsMatrix;
		this.expectedUtilities = emptyExpectedUtilities;
		this.absoluteActionOccurances = absoluteActionOccurances;
		this.relativeActionOccurances = relativeActionOccurances;
		
		this.monitor = monitor;
		this.lowestAspirationLevels = lowestAspirationLevels;

		int numberOfActions = parameters.getActorActions().size();
		selectedActionsIndices = new int[numberOfActions];
		actionSelector = new ActionSelector(numberOfActions);
		outcomeMatrix = factory.getOutcomeMatrix();
		childContentGenerator = new ChildNodeContentGenerator(parameters, outcomeMatrix);
	}

	public int calculteNumberOfLeafs(Integer leafStage)
			throws InterruptedException {
		this.leafStage = leafStage;
		this.progessStage = null;
		numberOfLeafs = 0;

		visitRecursively(0, 0);
		return numberOfLeafs;
	}

	public void visitRecursively(int stage, int index, Integer progessStage)
			throws InterruptedException {
		this.progessStage = progessStage;
		this.leafStage = null;
		visitRecursively(stage, index);
	}

	protected void visitRecursively(int stage, int index)
			throws InterruptedException {
		if (monitor.isCanceled())
			throw new InterruptedException();
		if (leafStage != null && leafStage == stage)
			numberOfLeafs++;
		else if (stage < commonConfig
				.getNumberOfRequestedExpectedUtilityValues()) {
			NodeContent parentContent = contentsMatrix[stage][index];
			actionSelector.computeSelectedActions(selectedActionsIndices,
					parentContent);
			int numberOfSelectedActions = getNumberOfSelectedActions();
			double multiActionProbability = 1.0 / numberOfSelectedActions;

			double childrensExpectedUtilitySum = 0;
			int childIndex = 0;
			int childrenStage = stage + 1;
			for (int i = 0; i < numberOfSelectedActions; i++) {
				int selectedActionIndex = selectedActionsIndices[i];

				if (leafStage == null
						&& commonConfig.isCalculateAbsoluteActionOccurances()) //|| commonConfig.isCalculateRelativeActionOccurances()))
					absoluteActionOccurances[stage][selectedActionIndex] = absoluteActionOccurances[stage][selectedActionIndex]
							.add(big_one, mathContext);
				for (int outcomeIndex = 0; outcomeIndex < outcomeMatrix[selectedActionIndex].length; outcomeIndex++) {
					NodeContent childContent = contentsMatrix[childrenStage][childIndex];
					childContentGenerator.computeChildContent(parentContent,
							childContent, multiActionProbability,
							selectedActionIndex, outcomeIndex, stage);

					if (leafStage == null) {
						childrensExpectedUtilitySum += childContent.probabilityProduct
								* outcomeMatrix[selectedActionIndex][outcomeIndex]
										.getUtility();
						if(commonConfig.isCalculateLowestAspirationLevels())
							lowestAspirationLevels[stage] = Math.min(lowestAspirationLevels[stage], childContent.getAspirationLevel());
					}
					
					if (leafStage == null
							&& commonConfig.isCalculateRelativeActionOccurances()) {
						relativeActionOccurances[stage][selectedActionIndex] = relativeActionOccurances[stage][selectedActionIndex]
								+ childContent.probabilityProduct;
					}
					
					childIndex++;
				}
			}
			if (leafStage == null)
				expectedUtilities[stage] += childrensExpectedUtilitySum;

			for (int i = 0; i < childIndex; i++) {
				visitRecursively(childrenStage, i);
			}
			if (progessStage != null && progessStage == stage)
				monitor.worked(1);
		}

	}

	protected int getNumberOfSelectedActions() {
		int numberOfSelectedActions = 0;
		for (int i = 0; i < selectedActionsIndices.length; i++) {
			if (selectedActionsIndices[i] > -1)
				numberOfSelectedActions++;
		}
		return numberOfSelectedActions;
	}
}
