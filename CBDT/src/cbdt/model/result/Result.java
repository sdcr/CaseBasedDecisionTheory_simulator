package cbdt.model.result;

import java.util.List;

import cbdt.model.result.tree.NodeShell;

/**
 * This class models the result of a simulation.<br>
 * <br>
 * It consists of two parts, a {@link List} of {@link StageResult} objects with
 * one object per computed stage, and possibly the root {@link NodeShell}
 * object, if the tree is kept for the simulation result.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class Result {

	private List<StageResult> stageResults;

	private NodeShell rootNode;

	/**
	 * @return the {@link List} of {@link StageResult} objects, where one object
	 *         represents the result of a computed stage.
	 */
	public List<StageResult> getStageResults() {
		return stageResults;
	}

	/**
	 * @param stageResults
	 *            the {@link List} of {@link StageResult} objects, where one
	 *            object represents the result of a computed stage.
	 */
	public void setStageResults(List<StageResult> stageResults) {
		this.stageResults = stageResults;
	}

	/**
	 * @return the {@link NodeShell} object which represents the root node of a
	 *         computed tree.
	 */
	public NodeShell getRootNode() {
		return rootNode;
	}

	/**
	 * @param rootNode
	 *            the {@link NodeShell} object which represents the root node of
	 *            a computed tree.
	 */
	public void setRootNode(NodeShell rootNode) {
		this.rootNode = rootNode;
	}
}
