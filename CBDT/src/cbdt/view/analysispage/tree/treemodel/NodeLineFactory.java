package cbdt.view.analysispage.tree.treemodel;

import cbdt.view.analysispage.tree.TreePApplet;

public class NodeLineFactory {
	
	private TreePApplet pApplet;

	public NodeLineFactory(TreePApplet pApplet) {
		this.pApplet = pApplet;
	}
	
	public void createNodeLinesRecursively(NodeCircle rootCircle){
		NodeLine[] outgoingLines = new NodeLine[rootCircle.getChildren().length];
		int i=0;
		for(NodeCircle child : rootCircle.getChildren()){
			outgoingLines[i] = new NodeLine(pApplet, rootCircle, child);
			i++;
		}
		rootCircle.setOutgoingLines(outgoingLines);
		for(NodeCircle child : rootCircle.getChildren()){
			createNodeLinesRecursively(child);
		}
	}
	
}
