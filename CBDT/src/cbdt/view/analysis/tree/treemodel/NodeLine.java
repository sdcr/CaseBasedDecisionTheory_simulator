package cbdt.view.analysis.tree.treemodel;

import cbdt.view.analysis.tree.TreePApplet;

public class NodeLine {

	private static final float DEFAULT_STROKE_WEIGHT = 1;
	NodeCircle from;
	NodeCircle to;
	private TreePApplet pApplet;
	
	public NodeLine(TreePApplet pApplet, NodeCircle from, NodeCircle to) {
		this.pApplet = pApplet;
		this.from = from;
		this.to = to;
	}
	
	public void draw(){
		int x1 = pApplet.getZoomConverter().convertToWindowCoordinatesX(from.getDocumentCoordinateX());
		int y1 = pApplet.getZoomConverter().convertToWindowCoordinatesY(from.getDocumentCoordinateY()) + NodeCircle.radius;
		int x2 = pApplet.getZoomConverter().convertToWindowCoordinatesX(to.getDocumentCoordinateX());
		int y2 = pApplet.getZoomConverter().convertToWindowCoordinatesY(to.getDocumentCoordinateY()) - NodeCircle.radius;
		pApplet.strokeWeight(DEFAULT_STROKE_WEIGHT);
		pApplet.line(x1, y1, x2, y2);
	}
}
