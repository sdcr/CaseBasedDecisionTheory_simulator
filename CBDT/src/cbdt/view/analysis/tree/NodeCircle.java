package cbdt.view.analysis.tree;

import processing.core.PApplet;
import processing.core.PShape;

public class NodeCircle {


	private int DEFAULT_STROKE_WEIGHT = 1;
	private int DEFAULT_STROKE_WEIGHT_MOUSE_OVER = 5;
	
	private PApplet pApplet;
	private NodeCircle[] children;
	private NodeLine[] linesTochildren;

	private PShape circleShape;
	private int radius;
	private int absCenterX;
	private int absCenterY;

	private int stageIndex;
	private int stageLength;
	private int stage;
	
	private NodeContext context;
	private NodeContext visualWindow;
	private int relCenterX;
	private int relCenterY;


	public NodeCircle(PApplet treePApplet) {
		pApplet = treePApplet;
	}
	
	public void draw(){
		update();

		pApplet.pushMatrix();
		pApplet.translate(getRelCenterX(), getRelCenterY());
		pApplet.shape(circleShape);
		pApplet.popMatrix();
		
		for(int i=0; i<children.length; i++){
			linesTochildren[i].draw();
			children[i].draw();
		}
	}

	public void calcPosition() {
		int horizontalStageDist = context.getWidth() / (stageLength - 1);
		absCenterX = horizontalStageDist * stageIndex + horizontalStageDist/2;
		absCenterY = context.VERTICAL_DIFF * stage;
		
		setRelCenterX((int) (((double)absCenterX - visualWindow.getMarginLeft())/visualWindow.getWidth() * context.getWidth() + context.getMarginLeft() ));
		setRelCenterY((int) (((double)absCenterY - visualWindow.getMarginTop())/visualWindow.getHeight() * context.getHeight() + context.getMarginTop() ));
		
		for(NodeCircle child : children){
			child.calcPosition();
		}
	}

	private void update() {
		if(isMouseInside())
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT_MOUSE_OVER);
		else
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT);
	}

	public void setChildren(NodeCircle[] children) {
		this.children = children;
	}

	public NodeCircle[] getChildren() {
		return children;
	}

	public void setChildrenShape(PShape shape){
		this.circleShape = shape;
		for(NodeCircle child : children){
			child.setChildrenShape(shape);
		}
	}
	
	private boolean isMouseInside() {
		int diffX = pApplet.mouseX-relCenterX;
		int diffY = pApplet.mouseY-relCenterY;
		if(radius*radius > diffX*diffX + diffY*diffY)
			return true;
		return false;
	}
	
	public void setChildrenRadius(int radius){
		this.radius = radius;
		for(NodeCircle child : children){
			child.setChildrenRadius(radius);
		}
	}

	public void setStageIndex(int stageIndex) {
		this.stageIndex = stageIndex;
	}
	
	public void setStageLength(int stageLength){
		this.stageLength = stageLength;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public NodeContext getFrame() {
		return context;
	}

	public void setFrame(NodeContext frame) {
		this.context = frame;
	}
	
	public int getRadius() {
		return radius;
	}

	public NodeLine[] getLinesTochildren() {
		return linesTochildren;
	}

	public void setLinesTochildren(NodeLine[] linesTochildren) {
		this.linesTochildren = linesTochildren;
	}

	public int getAbsCenterX() {
		return absCenterX;
	}
	
	public int getAbsCenterY() {
		return absCenterY;
	}
	
	public NodeContext getVisualWindow() {
		return visualWindow;
	}

	public void setVisualWindow(NodeContext visualWindow) {
		this.visualWindow = visualWindow;
		
		for(NodeCircle child : children){
			child.setVisualWindow(visualWindow);
		}
	}

	public int getRelCenterX() {
		return relCenterX;
	}

	public void setRelCenterX(int relCenterX) {
		this.relCenterX = relCenterX;
	}

	public int getRelCenterY() {
		return relCenterY;
	}

	public void setRelCenterY(int relCenterY) {
		this.relCenterY = relCenterY;
	}
}
