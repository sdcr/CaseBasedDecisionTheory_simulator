package cbdt.control.simulation.algorithm.dfsmatrix;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;

public class VirtualNodeContentVisitor extends NodeVisitor {

	protected int[] selectedActionsIndices;
	protected ActorActionOutcome[][] outcomeMatrix;

	protected int numberOfLeafs;

	protected ActionSelector actionSelector;
	private ChildNodeContentGenerator childContentGenerator;
	protected IProgressMonitor monitor;

	protected Integer leafStage;
	protected Integer progessStage;
	protected CommonEngineConfiguration commonConfig;
	private SimulationState simState;

	public VirtualNodeContentVisitor(Parameters parameters,
			CommonEngineConfiguration commonConfig, IProgressMonitor monitor,
			SimulationState simState) {
		this.commonConfig = commonConfig;
		this.monitor = monitor;
		this.simState = simState;

		int numberOfActions = parameters.getActorActions().size();
		selectedActionsIndices = new int[numberOfActions];
		actionSelector = new ActionSelector(numberOfActions);
		
		AbstractInitFactory factory = new BasicInitFactory(parameters, commonConfig);
		outcomeMatrix = factory .getOutcomeMatrix();
		childContentGenerator = new ChildNodeContentGenerator(parameters,
				outcomeMatrix);
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
			NodeContent parentContent = simState.contentsMatrix[stage][index];
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
					simState.absoluteActionOccurances[childrenStage][selectedActionIndex] = simState.absoluteActionOccurances[childrenStage][selectedActionIndex]
							.add(big_one, mathContext);
				for (int outcomeIndex = 0; outcomeIndex < outcomeMatrix[selectedActionIndex].length; outcomeIndex++) {
					NodeContent childContent = simState.contentsMatrix[childrenStage][childIndex];
					childContentGenerator.computeChildContent(parentContent,
							childContent, multiActionProbability,
							selectedActionIndex, outcomeIndex, childrenStage);

					if (leafStage == null) {
						childrensExpectedUtilitySum += childContent.probabilityProduct
								* outcomeMatrix[selectedActionIndex][outcomeIndex]
										.getUtility();
						if(commonConfig.isCalculateLowestAspirationLevels())
							simState.lowestAspirationLevels[childrenStage] = Math.min(simState.lowestAspirationLevels[childrenStage], childContent.getAspirationLevel());
						if (commonConfig.isCalculateRelativeActionOccurances()) {
							simState.relativeActionOccurances[childrenStage][selectedActionIndex] = simState.relativeActionOccurances[childrenStage][selectedActionIndex]
									+ childContent.probabilityProduct;
						}
					}
					
					childIndex++;
				}
			}
			if (leafStage == null)
				simState.expectedUtilities[childrenStage] += childrensExpectedUtilitySum;

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
