package cbdt.view.analysispage.tree.treemodel;

/**
 * This class represents a frame containing the coordinates of the
 * {@link NodeCircle}s and {@link NodeLine}s. This class does not have any
 * references to contained objects, but is only used for conversion of
 * coordinates between the document model and the visual window model.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class NodeFrame {

	private int width;
	private int height;
	private int marginLeft;
	private int marginTop;

	/**
	 * @return The width of this frame.
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width
	 *            The width of this frame.
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * @return The height of the frame.
	 */
	public int getHeight() {
		return height;
	}
	
	/**
	 * @param height The height of the frame.
	 */
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	 * The left margin is a certain distance from the left border inward into
	 * the frame.
	 * 
	 * @return The left margin of this frame.
	 */
	public int getMarginLeft() {
		return marginLeft;
	}

	/**
	 * The left margin is a certain distance from the left border inward into
	 * the frame.
	 * 
	 * @param marginLeft The left margin of this frame.
	 */
	public void setMarginLeft(int marginLeft) {
		this.marginLeft = marginLeft;
	}

	/**
	 * The top margin is a certain distance from the top border inward into
	 * the frame.
	 * 
	 * @return The top margin of this frame.
	 */
	public int getMarginTop() {
		return marginTop;
	}

	/**
	 * The top margin is a certain distance from the top border inward into
	 * the frame.
	 * 
	 * @param The top margin of this frame.
	 */
	public void setMarginTop(int marginTop) {
		this.marginTop = marginTop;
	}
}
