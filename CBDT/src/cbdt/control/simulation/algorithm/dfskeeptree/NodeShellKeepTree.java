package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;

public class NodeShellKeepTree {

	private NodeContentKeepTree content;
	
	List<NodeShellKeepTree> children;

	public NodeShellKeepTree(NodeContentKeepTree content) {
		this.setContent(content);
		this.children = new ArrayList<NodeShellKeepTree>();
	}

	public void setChildren(List<NodeShellKeepTree> children) {
		this.children = children;
	}

	public List<NodeShellKeepTree> getChildren() {
		return children;
	}
	
	public NodeContentKeepTree getContent() {
		return content;
	}

	public void setContent(NodeContentKeepTree content) {
		this.content = content;
	}

}
