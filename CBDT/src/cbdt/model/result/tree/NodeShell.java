package cbdt.model.result.tree;

import java.util.ArrayList;
import java.util.List;

/**
 * This class models a node of the computed tree. In particular, it has a list
 * of child nodes and a reference to a {@link NodeContent} object.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class NodeShell {

	private NodeContent content;

	List<NodeShell> children;

	/**
	 * The constructor.
	 * 
	 * @param content
	 *            {@link NodeContent}
	 */
	public NodeShell(NodeContent content) {
		this.setContent(content);
		this.children = new ArrayList<NodeShell>();
	}

	/**
	 * @param children
	 *            The list of children of this node.
	 */
	public void setChildren(List<NodeShell> children) {
		this.children = children;
	}

	/**
	 * @return The list of children of this node.
	 */
	public List<NodeShell> getChildren() {
		return children;
	}

	/**
	 * @return {@link NodeContent}
	 */
	public NodeContent getContent() {
		return content;
	}

	/**
	 * @param content
	 *            {@link NodeContent}
	 */
	public void setContent(NodeContent content) {
		this.content = content;
	}

}
