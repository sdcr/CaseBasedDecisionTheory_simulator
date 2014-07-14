package cbdt.view.analysispage.tree.treemodel.factory;

import cbdt.view.analysispage.tree.treemodel.NodeFrame;

/**
 * This class defines how nodes of the tree are mapped to points in the document
 * frame.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class DocumentLayoutManager {

	/**
	 * The vertical distance between nodes in the document frame.
	 */
	private final int VERTICAL_DIST = 100;

	/**
	 * The document frame to which nodes are mapped.
	 */
	public NodeFrame documentFrame;

	/**
	 * Constructor.
	 * 
	 * @param documentFrame
	 */
	public DocumentLayoutManager(NodeFrame documentFrame) {
		this.documentFrame = documentFrame;
	}

	/**
	 * Takes the location of a node in its stage of the tree, and returns the X
	 * coordinate of the node in the document frame. <br>
	 * All nodes are placed equidistant across the width of the document frame.
	 * 
	 * @param numberOfNodesOnStage
	 * @param indexOnStage
	 * @return
	 */
	public int convertToDocumentCoordinatesX(int numberOfNodesOnStage,
			int indexOnStage) {
		int widthPerNode = documentFrame.getWidth() / numberOfNodesOnStage;
		return widthPerNode * indexOnStage + widthPerNode / 2;
	}

	/**
	 * Takes the location of a node in terms of its depth in the tree, and
	 * returns the Y coordinate of the node in the document frame. <br>
	 * The root node is placed on the top border of the frame. Every other node
	 * is placed with a distance from the top proportionate to its depth.
	 * 
	 * @param indexOfTheStage
	 * @return
	 */
	public int convertToDocumentCoordinatesY(int indexOfTheStage) {
		return VERTICAL_DIST * indexOfTheStage;
	}

}
