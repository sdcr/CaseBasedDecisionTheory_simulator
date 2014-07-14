package cbdt.control.simulation.algorithm.dfskeeptree;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.tree.NodeContent;

/**
 * This class encapsulates the computation of a child's {@link NodeContent}
 * object.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ChildNodeContentGenerator {

	private NodeContentKeepTreeFactory factory;
	private AspirationLevelGenerator aspirationLevelGenerator;

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 * @param factory
	 */
	public ChildNodeContentGenerator(Parameters parameters,
			NodeContentKeepTreeFactory factory) {
		aspirationLevelGenerator = new AspirationLevelGenerator(parameters);
		this.factory = factory;
	}

	/**
	 * Compute the {@link NodeContent} object of a child node, given the
	 * parent's {@link NodeContent} and the outcome leading to the child, among
	 * others.
	 * 
	 * @param parentContent
	 * @param multiActionProbability
	 * @param outcome
	 * @param indexOfChildrensStage
	 * @return
	 */
	public NodeContent computeChildContent(NodeContent parentContent,
			double multiActionProbability, ActorActionOutcome outcome,
			int indexOfChildrensStage) {
		NodeContent childsContent = factory.getCopy(parentContent);

		ActorAction selectedAction = outcome.getAction();
		childsContent.setProbabilityProduct(parentContent
				.getProbabilityProduct()
				* multiActionProbability
				* outcome.getProbability());
		childsContent.getNumberOfOccurances().put(selectedAction,
				parentContent.getNumberOfOccurances().get(selectedAction) + 1);
		childsContent.getSumOfUtilities().put(
				selectedAction,
				parentContent.getSumOfUtilities().get(selectedAction)
						+ outcome.getUtility());
		childsContent.setLastActionOutcome(outcome);

		double childsAspirationLevel = aspirationLevelGenerator
				.computeChildsAspirationLevel(
						parentContent.getAspirationLevel(),
						indexOfChildrensStage, childsContent);
		childsContent.setAspirationLevel(childsAspirationLevel);
		return childsContent;
	}

}
