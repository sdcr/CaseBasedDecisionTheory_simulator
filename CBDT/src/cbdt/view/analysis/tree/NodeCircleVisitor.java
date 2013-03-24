package cbdt.view.analysis.tree;

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
			child.setShape(shape);
		}
	}
	
	/**
	 * Sets the radius of all NodeCircle objects in the subtree starting from the passed NodeCircle.
	 * 
	 * @param rootCircle
	 * @param radius
	 */
	public void setRadius(NodeCircle rootCircle, int radius){
		rootCircle.setRadius(radius);
		for(NodeCircle child : rootCircle.getChildren()){
			child.setRadius(radius);
		}
	}
	
	public void setDocumentCoordinates(NodeLayoutManager layoutManager, NodeCircle rootNode){
		
	}
}
