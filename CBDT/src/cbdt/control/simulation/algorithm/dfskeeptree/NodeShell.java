package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;

public class NodeShell {

	private NodeContentKeepTree content;
	
	List<NodeShell> children;

	public NodeShell(NodeContentKeepTree content) {
		this.setContent(content);
		this.children = new ArrayList<NodeShell>();
	}

	public void setChildren(List<NodeShell> children) {
		this.children = children;
	}

	public List<NodeShell> getChildren() {
		return children;
	}
	
	public NodeContentKeepTree getContent() {
		return content;
	}

	public void setContent(NodeContentKeepTree content) {
		this.content = content;
	}

}
