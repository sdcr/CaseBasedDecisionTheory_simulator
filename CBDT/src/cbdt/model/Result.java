package cbdt.model;


public class Result {
	
	private TreeNode rootNode;

	public TreeNode getRootNode() {
		return rootNode;
	}

	public void setRootNode(TreeNode rootNode) {
		this.rootNode = rootNode;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "RootNode: "+rootNode.toString();
	}
}
