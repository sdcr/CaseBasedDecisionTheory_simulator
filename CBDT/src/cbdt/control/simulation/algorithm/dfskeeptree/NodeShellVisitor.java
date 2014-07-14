package cbdt.control.simulation.algorithm.dfskeeptree;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;
import cbdt.model.result.tree.NodeContent;
import cbdt.model.result.tree.NodeShell;

/**
 * This class implements a visitor in a form of the visitor pattern. It can
 * recursively traverse a tree in depth first search and thereby compute all
 * information for the {@link Result}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class NodeShellVisitor extends NodeVisitor {
	private DFSkeepTreeEngineConfig config;
	private Result result;
	private ActionSelector actionSelector;
	private ChildNodeContentGenerator childContentGenerator;
	private IProgressMonitor monitor;
	private CommonConfig commonConfig;

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 * @param config
	 * @param commonConfig
	 * @param result
	 * @param factory
	 * @param monitor
	 */
	public NodeShellVisitor(Parameters parameters,
			DFSkeepTreeEngineConfig config, CommonConfig commonConfig,
			Result result, NodeContentKeepTreeFactory factory,
			IProgressMonitor monitor) {
		this.config = config;
		this.commonConfig = commonConfig;
		this.result = result;
		this.monitor = monitor;
		actionSelector = new ActionSelector(parameters.getActorActions());
		childContentGenerator = new ChildNodeContentGenerator(parameters,
				factory);
	}

	/**
	 * Takes a {@link NodeShell} and computes its children, updates the
	 * children's {@link StageResult}, and recursively calls itself on each of
	 * the children.
	 * 
	 * If the tree is not to be kept for the results, the {@link NodeShell}'s
	 * children are set to null, after each child has been visited.
	 * 
	 * @param nodeShell
	 * @param childrensStage
	 * @throws InterruptedException
	 */
	public void visitRecursively(NodeShell nodeShell, int childrensStage)
			throws InterruptedException {
		if (monitor.isCanceled())
			throw new InterruptedException();
		if (childrensStage <= commonConfig
				.getNumberOfRequestedExpectedUtilityValues()) {
			StageResult childrensStageResult = result.getStageResults().get(
					childrensStage);

			computeChildren(nodeShell, childrensStageResult, childrensStage);

			setContentAccordingToConfig(nodeShell);
			for (NodeShell child : nodeShell.getChildren()) {
				this.visitRecursively(child, childrensStage + 1);
			}
		}

		if (config.isSaveTreeStructure() == false)
			nodeShell.setChildren(null);
	}

	/**
	 * Computes the child nodes of a node, and updates the {@link StageResult}
	 * of the child nodes accordingly.
	 * 
	 * @param parentNodeShell
	 * @param childrensStageResult
	 * @param intexOfChildrensStage
	 */
	public void computeChildren(NodeShell parentNodeShell,
			StageResult childrensStageResult, int intexOfChildrensStage) {
		List<ActorAction> selectedActions = actionSelector
				.computeSelectedActions(parentNodeShell.getContent());
		double multiActionProbability = 1.0 / selectedActions.size();

		double childrensExpectedUtilitySum = 0;

		for (ActorAction selectedAction : selectedActions) {
			for (ActorActionOutcome outcome : selectedAction
					.getActionOutcomes()) {
				NodeContent childsContent = childContentGenerator
						.computeChildContent(parentNodeShell.getContent(),
								multiActionProbability, outcome,
								intexOfChildrensStage);
				parentNodeShell.getChildren().add(new NodeShell(childsContent));
				childrensExpectedUtilitySum += childsContent
						.getProbabilityProduct() * outcome.getUtility();
				if (commonConfig.isCalculateRelativeActionOccurances()) {
					increaseRelativeOccurance(childrensStageResult,
							childsContent.getProbabilityProduct(),
							selectedAction);
				}
				if (commonConfig.isCalculateLowestAspirationLevels()) {
					childrensStageResult.setLowestAspirationLevel(Math.min(
							childrensStageResult.getLowestAspirationLevel(),
							childsContent.getAspirationLevel()));
				}
			}
			if (commonConfig.isCalculateAbsoluteActionOccurances()) {
				increaseAbsoluteOccurance(childrensStageResult, selectedAction);
			}
		}
		childrensStageResult.setExpectedUtility(childrensStageResult
				.getExpectedUtility() + childrensExpectedUtilitySum);
	}

	private void increaseRelativeOccurance(StageResult childrensStageResult,
			Double childsProbilityProduct, ActorAction lastAction) {
		Map<ActorAction, Double> relativeActionOccurances = childrensStageResult
				.getRelativeActionOccurances();
		Double previousRelativeOccurance = relativeActionOccurances
				.get(lastAction);
		if (previousRelativeOccurance == null)
			previousRelativeOccurance = 0.0;
		relativeActionOccurances.put(lastAction, previousRelativeOccurance
				+ childsProbilityProduct);
	}

	private void increaseAbsoluteOccurance(StageResult stageResult,
			ActorAction selectedAction) {
		Map<ActorAction, BigDecimal> absoluteActionOccurances = stageResult
				.getAbsoluteActionOccurances();
		absoluteActionOccurances.put(selectedAction, absoluteActionOccurances
				.get(selectedAction).add(big_one, mathContext));
	}

	private void setContentAccordingToConfig(NodeShell nodeShell) {
		if (config.isSaveActionNames() || config.isSaveAspirationLevels()
				|| config.isSaveTreeStructure()) {
			nodeShell.getContent().setNumberOfOccurances(null);
			nodeShell.getContent().setSumOfUtilities(null);
			if (!config.isSaveActionNames())
				nodeShell.getContent().setLastActionOutcome(null);
		} else {
			nodeShell.setContent(null);
		}
	}

}
