package cbdt.view.analysis.tree;

import processing.core.PShape;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeContentKeepTree;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;

public class NodeCircle {


	private int DEFAULT_STROKE_WEIGHT = 1;
	private int DEFAULT_STROKE_WEIGHT_MOUSE_OVER = 5;
	
	private TreePApplet pApplet;
	private NodeCircle[] children;
	private NodeLine[] outgoingLines;

	private PShape circleShape;
	public static final int radius = 10;

	private int documentCoordinateX;
	private int documentCoordinateY;
	
	private NodeShell representedShell;
	
	public NodeCircle(TreePApplet treePApplet) {
		pApplet = treePApplet;
		outgoingLines = new NodeLine[0];
	}
	
	public void draw(){
		ZoomConverter zoomConverter = pApplet.getZoomConverter();
		int windowCoordinateX = zoomConverter.convertToWindowCoordinatesX(documentCoordinateX);
		int windowCoordinateY = zoomConverter.convertToWindowCoordinatesY(documentCoordinateY);

		if(isMouseInside(windowCoordinateX, windowCoordinateY)){
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT_MOUSE_OVER);
			pApplet.setInfoShowingCircle(this);
		} else
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT);
		
		pApplet.pushMatrix();
		pApplet.translate(windowCoordinateX, windowCoordinateY);
		pApplet.shape(circleShape);
		pApplet.popMatrix();
	}

	public void showDataRectangle(DataRectangleShower dataRectangleShower) {
		NodeContentKeepTree content = representedShell.getContent();
		if(content != null){
			dataRectangleShower.showDataRectangle(content);
		}
	}

	public void setChildren(NodeCircle[] children) {
		this.children = children;
	}

	public NodeCircle[] getChildren() {
		return children;
	}
	
	private boolean isMouseInside(int windowCoordinateX, int windowCoordinateY) {
		int diffX = pApplet.mouseX-windowCoordinateX;
		int diffY = pApplet.mouseY-windowCoordinateY;
		if(radius*radius > diffX*diffX + diffY*diffY)
			return true;
		return false;
	}
	
	public NodeLine[] getOutgoingLines() {
		return outgoingLines;
	}

	public void setOutgoingLines(NodeLine[] outgoingLines) {
		this.outgoingLines = outgoingLines;
	}

	public NodeShell getRepresentedShell() {
		return representedShell;
	}

	public void setRepresentedShell(NodeShell representedShell) {
		this.representedShell = representedShell;
	}

	public void setShape(PShape shape) {
		this.circleShape = shape;
	}
	
	public PShape getCircleShape() {
		return circleShape;
	}

	public int getDocumentCoordinateX() {
		return documentCoordinateX;
	}

	public void setDocumentCoordinateX(int documentCoordinateX) {
		this.documentCoordinateX = documentCoordinateX;
	}

	public int getDocumentCoordinateY() {
		return documentCoordinateY;
	}

	public void setDocumentCoordinateY(int documentCoordinateY) {
		this.documentCoordinateY = documentCoordinateY;
	}

}
