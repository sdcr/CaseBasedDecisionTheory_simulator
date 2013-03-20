package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;

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
			double[] expectedUtilities, List<Map<ActorAction,Integer>> actionOccurances, int childrensStage){
		if(childrensStage < config.getNumberOfRequestedExpectedUtilityValues()){
		
			List<ActorAction> selectedActions = computeSelectedActions(parameters);
			double multiActionProbability = 1.0 / selectedActions.size();
			
			double childrensExpectedUtilitySum = 0;
			
			for(ActorAction selectedAction : selectedActions){
				for(ActorActionOutcome outcome : selectedAction.getActionOutcomes()){
					NodeContentKeepTree childsContent = computeChildsContent(parameters,
							multiActionProbability, selectedAction, outcome);
					childrensExpectedUtilitySum += childsContent.getProbabilityProduct() * outcome.getUtility();
					NodeShellKeepTree child = new NodeShellKeepTree(childsContent);
					children.add(child);
				}
				if(config.isCalculateAbsoluteActionOccurances() || config.isCalculateRelativeActionOccurances())
					actionOccurances.get(childrensStage).put(selectedAction, actionOccurances.get(childrensStage).get(selectedAction) +1);
			}
			expectedUtilities[childrensStage] += childrensExpectedUtilitySum;
			
			setContentAccordingToConfig(config);
			initiateChildrensComputation(parameters, config, expectedUtilities, actionOccurances, childrensStage);
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

	private void initiateChildrensComputation(Parameters parameters, DFSkeepTreeEngineConfig config, double[] expectedUtilities,
			List<Map<ActorAction, Integer>> actionOccurances, int childrensLevelIndex) {
		for(NodeShellKeepTree child : children){
			child.computeChildren(parameters, config, expectedUtilities, actionOccurances, childrensLevelIndex+1);
		}
	}

	private NodeContentKeepTree computeChildsContent(Parameters parameters,
			double multiActionProbability, ActorAction selectedAction,
			ActorActionOutcome outcome) {
		NodeContentKeepTree childsContent = content.getCopy();

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
