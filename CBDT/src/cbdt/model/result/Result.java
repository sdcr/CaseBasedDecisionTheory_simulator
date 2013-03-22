package cbdt.model.result;

import java.util.List;

import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;

public class Result {

	private List<StageResult> stageResults;
	
	private NodeShell rootNode;

	public List<StageResult> getStageResults() {
		return stageResults;
	}

	public void setStageResults(List<StageResult> stageResults) {
		this.stageResults = stageResults;
	}

	public NodeShell getRootNode() {
		return rootNode;
	}

	public void setRootNode(NodeShell rootNode) {
		this.rootNode = rootNode;
	}
}
