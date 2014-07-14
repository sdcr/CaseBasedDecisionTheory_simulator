package cbdt.view.analysispage.tree;

import cbdt.view.analysispage.tree.treemodel.NodeCircle;
import cbdt.view.analysispage.tree.treemodel.NodeLine;

/**
 * This class is able to draw a subtree of {@link NodeCircle}s and
 * {@link NodeLine}s, by recursively visiting each node in the subtree and
 * making each object draw itself in visual window coordinates.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class NodeDrawVisitor {

	/**
	 * Draws the subtree under the given {@link NodeCircle} in visual window
	 * coordinates. All {@link NodeCircle}s and {@link NodeLine}s of the subtree
	 * are drawn.
	 * 
	 * @param rootNode
	 */
	public void drawSubtree(NodeCircle rootNode) {
		rootNode.drawInVisualWindow();
		for (NodeLine outgoingLine : rootNode.getOutgoingLines()) {
			outgoingLine.drawInVisualWindow();
		}
		for (NodeCircle child : rootNode.getChildren()) {
			this.drawSubtree(child);
		}
	}
}
