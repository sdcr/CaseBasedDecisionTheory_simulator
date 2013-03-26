package cbdt.view.analysis.tree;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PShape;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;

public class TreePApplet extends PApplet{
	public static final int WIDTH = 800;

	public static final int HEIGHT = 800;

	private static final long serialVersionUID = 1L;

	private NodeCircle rootCircle;

	List<Integer> stageIndexes;
	private NodeContext nodeFrame;
	private NodeContext visualWindow;

	private NodeCircle infoShowingCircle;

	private DataRectangleShower dataRectangleShower;

	private ZoomConverter zoomConverter;

	private NodeCircleVisitor visitor;

	private PShape circleShape;

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
		dataRectangleShower = new DataRectangleShower(this);
		zoomConverter = new ZoomConverter(nodeFrame, visualWindow);
		visitor = new NodeCircleVisitor();
	}
	
	public void setup() {
		size(WIDTH, HEIGHT, P2D);
		background(255);

		circleShape = createShape(ELLIPSE, 0, 0, NodeCircle.radius*2, NodeCircle.radius*2);
		
		addMouseWheelListener(new MouseWheelListener() { 
		    public void mouseWheelMoved(MouseWheelEvent mwe) { 
		      zoomConverter.updateZoom(mwe.getWheelRotation(), mwe.getPoint());
		  }});
	}

	public void draw() {
		background(255);
		if(rootCircle!=null){
			if(rootCircle.getCircleShape()==null && circleShape!=null)
				visitor.setShape(rootCircle, circleShape);
			visitor.draw(rootCircle);
		}
		if(infoShowingCircle != null)
			infoShowingCircle.showDataRectangle(dataRectangleShower);
		infoShowingCircle = null;
	}
	
	public void setTreeModel(NodeShell rootShell){
		NodeLayoutManager layoutManager = new NodeLayoutManager(nodeFrame);
		NodeCircleFactory factory = new NodeCircleFactory(this, layoutManager);
		rootCircle = factory.createNodeCircles(rootShell);
		NodeLineFactory lineFactory = new NodeLineFactory(this);
		lineFactory.createNodeLinesRecursively(rootCircle);
	}

	public void setInfoShowingCircle(NodeCircle nodeCircle) {
		this.infoShowingCircle = nodeCircle;
	}

	public ZoomConverter getZoomConverter() {
		return zoomConverter;
	}
}
