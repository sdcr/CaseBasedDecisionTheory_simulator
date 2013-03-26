package cbdt.view.analysis.tree.treemodel;

import java.util.HashMap;
import java.util.Map;

import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;
import cbdt.view.analysis.tree.NodeLayoutManager;
import cbdt.view.analysis.tree.TreePApplet;

public class NodeCircleFactory {
	
	private TreePApplet pApplet;
	private NodeLayoutManager layoutManager;
	private Map<Integer, Integer> indexOfNodeOnStageMap;
	private Map<Integer, Integer> numberOfNodesOnStageMap;
	
	public NodeCircleFactory(TreePApplet pApplet, NodeLayoutManager layoutManager) {
		this.pApplet = pApplet;
		this.layoutManager = layoutManager;
	}

	public NodeCircle createNodeCircles(NodeShell rootShell){
		indexOfNodeOnStageMap = new HashMap<Integer, Integer>();
		numberOfNodesOnStageMap = new HashMap<Integer, Integer>();
		calculateStageWidthMap(rootShell, 0);
		return createNodeCirclesRecursively(rootShell, 0);
	}
	
	private void calculateStageWidthMap(NodeShell rootShell, int indexOfStage) {
		getNewIndexOnStage(numberOfNodesOnStageMap, indexOfStage, 1);
		
		for(NodeShell childShell : rootShell.getChildren()){
			calculateStageWidthMap(childShell, indexOfStage+1);
		}
	}

	private NodeCircle createNodeCirclesRecursively(NodeShell nodeShell, int indexOfStage){
		NodeCircle nodeCircle = new NodeCircle(pApplet);
		nodeCircle.setRepresentedShell(nodeShell);

		int newIndexOfNodeOnStage = getNewIndexOnStage(indexOfNodeOnStageMap, indexOfStage, 0);
		
		int documentCoordinateX = layoutManager.convertToDocumentCoordinatesX(numberOfNodesOnStageMap.get(indexOfStage), newIndexOfNodeOnStage);
		int documentCoordinateY = layoutManager.convertToDocumentCoordinatesY(indexOfStage);
		
		nodeCircle.setDocumentCoordinateX(documentCoordinateX);
		nodeCircle.setDocumentCoordinateY(documentCoordinateY);
		
		NodeCircle[] children = new NodeCircle[nodeShell.getChildren().size()];
		
		int i=0;
		for(NodeShell childShell : nodeShell.getChildren()){
			children[i] = createNodeCirclesRecursively(childShell, indexOfStage + 1);
			i++;
		}
		
		nodeCircle.setChildren(children);
		return nodeCircle;
	}

	private int getNewIndexOnStage(Map<Integer, Integer> stageMap, int indexOfStage, int firstIndexOnStage) {
		Integer lastIndexOfNodeOnStage = stageMap.get(indexOfStage);
		int newIndexOfNodeOnStage = firstIndexOnStage;
		if (lastIndexOfNodeOnStage!=null) {
			newIndexOfNodeOnStage = lastIndexOfNodeOnStage + 1;
		}
		stageMap.put(indexOfStage, newIndexOfNodeOnStage);
		return newIndexOfNodeOnStage;
	}
}
