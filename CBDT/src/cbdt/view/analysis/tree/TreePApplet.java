package cbdt.view.analysis.tree;

import java.awt.Point;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import processing.core.PApplet;
import processing.core.PShape;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeContentKeepTree;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;
import cbdt.model.parameters.ActorAction;

public class TreePApplet extends PApplet{
	private static final long serialVersionUID = 1L;

	private NodeCircle rootCircle;

	private PShape circleShape;
	
	List<Integer> stageIndexes;
	private NodeContext nodeFrame;
	private NodeContext visualWindow;

	private NodeCircle infoShowingCircle;

	private ZoomConverter coordinateConverter;

	private DataRectangleShower dataRectangleShower;

	private ZoomConverter zoomConverter;

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
		coordinateConverter = new ZoomConverter(nodeFrame, visualWindow);
		dataRectangleShower = new DataRectangleShower(this);
		zoomConverter = new ZoomConverter(nodeFrame, visualWindow);
	}
	
	public void setup() {
		size(800, 800, P2D);
		background(255);
		smooth();

		int radius = 10;
		
		circleShape = createShape(ELLIPSE, 0, 0, radius*2, radius*2);
		if(rootCircle!=null){
			NodeCircleVisitor visitor = new NodeCircleVisitor();
			visitor.setShape(rootCircle, circleShape);
			visitor.setRadius(rootCircle, radius);
		}
		
		addMouseWheelListener(new MouseWheelListener() { 
		    public void mouseWheelMoved(MouseWheelEvent mwe) { 
		      zoomConverter.updateZoom(mwe.getWheelRotation(), mwe.getPoint());
//		      rootCircle.calcPositionRecursively();
		  }});
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
//		setStageLengths(rootCircle, 0);
//		rootCircle.calcPositionRecursively();
	}
	
//	private void setStageLengths(NodeCircle nodeCircle, int stage) {
//		nodeCircle.setNumberOfNodesOnStage(stageIndexes.get(stage)+1);
//		for(NodeCircle child : nodeCircle.getChildren()){
//			setStageLengths(child, stage+1);
//		}
//	}

	private NodeCircle getNodeCircle(NodeShell nodeShell, int stage){
		NodeCircle nodeCircle = new NodeCircle(this, dataRectangleShower, coordinateConverter);
		nodeCircle.setRepresentedShell(nodeShell);
//		while(stageIndexes.size()<stage+1){
//			stageIndexes.add(new Integer(0));
//		}
//		nodeCircle.setIndexOnStage(stageIndexes.get(stage));
//		stageIndexes.set(stage, stageIndexes.get(stage) +1);
//		nodeCircle.setStagesIndex(stage);
		
		NodeCircle[] children = new NodeCircle[nodeShell.getChildren().size()];
		NodeLine[] linesToChildren = new NodeLine[nodeShell.getChildren().size()];
		
		Map<ActorAction, List<NodeCircle>> actionOccuranceMap = new HashMap<ActorAction, List<NodeCircle>>(); 
		
		int i=0;
		for(NodeShell shell : nodeShell.getChildren()){
			NodeContentKeepTree childContent = shell.getContent();
			if(childContent!=null && childContent.getLastAction()!=null)
				children[i] = getNodeCircle(shell, stage+1);
				if(!actionOccuranceMap.containsKey(childContent.getLastAction())){
//					linesToChildren[i] = new NodeLine(this, nodeCircle, children[i]);
					List<NodeCircle> sameActionList = new ArrayList<NodeCircle>();
					sameActionList.add(children[i]);
					actionOccuranceMap.put(childContent.getLastAction(), sameActionList);
				}else{
					actionOccuranceMap.get(childContent.getLastAction()).add(children[i]);
				}
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
