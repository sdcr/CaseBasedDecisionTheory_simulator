package cbdt.control.simulation.algorithm.dfskeeptree;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class ChildNodeContentGenerator {

	private NodeContentKeepTreeFactory factory;
	private AspirationLevelGenerator aspirationLevelGenerator;
	
	public ChildNodeContentGenerator(Parameters parameters, NodeContentKeepTreeFactory factory) {
		aspirationLevelGenerator = new AspirationLevelGenerator(parameters);
		this.factory = factory;
	}
	
	public NodeContentKeepTree computeChildContent(NodeContentKeepTree parentContent,
			double multiActionProbability, ActorActionOutcome outcome, int indexOfChildrensStage) {
		NodeContentKeepTree childsContent = factory.getCopy(parentContent);
		
		ActorAction selectedAction = outcome.getAction();
		childsContent.setProbabilityProduct(parentContent.getProbabilityProduct() 
				* multiActionProbability * outcome.getProbability());
		childsContent.getNumberOfOccurances().put(selectedAction, parentContent.getNumberOfOccurances().get(selectedAction) + 1);
		childsContent.getSumOfUtilities().put(selectedAction, parentContent.getSumOfUtilities().get(selectedAction) + outcome.getUtility());
		childsContent.setLastActionOutcome(outcome);
		
		double childsAspirationLevel = aspirationLevelGenerator.computeChildsAspirationLevel(parentContent.getAspirationLevel(), indexOfChildrensStage, childsContent);
		childsContent.setAspirationLevel(childsAspirationLevel);
		return childsContent;
	}


}
