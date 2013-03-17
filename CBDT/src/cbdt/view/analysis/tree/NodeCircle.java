package cbdt.view.analysis.tree;

import processing.core.PApplet;
import processing.core.PShape;

public class NodeCircle {

	private int DEFAULT_STROKE_WEIGHT = 1;
	private int DEFAULT_STROKE_WEIGHT_MOUSE_OVER = 5;
	
	private PShape shape;

	int centerX;
	int centerY;
	int radius;

	int strokeWeight;
	
	private PApplet pApplet;
	
	public NodeCircle(PApplet treePApplet, PShape shape) {
		pApplet = treePApplet;
		this.shape = shape;
	}
	
	public NodeCircle(PApplet pApplet, int centerX, int centerY, int radius) {
		this.pApplet = pApplet;
		this.centerX = centerX;
		this.centerY = centerY;
		this.radius = radius;
		
		strokeWeight = DEFAULT_STROKE_WEIGHT;
	}
	
	public void draw(){
		pApplet.shape(shape);
//		update();
//		
//		pApplet.strokeWeight(strokeWeight);
//		pApplet.ellipseMode(pApplet.CENTER);
//		pApplet.ellipse(centerX, centerY, radius*2, radius*2);
	}

//	private void update() {
////		centerX=pApplet.mouseX;
////		centerY=pApplet.mouseY;
//		
//		if(isMouseInside())
//			strokeWeight = DEFAULT_STROKE_WEIGHT_MOUSE_OVER;
//		else
//			strokeWeight = DEFAULT_STROKE_WEIGHT;
//	}
//
//	private boolean isMouseInside() {
//		int diffX = pApplet.mouseX-centerX;
//		int diffY = pApplet.mouseY-centerY;
//		if(radius*radius > diffX*diffX + diffY*diffY)
//			return true;
//		return false;
//	}
	
	
}
