package cbdt.view.analysis.tree;

import processing.core.PApplet;

public class NodeLine {

	NodeCircle from;
	NodeCircle to;
	private PApplet pApplet;
	
	public NodeLine(PApplet pApplet, NodeCircle from, NodeCircle to) {
		this.pApplet = pApplet;
		this.from = from;
		this.to = to;
	}
	
	public void draw(){
		int x1 = from.getRelCenterX();
		int y1 = from.getWindowCoordinateY() + from.getRadius();
		int x2 = to.getRelCenterX();
		int y2 = to.getWindowCoordinateY() - to.getRadius();
		pApplet.line(x1, y1, x2, y2);
	}
}
