package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class NodeShellKeepTree {

	NodeContentKeepTree content;
	
	List<NodeShellKeepTree> children;
	
	public NodeContentKeepTree getContent() {
		return content;
	}

	public NodeShellKeepTree(NodeContentKeepTree content) {
		this.content = content;
		this.children = new ArrayList<NodeShellKeepTree>();
	}

	public void setChildren(List<NodeShellKeepTree> children) {
		this.children = children;
	}

	public List<NodeShellKeepTree> getChildren() {
		return children;
	}
	
	public void computeChildren(Parameters parameters, DFSkeepTreeEngineConfig config, 
			Result result, int childrensStage, NodeContentFactory factory){
		if(childrensStage < config.getNumberOfRequestedExpectedUtilityValues()){
		
			List<ActorAction> selectedActions = computeSelectedActions(parameters);
			double multiActionProbability = 1.0 / selectedActions.size();
			
			double childrensExpectedUtilitySum = 0;
			
			StageResult stageResult = result.getStageResults().get(childrensStage);
			for(ActorAction selectedAction : selectedActions){
				for(ActorActionOutcome outcome : selectedAction.getActionOutcomes()){
					NodeContentKeepTree childsContent = computeChildsContent(parameters,
							multiActionProbability, selectedAction, outcome, factory);
					childrensExpectedUtilitySum += childsContent.getProbabilityProduct() * outcome.getUtility();
					NodeShellKeepTree child = new NodeShellKeepTree(childsContent);
					children.add(child);
				}
				if(config.isCalculateAbsoluteActionOccurances() || config.isCalculateRelativeActionOccurances()) {
					Map<ActorAction, Integer> absoluteActionOccurances = stageResult
							.getAbsoluteActionOccurances();
					absoluteActionOccurances.put(selectedAction,
							absoluteActionOccurances.get(selectedAction) + 1);
				}
			}
			stageResult.setExpectedUtility(stageResult.getExpectedUtility() +childrensExpectedUtilitySum);
			
			setContentAccordingToConfig(config);
			initiateChildrensComputation(parameters, config, result, childrensStage, factory);
		}
		
		if(config.isSaveTreeStructure() == false)
			children = null;
	}

	private void setContentAccordingToConfig(DFSkeepTreeEngineConfig config) {
		if(!config.isSaveActionNames() && !config.isSaveAspirationLevels() && !config.isSaveTreeStructure())
			content = null;
		else{
			content.setNumberOfOccurances(null);
			content.setSumOfUtilities(null);
			if(!config.isSaveActionNames())
				content.setLastAction(null);
		}
	}

	private void initiateChildrensComputation(Parameters parameters, DFSkeepTreeEngineConfig config, 
			Result result, int childrensLevelIndex, NodeContentFactory factory) {
		for(NodeShellKeepTree child : children){
			child.computeChildren(parameters, config, result, childrensLevelIndex+1, factory);
		}
	}

	private NodeContentKeepTree computeChildsContent(Parameters parameters,
			double multiActionProbability, ActorAction selectedAction,
			ActorActionOutcome outcome, NodeContentFactory factory) {
		NodeContentKeepTree childsContent = factory.getCopy(content);

		childsContent.setProbabilityProduct(content.getProbabilityProduct() 
				* multiActionProbability * outcome.getProbability());
		childsContent.getNumberOfOccurances().put(selectedAction, content.getNumberOfOccurances().get(selectedAction) + 1);
		childsContent.getSumOfUtilities().put(selectedAction, content.getSumOfUtilities().get(selectedAction) + outcome.getUtility());
		childsContent.setLastAction(selectedAction);
		
		double childsAspirationLevel = computeChildsAspirationLevel(parameters, childsContent);
		childsContent.setAspirationLevel(childsAspirationLevel);
		return childsContent;
	}

	private double computeChildsAspirationLevel(Parameters parameters, NodeContentKeepTree childsContent) {
		double maxAverageUtility = Double.NEGATIVE_INFINITY;
		for(ActorAction existingAction : parameters.getActorActions()){
			Integer actionOccurances = childsContent.getNumberOfOccurances().get(existingAction);
			if(actionOccurances>0){
				double averageUtility = childsContent.getSumOfUtilities().get(existingAction) / actionOccurances;
				maxAverageUtility = Math.max(maxAverageUtility, averageUtility);
			}
		}				
		double childsAspirationLevel = content.getAspirationLevel()*parameters.getWeightingFactorAlpha()
				+ maxAverageUtility*(1-parameters.getWeightingFactorAlpha());
		return childsAspirationLevel;
	}

	private List<ActorAction> computeSelectedActions(Parameters parameters) {
		List<ActorAction> selectedActions = new ArrayList<ActorAction>(); 
		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
		
		for(ActorAction actorAction : parameters.getActorActions()){
			double cumulativePerformance = computeCumulativePerformance(actorAction);
			if(cumulativePerformance > maxCumulativePerformance){
				selectedActions.clear();
				selectedActions.add(actorAction);
				maxCumulativePerformance = cumulativePerformance;
			} else if(cumulativePerformance == maxCumulativePerformance) {
				selectedActions.add(actorAction);
			}
		}
		return selectedActions;
	}

	private double computeCumulativePerformance(ActorAction actorAction) {
		return content.getSumOfUtilities().get(actorAction) - 
				content.getAspirationLevel() * content.getNumberOfOccurances().get(actorAction);
	}
}
