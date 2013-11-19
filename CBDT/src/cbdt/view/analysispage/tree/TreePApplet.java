package cbdt.view.analysispage.tree;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.List;

import processing.core.PApplet;
import processing.core.PShape;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;
import cbdt.model.config.DFSkeepTreeEngineConfig;
import cbdt.view.analysispage.tree.treemodel.NodeCircle;
import cbdt.view.analysispage.tree.treemodel.NodeCircleFactory;
import cbdt.view.analysispage.tree.treemodel.NodeContext;
import cbdt.view.analysispage.tree.treemodel.NodeLineFactory;

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

	boolean drawHasBeenCalledBefore = false;

	public TreePApplet() {
		System.out.println("TreePApplet.Constructor: After entering constructor");
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
		zoomConverter = new ZoomConverter(nodeFrame, visualWindow);
		visitor = new NodeCircleVisitor();
		System.out.println("TreePApplet.Constructor: before leaving constructor");
	}
	
	public void setup() {
		System.out.println("TreePApplet.setup(): before calling size(WIDTH, HEIGHT, JAVA2D)");
		size(WIDTH, HEIGHT, JAVA2D);
		System.out.println("TreePApplet.setup(): after calling size(WIDTH, HEIGHT, JAVA2D)");
		background(255);

		//only needed when using P2D/P3D rendering
//		circleShape = createShape(ELLIPSE, 0, 0, NodeCircle.radius*2, NodeCircle.radius*2);
		
		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent mwe) {
				zoomConverter
						.updateZoom(mwe.getWheelRotation(), mwe.getPoint());
			}
		});
		
		System.out.println("TreePApplet.setup(): before leaving TreePApplet.setup()");
	}
	
	public void draw() {
		if(drawHasBeenCalledBefore == false)
			System.out.println("TreePApplet.draw(): after entering first call to draw()");
		
		background(255);
		if(rootCircle!=null){
			if(rootCircle.getCircleShape()==null && circleShape!=null)
				visitor.setShape(rootCircle, circleShape);
			visitor.draw(rootCircle);
		}
		if(infoShowingCircle != null)
			infoShowingCircle.showDataRectangle(dataRectangleShower);
		infoShowingCircle = null;
		
		if(drawHasBeenCalledBefore == false)
			System.out.println("TreePApplet.draw(): before leaving first call to draw()");
		drawHasBeenCalledBefore = true;
	}
	
	public void setTreeModel(NodeShell rootShell, DFSkeepTreeEngineConfig config){
		System.out.println("TreePApplet.setTreeModel(NodeShell rootShell, DFSkeepTreeEngineConfig config): after entering setTreModel()");
		NodeLayoutManager layoutManager = new NodeLayoutManager(nodeFrame);
		NodeCircleFactory factory = new NodeCircleFactory(this, layoutManager);
		rootCircle = factory.createNodeCircles(rootShell);
		NodeLineFactory lineFactory = new NodeLineFactory(this);
		lineFactory.createNodeLinesRecursively(rootCircle);
		dataRectangleShower = new DataRectangleShower(this, config);
		System.out.println("TreePApplet.setTreeModel(NodeShell rootShell, DFSkeepTreeEngineConfig config): before leaving setTreModel()");
	}

	public void setInfoShowingCircle(NodeCircle nodeCircle) {
		this.infoShowingCircle = nodeCircle;
	}

	public ZoomConverter getZoomConverter() {
		return zoomConverter;
	}
}
