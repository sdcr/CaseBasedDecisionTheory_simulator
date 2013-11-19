package cbdt.view.analysispage.tree;

import java.awt.Point;

import cbdt.view.analysispage.tree.treemodel.NodeContext;

public class ZoomConverter {

	private NodeContext context;
	private NodeContext visualWindow;
	
	public ZoomConverter(NodeContext context, NodeContext visualWindow) {
		this.context = context;
		this.visualWindow = visualWindow;
	}
	
	public int convertToWindowCoordinatesX(int documentCoordinateX) {
		return (int) (((double)documentCoordinateX - visualWindow.getMarginLeft())/
				visualWindow.getWidth() * context.getWidth() + context.getMarginLeft() );
	}

	public int convertToWindowCoordinatesY(int documentCoordinateY) {
		return (int) (((double)documentCoordinateY - visualWindow.getMarginTop()) /
				visualWindow.getHeight() * context.getHeight() + context.getMarginTop() );
	}

	public Point getInDocumentCoordinates(Point mousePosInWindowCoordinates) {
		Point newPoint = new Point();
		newPoint.x = (int) (((double)(mousePosInWindowCoordinates.x - context.getMarginLeft()) / context.getWidth() * visualWindow.getWidth()) + visualWindow.getMarginLeft());
		newPoint.y = (int) (((double)(mousePosInWindowCoordinates.y - context.getMarginTop()) / context.getHeight() * visualWindow.getHeight()) + visualWindow.getMarginTop());
		return newPoint;
	}
	
	public void updateZoom(int wheelRotation, Point mousePosInWindowCoordinates){
		Point mousePosInDocCoords = getInDocumentCoordinates(mousePosInWindowCoordinates);
		
		if(wheelRotation<0){
			visualWindow.setWidth((int) (visualWindow.getWidth() * 0.8));
			visualWindow.setHeight((int) (visualWindow.getHeight() * 0.8));
			visualWindow.setMarginLeft((int) (visualWindow.getMarginLeft() + (mousePosInDocCoords.x - visualWindow.getMarginLeft()) * 0.2));
			visualWindow.setMarginTop((int) (visualWindow.getMarginTop() + (mousePosInDocCoords.y - visualWindow.getMarginTop()) * 0.2));
		}else{
			visualWindow.setWidth((int) (visualWindow.getWidth() * 1.25));
			visualWindow.setHeight((int) (visualWindow.getHeight() * 1.25));
			visualWindow.setMarginLeft((int) (visualWindow.getMarginLeft() - (mousePosInDocCoords.x - visualWindow.getMarginLeft()) * 0.25));
			visualWindow.setMarginTop((int) (visualWindow.getMarginTop() - (mousePosInDocCoords.y - visualWindow.getMarginTop()) * 0.25));
		}
	}
}
