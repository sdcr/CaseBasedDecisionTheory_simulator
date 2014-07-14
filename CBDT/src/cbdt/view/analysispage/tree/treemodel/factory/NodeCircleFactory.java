package cbdt.view.analysispage.tree.treemodel.factory;

import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import cbdt.model.result.tree.NodeShell;
import cbdt.view.analysispage.tree.TreePApplet;
import cbdt.view.analysispage.tree.treemodel.NodeCircle;

/**
 * This factory class is able to compute the {@link NodeCircle} objects for the
 * document model recursively.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class NodeCircleFactory {

	private TreePApplet pApplet;
	private DocumentLayoutManager layoutManager;
	private Map<Integer, Integer> indexOfNodeOnStageMap;
	private Map<Integer, Integer> numberOfNodesOnStageMap;

	/**
	 * Constructor.
	 * 
	 * @param pApplet
	 * @param layoutManager
	 */
	public NodeCircleFactory(TreePApplet pApplet,
			DocumentLayoutManager layoutManager) {
		this.pApplet = pApplet;
		this.layoutManager = layoutManager;
	}

	/**
	 * Computes the {@link NodeCircle} objects for the tree in the document
	 * model.
	 * 
	 * @param rootShell
	 * @return
	 */
	public NodeCircle createDocumentNodeCircles(NodeShell rootShell) {
		indexOfNodeOnStageMap = new HashMap<Integer, Integer>();
		numberOfNodesOnStageMap = new HashMap<Integer, Integer>();
		calculateNumberOfNodesOnStageMap(rootShell, 0);
		return createNodeCirclesRecursively(rootShell, 0);
	}

	/**
	 * Computes the number of nodes on each stage of the tree.
	 * 
	 * @param rootShell
	 * @param indexOfStage
	 */
	private void calculateNumberOfNodesOnStageMap(NodeShell rootShell,
			int indexOfStage) {
		getNextNumberOnStage(numberOfNodesOnStageMap, indexOfStage, 1);

		for (NodeShell childShell : rootShell.getChildren()) {
			calculateNumberOfNodesOnStageMap(childShell, indexOfStage + 1);
		}
	}

	/**
	 * Recursively computes the {@link NodeCircle} objects for the tree.
	 * 
	 * @param nodeShell
	 * @param indexOfStage
	 * @return
	 */
	private NodeCircle createNodeCirclesRecursively(NodeShell nodeShell,
			int indexOfStage) {
		NodeCircle nodeCircle = new NodeCircle(pApplet);
		nodeCircle.setRepresentedShell(nodeShell);

		int newIndexOfNodeOnStage = getNextNumberOnStage(indexOfNodeOnStageMap,
				indexOfStage, 0);

		Point documentCoordinate = new Point();
		documentCoordinate.x = layoutManager.convertToDocumentCoordinatesX(
				numberOfNodesOnStageMap.get(indexOfStage),
				newIndexOfNodeOnStage);
		documentCoordinate.y = layoutManager
				.convertToDocumentCoordinatesY(indexOfStage);
		nodeCircle.setDocumentCoordinate(documentCoordinate);

		NodeCircle[] children = new NodeCircle[nodeShell.getChildren().size()];

		int i = 0;
		for (NodeShell childShell : nodeShell.getChildren()) {
			children[i] = createNodeCirclesRecursively(childShell,
					indexOfStage + 1);
			i++;
		}

		nodeCircle.setChildren(children);
		return nodeCircle;
	}

	/**
	 * Given a {@link Map} and an index, looks up the current number to which
	 * the map maps, increases that value by 1, and returns the new number. If
	 * the map does not map to a number for that index yet, the map is
	 * initialized for that index with the given parameter. That parameter is
	 * also returned.
	 * 
	 * @param stageMap
	 * @param indexOfStage
	 * @param firstNumberOnStage
	 * @return
	 */
	private int getNextNumberOnStage(Map<Integer, Integer> stageMap,
			int indexOfStage, int firstNumberOnStage) {
		int nextNumberOnStage;
		Integer lastNumberOnStage = stageMap.get(indexOfStage);

		if (lastNumberOnStage != null) {
			nextNumberOnStage = lastNumberOnStage + 1;
		} else {
			nextNumberOnStage = firstNumberOnStage;
		}
		stageMap.put(indexOfStage, nextNumberOnStage);
		return nextNumberOnStage;
	}
}
