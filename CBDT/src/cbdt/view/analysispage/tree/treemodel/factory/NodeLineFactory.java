package cbdt.view.analysispage.tree.treemodel.factory;

import cbdt.view.analysispage.tree.TreePApplet;
import cbdt.view.analysispage.tree.treemodel.NodeCircle;
import cbdt.view.analysispage.tree.treemodel.NodeLine;

/**
 * This factory class produces the {@link NodeLine} objects for the document
 * model of the tree.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class NodeLineFactory {

	private TreePApplet pApplet;

	/**
	 * The constructor.
	 * 
	 * @param pApplet
	 */
	public NodeLineFactory(TreePApplet pApplet) {
		this.pApplet = pApplet;
	}

	/**
	 * Produces the {@link NodeLine} objects for the tree in the document model.
	 * 
	 * @param rootCircle
	 */
	public void createDocumentNodeLinesRecursively(NodeCircle rootCircle) {
		NodeLine[] outgoingLines = new NodeLine[rootCircle.getChildren().length];
		int i = 0;
		for (NodeCircle child : rootCircle.getChildren()) {
			outgoingLines[i] = new NodeLine(pApplet, rootCircle, child);
			i++;
		}
		rootCircle.setOutgoingLines(outgoingLines);
		for (NodeCircle child : rootCircle.getChildren()) {
			createDocumentNodeLinesRecursively(child);
		}
	}

}
