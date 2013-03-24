package cbdt.view.analysis.tree;

public class NodeLayoutManager {

	public NodeContext frame;
	
	public NodeLayoutManager(NodeContext frame) {
		this.frame = frame;
	}
	
	public int convertToDocumentCoordinatesX(int numberOfNodesOnStage, int indexOnStage){
		int horizontalStageDist = frame.getWidth() / (numberOfNodesOnStage - 1);
		return horizontalStageDist * indexOnStage + horizontalStageDist/2;
	}
	
	public int convertToDocumentCoordinatesY(int indexOfTheStage) {
		return NodeContext.VERTICAL_DIFF * indexOfTheStage;
	}
	
}
