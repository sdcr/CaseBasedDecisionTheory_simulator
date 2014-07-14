package cbdt.control.simulation.algorithm.dfsmatrix;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShellVisitor;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

/**
 * This class works similar to the {@link NodeShellVisitor} in the DFSkeeptree
 * algorithm. It is called 'virtual', because it does not actually traverse a
 * tree. Instead, at every point in the computation the current state of the
 * tree is represented by matrixes in the {@link SimulationState} object. This
 * class works on this simulation state as if it traversed the tree in Depth
 * First Search manner.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class VirtualNodeContentVisitor extends NodeVisitor {

	protected int[] selectedActionsIndices;
	protected ActorActionOutcome[][] outcomeMatrix;

	protected int numberOfLeafs;

	protected ActionSelector actionSelector;
	private ChildNodeContentGenerator childContentGenerator;
	protected IProgressMonitor monitor;

	protected Integer leafStage;
	protected Integer progressStage;
	protected CommonConfig commonConfig;
	private SimulationState simState;

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 * @param commonConfig
	 * @param monitor
	 * @param simState
	 */
	public VirtualNodeContentVisitor(Parameters parameters,
			CommonConfig commonConfig, IProgressMonitor monitor,
			SimulationState simState) {
		this.commonConfig = commonConfig;
		this.monitor = monitor;
		this.simState = simState;

		int numberOfActions = parameters.getActorActions().size();
		selectedActionsIndices = new int[numberOfActions];
		actionSelector = new ActionSelector(numberOfActions);

		AbstractInitFactory factory = new IntDoubleInitFactory(parameters,
				commonConfig);
		outcomeMatrix = factory.getOutcomeMatrix();
		childContentGenerator = new ChildNodeContentGenerator(parameters,
				outcomeMatrix);
	}

	/**
	 * Computes the number of leafs in a stage.
	 * 
	 * @param leafStage
	 * @return
	 * @throws InterruptedException
	 */
	public int calculteNumberOfLeafs(Integer leafStage)
			throws InterruptedException {
		this.leafStage = leafStage;
		this.progressStage = null;
		numberOfLeafs = 0;

		visitRecursively(0, 0);
		return numberOfLeafs;
	}

	/**
	 * Calls {@link VirtualNodeContentVisitor}
	 * {@link #visitRecursively(int, int)}, but sets the index of a progress
	 * stage. When ever a node in the progress stage is completed the monitor is
	 * increased by one step.
	 * 
	 * @param stage
	 * @param index
	 * @param progressStage
	 * @throws InterruptedException
	 */
	public void visitRecursively(int stage, int index, Integer progressStage)
			throws InterruptedException {
		this.progressStage = progressStage;
		this.leafStage = null;
		visitRecursively(stage, index);
	}

	/**
	 * Visits a subtree recursively.
	 * 
	 * @param stage
	 *            The stage of the node to visit next.
	 * @param index
	 *            In the matrix representations of {@link SimulationState}, the
	 *            index of a node in the matrix.
	 * @throws InterruptedException
	 */
	protected void visitRecursively(int stage, int index)
			throws InterruptedException {
		if (monitor.isCanceled())
			throw new InterruptedException();
		if (leafStage != null && leafStage == stage)
			numberOfLeafs++;
		else if (stage < commonConfig
				.getNumberOfRequestedExpectedUtilityValues()) {
			MatrixNodeContent parentContent = simState.contentsMatrix[stage][index];
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
						&& commonConfig.isCalculateAbsoluteActionOccurances()) {
					simState.absoluteActionOccurances[childrenStage][selectedActionIndex] = simState.absoluteActionOccurances[childrenStage][selectedActionIndex]
							.add(big_one, mathContext);
				}
				for (int outcomeIndex = 0; outcomeIndex < outcomeMatrix[selectedActionIndex].length; outcomeIndex++) {
					MatrixNodeContent childContent = simState.contentsMatrix[childrenStage][childIndex];
					childContentGenerator.computeChildContent(parentContent,
							childContent, multiActionProbability,
							selectedActionIndex, outcomeIndex, childrenStage);

					if (leafStage == null) {
						childrensExpectedUtilitySum += childContent.probabilityProduct
								* outcomeMatrix[selectedActionIndex][outcomeIndex]
										.getUtility();
						if (commonConfig.isCalculateLowestAspirationLevels())
							simState.lowestAspirationLevels[childrenStage] = Math
									.min(simState.lowestAspirationLevels[childrenStage],
											childContent.aspirationLevel);
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
			if (progressStage != null && progressStage == stage)
				monitor.worked(1);
		}
	}

	/**
	 * @return The number of selected actions.
	 */
	protected int getNumberOfSelectedActions() {
		int numberOfSelectedActions = 0;
		for (int i = 0; i < selectedActionsIndices.length; i++) {
			if (selectedActionsIndices[i] > -1)
				numberOfSelectedActions++;
		}
		return numberOfSelectedActions;
	}
}
