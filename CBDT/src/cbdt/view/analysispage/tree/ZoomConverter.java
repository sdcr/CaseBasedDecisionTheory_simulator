package cbdt.view.analysispage.tree;

import java.awt.Point;

import cbdt.view.analysispage.tree.treemodel.NodeFrame;

/**
 * This class implements a zooming effect by translating coordinates between the
 * document model and the visual window model, and resizing the visual window
 * when the mouse wheel is rotated.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ZoomConverter {

	/**
	 * This {@link NodeFrame} represents the frame of the document model.
	 */
	private NodeFrame documentFrame;
	
	/**
	 * This {@link NodeFrame} represents the frame of the visual window model.
	 */
	private NodeFrame visualWindow;
	
	/**
	 * Constructor.
	 * 
	 * @param context
	 * @param visualWindow
	 */
	public ZoomConverter(NodeFrame context, NodeFrame visualWindow) {
		this.documentFrame = context;
		this.visualWindow = visualWindow;
	}

	/**
	 * Takes a {@link Point} object in document coordinates, and creates a
	 * new {@link Point} object with the corresponding coordinates in visual 
	 * window coordinates.
	 * 
	 * @param pointInWindowCoordinates
	 * @return
	 */
	public Point convertToWindowCoordinates(Point pointInDocumentCoordinates) {
		Point newPoint = new Point();
		newPoint.x = (int) (((double)pointInDocumentCoordinates.x - visualWindow.getMarginLeft())/
				visualWindow.getWidth() * documentFrame.getWidth() + documentFrame.getMarginLeft() );
		newPoint.y = (int) (((double)pointInDocumentCoordinates.y - visualWindow.getMarginTop()) /
				visualWindow.getHeight() * documentFrame.getHeight() + documentFrame.getMarginTop() );		
		return newPoint;
	}

	/**
	 * Takes a {@link Point} object in visual window coordinates, and creates a
	 * new {@link Point} object with the corresponding coordinates in document
	 * coordinates.
	 * 
	 * @param pointInWindowCoordinates
	 * @return
	 */
	private Point convertToDocumentCoordinates(Point pointInWindowCoordinates) {
		Point newPoint = new Point();
		newPoint.x = (int) (((double)(pointInWindowCoordinates.x - documentFrame.getMarginLeft()) / documentFrame.getWidth() * visualWindow.getWidth()) + visualWindow.getMarginLeft());
		newPoint.y = (int) (((double)(pointInWindowCoordinates.y - documentFrame.getMarginTop()) / documentFrame.getHeight() * visualWindow.getHeight()) + visualWindow.getMarginTop());
		return newPoint;
	}
	
	/**
	 * Updates the size of the visual window, to generate a zooming effect.<br>
	 * If the mouse wheel is rotated forward, the visual window is shrunk,
	 * creating a 'zoom in' effect. If the mouse wheel is rotated backward, the
	 * visual window is expanded, creating a 'zoom out' effect.
	 * 
	 * @param wheelRotation
	 * @param mousePosInWindowCoordinates
	 */	
	public void updateZoom(int wheelRotation, Point mousePosInWindowCoordinates){
		Point mousePosInDocCoords = convertToDocumentCoordinates(mousePosInWindowCoordinates);
		
		if(wheelRotation<0){
			// zoom in
			visualWindow.setWidth((int) (visualWindow.getWidth() * 0.8));
			visualWindow.setHeight((int) (visualWindow.getHeight() * 0.8));
			visualWindow.setMarginLeft((int) (visualWindow.getMarginLeft() + (mousePosInDocCoords.x - visualWindow.getMarginLeft()) * 0.2));
			visualWindow.setMarginTop((int) (visualWindow.getMarginTop() + (mousePosInDocCoords.y - visualWindow.getMarginTop()) * 0.2));
		}else{
			// zoom out
			visualWindow.setWidth((int) (visualWindow.getWidth() * 1.25));
			visualWindow.setHeight((int) (visualWindow.getHeight() * 1.25));
			visualWindow.setMarginLeft((int) (visualWindow.getMarginLeft() - (mousePosInDocCoords.x - visualWindow.getMarginLeft()) * 0.25));
			visualWindow.setMarginTop((int) (visualWindow.getMarginTop() - (mousePosInDocCoords.y - visualWindow.getMarginTop()) * 0.25));
		}
	}
}
