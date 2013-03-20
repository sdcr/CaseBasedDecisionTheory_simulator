package cbdt.view.analysis.tree;

import processing.core.PShape;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShellKeepTree;

public class NodeCircle {


	private int DEFAULT_STROKE_WEIGHT = 1;
	private int DEFAULT_STROKE_WEIGHT_MOUSE_OVER = 5;
	
	private TreePApplet pApplet;
	private NodeCircle[] children;
	private NodeLine[] linesTochildren;

	private PShape circleShape;
	private int radius;

	private int stageIndex;
	private int stageLength;
	private int stage;
	
	private NodeContext context;
	private NodeContext visualWindow;
	private int relCenterX;
	private int relCenterY;

	private NodeShellKeepTree representedShell;

	public NodeCircle(TreePApplet treePApplet) {
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
		int absCenterX = horizontalStageDist * stageIndex + horizontalStageDist/2;
		int absCenterY = NodeContext.VERTICAL_DIFF * stage;
		
		setRelCenterX((int) (((double)absCenterX - visualWindow.getMarginLeft())/visualWindow.getWidth() * context.getWidth() + context.getMarginLeft() ));
		setRelCenterY((int) (((double)absCenterY - visualWindow.getMarginTop())/visualWindow.getHeight() * context.getHeight() + context.getMarginTop() ));
		
		for(NodeCircle child : children){
			child.calcPosition();
		}
	}

	private void update() {
		if(isMouseInside()){
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT_MOUSE_OVER);
			pApplet.setInfoShowingCircle(this);
		} else
			circleShape.setStrokeWeight(DEFAULT_STROKE_WEIGHT);
	}

	public void showDataRectangle() {
		if(getRepresentedShell().getContent() != null){
			pApplet.fill(255);
			pApplet.rect(pApplet.mouseX+10, pApplet.mouseY+10, 150, 50);
			
			pApplet.fill(0);
			int offsetY = 0;			

			if(getRepresentedShell().getContent().getLastAction() != null){
				String lastActionName = getRepresentedShell().getContent().getLastAction().getActionName();
				pApplet.text("Last action: "+lastActionName, pApplet.mouseX+30, pApplet.mouseY+25);
				offsetY = 15;
			}

			double aspLevel = getRepresentedShell().getContent().getAspirationLevel();
			pApplet.text("Asp. level: "+aspLevel, pApplet.mouseX+30, pApplet.mouseY+25 + offsetY);

			double probProd = getRepresentedShell().getContent().getProbabilityProduct();
			pApplet.text("Prob.: "+probProd, pApplet.mouseX+30, pApplet.mouseY+40 + offsetY);
		}
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

	public NodeShellKeepTree getRepresentedShell() {
		return representedShell;
	}

	public void setRepresentedShell(NodeShellKeepTree representedShell) {
		this.representedShell = representedShell;
	}
}
