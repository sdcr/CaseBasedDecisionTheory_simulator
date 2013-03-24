package cbdt.view.analysis.tree;

import processing.core.PShape;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeContentKeepTree;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;

public class NodeCircle {


	private int DEFAULT_STROKE_WEIGHT = 1;
	private int DEFAULT_STROKE_WEIGHT_MOUSE_OVER = 5;
	
	private TreePApplet pApplet;
	private NodeCircle[] children;
	private NodeLine[] linesTochildren;

	private PShape circleShape;
	private int radius;

//	private int indexOnStage;
//	private int NumberOfNodesOnStage;
//	private int stagesIndex;
	
//	private int windowCoordinateX;
//	private int windowCoordinateY;

	private int documentCoordinateX;
	private int documentCoordinateY;
	
	private NodeShell representedShell;
	
	private DataRectangleShower dataRectangleShower;
	private ZoomConverter coordinateConverter;

	public NodeCircle(TreePApplet treePApplet, DataRectangleShower dataRectangleShower, ZoomConverter coordinateConverter) {
		pApplet = treePApplet;
		this.dataRectangleShower = dataRectangleShower;
		this.coordinateConverter = coordinateConverter;
	}
	
	public void draw(){
		int windowCoordinateX = coordinateConverter.convertToWindowCoordinatesX(documentCoordinateX);
		int windowCoordinateY = coordinateConverter.convertToWindowCoordinatesY(documentCoordinateY);

		if(isMouseInside(windowCoordinateX, windowCoordinateY)){
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT_MOUSE_OVER);
			pApplet.setInfoShowingCircle(this);
		} else
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT);
		
		pApplet.pushMatrix();
		pApplet.translate(windowCoordinateX, windowCoordinateY);
		pApplet.shape(circleShape);
		pApplet.popMatrix();
		
		for(int i=0; i<children.length; i++){
			linesTochildren[i].draw();
			children[i].draw();
		}
	}

//	public void calcPositionRecursively() {
//		windowCoordinateX = coordinateConverter.convertToWindowCoordinatesX(NumberOfNodesOnStage, indexOnStage);
//		windowCoordinateY = coordinateConverter.convertToWindowCoordinatesY(stagesIndex);
//		
//		for(NodeCircle child : children){
//			child.calcPositionRecursively();
//		}
//	}


	public void showDataRectangle() {
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
	
//	public void setIndexOnStage(int indexOnStage) {
//		this.indexOnStage = indexOnStage;
//	}
//	
//	public void setNumberOfNodesOnStage(int NumberOfNodesOnStage){
//		this.NumberOfNodesOnStage = NumberOfNodesOnStage;
//	}
//
//	public void setStagesIndex(int stage) {
//		this.stagesIndex = stage;
//	}
	
	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public NodeLine[] getLinesTochildren() {
		return linesTochildren;
	}

	public void setLinesTochildren(NodeLine[] linesTochildren) {
		this.linesTochildren = linesTochildren;
	}

//	public int getWindowCoordinateX() {
//		return windowCoordinateX;
//	}
//
//	public void setWindowCoordinateX(int windowCoordinateX) {
//		this.windowCoordinateX = windowCoordinateX;
//	}
//
//	public int getWindowCoordinateY() {
//		return windowCoordinateY;
//	}
//
//	public void setWindowCoordinateY(int windowCoordinateY) {
//		this.windowCoordinateY = windowCoordinateY;
//	}

	public NodeShell getRepresentedShell() {
		return representedShell;
	}

	public void setRepresentedShell(NodeShell representedShell) {
		this.representedShell = representedShell;
	}

	public void setShape(PShape shape) {
		this.circleShape = shape;
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
