package cbdt.view.analysis.tree;

import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PFont;
import processing.core.PShape;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;

public class TreePApplet extends PApplet{
	private static final long serialVersionUID = 1L;

	private NodeCircle rootCircle;

	private PShape circleShape;
	private PShape lineShape;
	
	private int ROOT_X = 300;
	private int ROOT_Y = 200;
	
	List<Integer> stageIndexes;
	private NodeContext nodeFrame;
	private NodeContext visualWindow;

	private NodeCircle infoShowingCircle;

	public TreePApplet() {
		stageIndexes = new ArrayList<Integer>();
		nodeFrame = new NodeContext();
		nodeFrame.setWidth(500);
		nodeFrame.setHeight(500);
		nodeFrame.setMarginLeft(50);
		nodeFrame.setMarginTop(50);
		visualWindow = new NodeContext();
		visualWindow.setMarginLeft(0);
		visualWindow.setMarginTop(0);
		visualWindow.setWidth(500);
		visualWindow.setHeight(500);
//		PFont f = createFont("Arial", 16, true);
//		textFont(f);
	}
	
	public void setup() {
		size(800, 800, P2D);
		background(255);
		smooth();

		int radius = 10;
		
		circleShape = createShape(ELLIPSE, 0, 0, radius*2, radius*2);
		if(rootCircle!=null){
			rootCircle.setChildrenShape(circleShape);
			rootCircle.setChildrenRadius(radius);
		}
		
		addMouseWheelListener(new MouseWheelListener() { 
		    public void mouseWheelMoved(MouseWheelEvent mwe) { 
		      mouseWheel(mwe.getWheelRotation(), mwe.getPoint());
		  }});
	}

	protected void mouseWheel(int wheelRotation, Point mousePos) {
		System.out.println(wheelRotation);
		mousePos = getInDocumentCoordinates(mousePos);
		
		if(wheelRotation<0){
			visualWindow.setWidth((int) (visualWindow.getWidth() * 0.8));
			visualWindow.setHeight((int) (visualWindow.getHeight() * 0.8));
			visualWindow.setMarginLeft((int) (visualWindow.getMarginLeft() + (mousePos.x - visualWindow.getMarginLeft()) * 0.2));
			visualWindow.setMarginTop((int) (visualWindow.getMarginTop() + (mousePos.y - visualWindow.getMarginTop()) * 0.2));
		}else{
			visualWindow.setWidth((int) (visualWindow.getWidth() * 1.25));
			visualWindow.setHeight((int) (visualWindow.getHeight() * 1.25));
			visualWindow.setMarginLeft((int) (visualWindow.getMarginLeft() - (mousePos.x - visualWindow.getMarginLeft()) * 0.25));
			visualWindow.setMarginTop((int) (visualWindow.getMarginTop() - (mousePos.y - visualWindow.getMarginTop()) * 0.25));
		}
		
		rootCircle.calcPosition();
	}

	private Point getInDocumentCoordinates(Point mousePos) {
		Point newPoint = new Point();
		newPoint.x = (int) (((double)(mousePos.x - nodeFrame.getMarginLeft()) / nodeFrame.getWidth() * visualWindow.getWidth()) + visualWindow.getMarginLeft());
		newPoint.y = (int) (((double)(mousePos.y - nodeFrame.getMarginTop()) / nodeFrame.getHeight() * visualWindow.getHeight()) + visualWindow.getMarginTop());
		return newPoint;
	}

	public void draw() {
		background(255);
		rootCircle.draw();
		if(infoShowingCircle != null)
			infoShowingCircle.showDataRectangle();
		infoShowingCircle = null;
	}
	
	public void setTreeModel(NodeShell rootShell){
		rootCircle = getNodeCircle(rootShell, 0);
		setStageLengths(rootCircle, 0);
		rootCircle.setVisualWindow(visualWindow);
		rootCircle.calcPosition();
	}
	
	private void setStageLengths(NodeCircle nodeCircle, int stage) {
		nodeCircle.setStageLength(stageIndexes.get(stage)+1);
		for(NodeCircle child : nodeCircle.getChildren()){
			setStageLengths(child, stage+1);
		}
	}

	private NodeCircle getNodeCircle(NodeShell nodeShell, int stage){
		NodeCircle nodeCircle = new NodeCircle(this);
		nodeCircle.setRepresentedShell(nodeShell);
		while(stageIndexes.size()<stage+1){
			stageIndexes.add(new Integer(0));
		}
		nodeCircle.setStageIndex(stageIndexes.get(stage));
		stageIndexes.set(stage, stageIndexes.get(stage) +1);
		nodeCircle.setStage(stage);
		nodeCircle.setFrame(nodeFrame);
		
		NodeCircle[] children = new NodeCircle[nodeShell.getChildren().size()];
		NodeLine[] linesToChildren = new NodeLine[nodeShell.getChildren().size()];
		
		int i=0;
		for(NodeShell shell : nodeShell.getChildren()){
			children[i] = getNodeCircle(shell, stage+1);
			linesToChildren[i] = new NodeLine(this, null, nodeCircle, children[i]);
			i++;
		}
		nodeCircle.setChildren(children);
		nodeCircle.setLinesTochildren(linesToChildren);
		return nodeCircle;
	}

	public void setInfoShowingCircle(NodeCircle nodeCircle) {
		this.infoShowingCircle = nodeCircle;
	}
}
