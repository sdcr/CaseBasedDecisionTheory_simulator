package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class NodeShellVisitor {
	private DFSkeepTreeEngineConfig config;
	private Result result;
	private ActionSelector actionSelector;
	private ChildNodeContentGenerator childContentGenerator;

	public NodeShellVisitor(Parameters parameters, DFSkeepTreeEngineConfig config, Result result,
			NodeContentFactory factory) {
		this.config = config;
		this.result = result;
		actionSelector = new ActionSelector(parameters.getActorActions());
		childContentGenerator = new ChildNodeContentGenerator(parameters.getWeightingFactorAlpha(), factory);
	}

	public void visitRecursively(NodeShellKeepTree nodeShell, int childrensStage){
		if(childrensStage < config.getNumberOfRequestedExpectedUtilityValues()) {
			StageResult childrensStageResult = result.getStageResults().get(childrensStage);

			computeChildren(nodeShell, childrensStageResult);
			
			setContentAccordingToConfig(nodeShell);
			for(NodeShellKeepTree child : nodeShell.getChildren()){
				this.visitRecursively(child, childrensStage+1);
			}
		}
		
		if(config.isSaveTreeStructure() == false)
			nodeShell.setChildren(null);
	}
	
	public void computeChildren(NodeShellKeepTree nodeShell, StageResult childrensStageResult) {
		List<ActorAction> selectedActions = actionSelector.computeSelectedActions(nodeShell.getContent());
		double multiActionProbability = 1.0 / selectedActions.size();
		
		double childrensExpectedUtilitySum = 0;
		
		for(ActorAction selectedAction : selectedActions){
			for(ActorActionOutcome outcome : selectedAction.getActionOutcomes()){
				NodeContentKeepTree childsContent = childContentGenerator.computeChildContent( 
						nodeShell.getContent(), multiActionProbability, outcome);					
				nodeShell.getChildren().add(new NodeShellKeepTree(childsContent));
				childrensExpectedUtilitySum += childsContent.getProbabilityProduct() * outcome.getUtility();
			}
			if(config.isCalculateAbsoluteActionOccurances() || config.isCalculateRelativeActionOccurances()) {
				increaseAbsoluteOccurance(childrensStageResult, selectedAction);
			}
		}
		childrensStageResult.setExpectedUtility(childrensStageResult.getExpectedUtility() +childrensExpectedUtilitySum);
	}

	private void increaseAbsoluteOccurance(StageResult stageResult, ActorAction selectedAction) {
		Map<ActorAction, Integer> absoluteActionOccurances = stageResult
				.getAbsoluteActionOccurances();
		absoluteActionOccurances.put(selectedAction,
				absoluteActionOccurances.get(selectedAction) + 1);
	}

	private void setContentAccordingToConfig(NodeShellKeepTree nodeShell) {
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
