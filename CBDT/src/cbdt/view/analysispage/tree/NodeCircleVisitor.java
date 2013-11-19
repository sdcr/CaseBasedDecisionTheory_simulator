package cbdt.view.analysispage.tree;

import cbdt.view.analysispage.tree.treemodel.NodeCircle;
import cbdt.view.analysispage.tree.treemodel.NodeLine;
import processing.core.PShape;

public class NodeCircleVisitor {

	
	/**
	 * Sets the shape of all NodeCircle objects in the subtree starting from the passed NodeCircle.
	 * 
	 * @param rootCircle
	 * @param shape
	 */
	public void setShape(NodeCircle rootCircle, PShape shape){
		rootCircle.setShape(shape);
		for(NodeCircle child : rootCircle.getChildren()){
			this.setShape(child, shape);
		}
	}
	
	public void draw(NodeCircle rootNode){
		rootNode.draw();
		for(NodeLine outgoingLine : rootNode.getOutgoingLines()){
			outgoingLine.draw();
		}
		for(NodeCircle child : rootNode.getChildren()){
			this.draw(child);
		}
	}
}
