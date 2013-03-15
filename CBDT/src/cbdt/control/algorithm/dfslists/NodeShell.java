package cbdt.control.algorithm.dfslists;


public class NodeShell {

//	NodeContent content;
//	
////	List<NodeShell> children;
//	
//	public NodeShell(NodeContent content) {
//		this.content = content;
////		this.children = new ArrayList<NodeShell>();
//	}
//	
//	public void computeChildren(List<NodeShell> children, List<ActorAction> selectedActions, Parameters parameters, double[] expectedUtilities, int childrensLevelIndex){
//		if(childrensLevelIndex < parameters.getNumberOfRequestedExpectedUtilities()){
//			computeSelectedActions(parameters, selectedActions);
//			double multiActionProbability = 1.0 / selectedActions.size();
//			
//			double childrensExpectedUtilitySum = 0;
//			
//			for(ActorAction selectedAction : selectedActions){
//				for(ActorActionOutcome outcome : selectedAction.getActionOutcomes()){
//					NodeContent childsContent = computeChildsContent(parameters,
//							multiActionProbability, selectedAction, outcome);
//					childrensExpectedUtilitySum += childsContent.getProbabilityProduct() * outcome.getUtility();
//					NodeShell child = new NodeShell(childsContent);
//					children.add(child);
//				}
//			}
//			expectedUtilities[childrensLevelIndex] += childrensExpectedUtilitySum;
//			
//			content = null;
//			
//			initiateChildrensComputation(parameters, expectedUtilities, childrensLevelIndex);
//		}
//		content = null;
//		children = null;
//	}
//
//	private HashMap<ActorAction, Integer> getInitNumberOfOccurences(Parameters parameters) {
//		HashMap<ActorAction, Integer> hashMap = new HashMap<ActorAction,Integer>();
//		for(ActorAction action : parameters.getActorActions()){
//			hashMap.put(action, 0);
//		}
//		return hashMap;
//	}
//
//	private HashMap<ActorAction,Double> getInitSumOfUtilities(Parameters parameters) {
//		HashMap<ActorAction, Double> hashMap = new HashMap<ActorAction,Double>();
//		for(ActorAction action : parameters.getActorActions()){
//			hashMap.put(action, 0.0);
//		}
//		return hashMap;
//	}
//
//	private void initiateChildrensComputation(Parameters parameters, double[] expectedUtilities,
//			int childrensLevelIndex) {
//		List<NodeShell> children = new ArrayList<NodeShell>();
//		for(ActorAction action : parameters.getActorActions()){
//			for(ActorActionOutcome outcome : action.getActionOutcomes()){
//				NodeContent childContent = new NodeContent();
//				childContent.setNumberOfOccurances(getInitNumberOfOccurences(parameters));
//				childContent.setSumOfUtilities(getInitSumOfUtilities(parameters));
//				children.add(new NodeShell(childContent));
//			}
//		}
//		for(NodeShell child : children){
//			child.computeChildren(parameters, expectedUtilities, childrensLevelIndex+1);
//		}
//	}
//
//	private NodeContent computeChildsContent(Parameters parameters,
//			double multiActionProbability, ActorAction selectedAction,
//			ActorActionOutcome outcome) {
//		NodeContent childsContent = content.getCopy();
//
//		childsContent.setProbabilityProduct(content.getProbabilityProduct() 
//				* multiActionProbability * outcome.getProbability());
//		childsContent.getNumberOfOccurances().put(selectedAction, content.getNumberOfOccurances().get(selectedAction) + 1);
//		childsContent.getSumOfUtilities().put(selectedAction, content.getSumOfUtilities().get(selectedAction) + outcome.getUtility());
//		
//		double childsAspirationLevel = computeChildsAspirationLevel(parameters, childsContent);
//		childsContent.setAspirationLevel(childsAspirationLevel);
//		return childsContent;
//	}
//
//	private double computeChildsAspirationLevel(Parameters parameters, NodeContent childsContent) {
//		double maxAverageUtility = Double.NEGATIVE_INFINITY;
//		for(ActorAction existingAction : parameters.getActorActions()){
//			Integer actionOccurances = childsContent.getNumberOfOccurances().get(existingAction);
//			if(actionOccurances>0){
//				double averageUtility = childsContent.getSumOfUtilities().get(existingAction) / actionOccurances;
//				maxAverageUtility = Math.max(maxAverageUtility, averageUtility);
//			}
//		}				
//		double childsAspirationLevel = content.getAspirationLevel()*parameters.getWeightingFactorAlpha()
//				+ maxAverageUtility*(1-parameters.getWeightingFactorAlpha());
//		return childsAspirationLevel;
//	}
//
//	private List<ActorAction> computeSelectedActions(Parameters parameters, List<ActorAction> selectedActions) {
//		selectedActions.clear();
//		double maxCumulativePerformance = Double.NEGATIVE_INFINITY;
//		
//		for(ActorAction actorAction : parameters.getActorActions()){
//			double cumulativePerformance = computeCumulativePerformance(actorAction);
//			if(cumulativePerformance > maxCumulativePerformance){
//				selectedActions.clear();
//				selectedActions.add(actorAction);
//				maxCumulativePerformance = cumulativePerformance;
//			} else if(cumulativePerformance == maxCumulativePerformance) {
//				selectedActions.add(actorAction);
//			}
//		}
//		return selectedActions;
//	}
//
//	private double computeCumulativePerformance(ActorAction actorAction) {
//		return content.getSumOfUtilities().get(actorAction) - 
//				content.getAspirationLevel() * content.getNumberOfOccurances().get(actorAction);
//	}
}
