package cbdt.model.result;

import java.util.List;

import cbdt.control.simulation.algorithm.dfskeeptree.NodeShellKeepTree;

public class Result {

	private List<StageResult> stageResults;
	
	private NodeShellKeepTree rootNode;

	public List<StageResult> getStageResults() {
		return stageResults;
	}

	public void setStageResults(List<StageResult> stageResults) {
		this.stageResults = stageResults;
	}

	public NodeShellKeepTree getRootNode() {
		return rootNode;
	}

	public void setRootNode(NodeShellKeepTree rootNode) {
		this.rootNode = rootNode;
	}
}
