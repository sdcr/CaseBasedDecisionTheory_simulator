package cbdt.view.analysis.tree;

import processing.core.PApplet;
import processing.core.PShape;

public class NodeLine {

	NodeCircle from;
	NodeCircle to;
	private PApplet pApplet;
	private PShape lineShape;
	
	public NodeLine(PApplet pApplet, PShape lineShape, NodeCircle from, NodeCircle to) {
		this.pApplet = pApplet;
		this.lineShape = lineShape;
		this.from = from;
		this.to = to;
	}
	
	public void draw(){
		int x1 = from.getRelCenterX();
		int y1 = from.getRelCenterY() + from.getRadius();
		
		int x2 = to.getRelCenterX();
		int y2 = to.getRelCenterY() - to.getRadius();
		pApplet.line(x1, y1, x2, y2);
//		pApplet.shape(lineShape, x1, y1, x2, y2);
	}
}
