package cbdt.view.analysis.tree;

public class CoordinateConverter {

	private NodeContext context;
	private NodeContext visualWindow;
	
	public CoordinateConverter(NodeContext context, NodeContext visualWindow) {
		this.context = context;
		this.visualWindow = visualWindow;
	}
	
	public int convertToWindowCoordinatesX(int numberOfNodesOnStage, int indexOnStage) {
		int horizontalStageDist = context.getWidth() / (numberOfNodesOnStage - 1);
		int absCenterX = horizontalStageDist * indexOnStage + horizontalStageDist/2;
		
		return (int) (((double)absCenterX - visualWindow.getMarginLeft())/
				visualWindow.getWidth() * context.getWidth() + context.getMarginLeft() );
	}

	public int convertToWindowCoordinatesY(int indexOfTheStage) {
		int absCenterY = NodeContext.VERTICAL_DIFF * indexOfTheStage;
		
		return (int) (((double)absCenterY - visualWindow.getMarginTop()) /
				visualWindow.getHeight() * context.getHeight() + context.getMarginTop() );
	}
	
}
