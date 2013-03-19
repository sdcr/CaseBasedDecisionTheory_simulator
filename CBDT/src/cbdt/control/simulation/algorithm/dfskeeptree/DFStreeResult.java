package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.result.Result;

/**
 * Contains the results of the DFS with tree structure algorithm.
 * @author S-lenovo
 */
public class DFStreeResult implements Result {

	private double[] expectedUtilities;
	
	private List<Map<ActorAction, Integer>> absoluteActionOccurances;

	private List<Map<ActorAction,Double>> relativeActionOccurances;

	private NodeShellKeepTree rootNode;
	
	public double[] getExpectedUtilities() {
		return expectedUtilities;
	}

	public void setExpectedUtilities(double[] expectedUtilities) {
		this.expectedUtilities = expectedUtilities;
	}

	public List<Map<ActorAction, Integer>> getAbsoluteActionOccurances() {
		return absoluteActionOccurances;
	}

	public void setAbsoluteActionOccurances(List<Map<ActorAction, Integer>> absoluteActionOccurances) {
		this.absoluteActionOccurances = absoluteActionOccurances;
	}

	public List<Map<ActorAction,Double>> getRelativeActionOccurances() {
		return relativeActionOccurances;
	}

	public void setRelativeActionOccurances(List<Map<ActorAction,Double>> relativeActionOccurances) {
		this.relativeActionOccurances = relativeActionOccurances;
	}

	public NodeShellKeepTree getRootNode() {
		return rootNode;
	}

	public void setRootNode(NodeShellKeepTree rootShell) {
		this.rootNode = rootShell;
	}
}
