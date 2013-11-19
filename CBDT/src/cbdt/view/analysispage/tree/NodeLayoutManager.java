package cbdt.view.analysispage.tree;

import cbdt.view.analysispage.tree.treemodel.NodeContext;

public class NodeLayoutManager {

	public NodeContext frame;
	
	public NodeLayoutManager(NodeContext frame) {
		System.out.println("NodeLayoutManager.Constructor: after entering Constructor");
		this.frame = frame;
	}
	
	public int convertToDocumentCoordinatesX(int numberOfNodesOnStage, int indexOnStage){
		int horizontalStageDist = frame.getWidth() / numberOfNodesOnStage;
		return horizontalStageDist * indexOnStage + horizontalStageDist/2;
	}
	
	public int convertToDocumentCoordinatesY(int indexOfTheStage) {
		return NodeContext.VERTICAL_DIFF * indexOfTheStage;
	}
	
}
