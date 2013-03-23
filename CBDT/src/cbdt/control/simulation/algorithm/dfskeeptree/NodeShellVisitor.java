package cbdt.control.simulation.algorithm.dfskeeptree;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.control.simulation.algorithm.NodeVisitor;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class NodeShellVisitor extends NodeVisitor {
	private DFSkeepTreeEngineConfig config;
	private Result result;
	private ActionSelector actionSelector;
	private ChildNodeContentGenerator childContentGenerator;
	private IProgressMonitor monitor;
	private CommonEngineConfiguration commonConfig;

	public NodeShellVisitor(Parameters parameters, DFSkeepTreeEngineConfig config, CommonEngineConfiguration commonConfig,
			Result result, NodeContentKeepTreeFactory factory, IProgressMonitor monitor) {
		this.config = config;
		this.commonConfig = commonConfig;
		this.result = result;
		this.monitor = monitor;
		actionSelector = new ActionSelector(parameters.getActorActions());
		childContentGenerator = new ChildNodeContentGenerator(parameters.getWeightingFactorAlpha(), factory);
	}

	public void visitRecursively(NodeShell nodeShell, int childrensStage) throws InterruptedException{
		if(monitor.isCanceled())
			throw new InterruptedException();
		if(childrensStage < commonConfig.getNumberOfRequestedExpectedUtilityValues()) {
			StageResult childrensStageResult = result.getStageResults().get(childrensStage);

			computeChildren(nodeShell, childrensStageResult);
			
			setContentAccordingToConfig(nodeShell);
			for(NodeShell child : nodeShell.getChildren()){
				this.visitRecursively(child, childrensStage+1);
			}
		}
		
		if(config.isSaveTreeStructure() == false)
			nodeShell.setChildren(null);
	}
	
	public void computeChildren(NodeShell nodeShell, StageResult childrensStageResult) {
		List<ActorAction> selectedActions = actionSelector.computeSelectedActions(nodeShell.getContent());
		double multiActionProbability = 1.0 / selectedActions.size();
		
		double childrensExpectedUtilitySum = 0;
		
		for(ActorAction selectedAction : selectedActions){
			for(ActorActionOutcome outcome : selectedAction.getActionOutcomes()){
				NodeContentKeepTree childsContent = childContentGenerator.computeChildContent( 
						nodeShell.getContent(), multiActionProbability, outcome);					
				nodeShell.getChildren().add(new NodeShell(childsContent));
				childrensExpectedUtilitySum += childsContent.getProbabilityProduct() * outcome.getUtility();
			}
			if(commonConfig.isCalculateAbsoluteActionOccurances() || commonConfig.isCalculateRelativeActionOccurances()) {
				increaseAbsoluteOccurance(childrensStageResult, selectedAction);
			}
		}
		childrensStageResult.setExpectedUtility(childrensStageResult.getExpectedUtility() +childrensExpectedUtilitySum);
	}

	private void increaseAbsoluteOccurance(StageResult stageResult, ActorAction selectedAction) {
		Map<ActorAction, BigDecimal> absoluteActionOccurances = stageResult.getAbsoluteActionOccurances();
		absoluteActionOccurances.put(selectedAction,
				absoluteActionOccurances.get(selectedAction).add(big_one, mathContext));
	}

	private void setContentAccordingToConfig(NodeShell nodeShell) {
		if (config.isSaveActionNames() || config.isSaveAspirationLevels() || config.isSaveTreeStructure()) {
			nodeShell.getContent().setNumberOfOccurances(null);
			nodeShell.getContent().setSumOfUtilities(null);
			if (!config.isSaveActionNames())
				nodeShell.getContent().setLastAction(null);
		} else {
			nodeShell.setContent(null);
		}
	}

}