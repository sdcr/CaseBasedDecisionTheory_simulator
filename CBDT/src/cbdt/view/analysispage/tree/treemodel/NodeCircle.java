package cbdt.view.analysispage.tree.treemodel;

import java.awt.Point;

import cbdt.model.result.tree.NodeShell;
import cbdt.view.analysispage.tree.TreePApplet;
import cbdt.view.analysispage.tree.ZoomConverter;

/**
 * This class models a circle in the document model. It is able to draw itself
 * in visual window coordinates by using the {@link ZoomConverter} referenced by
 * {@link TreePApplet}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class NodeCircle {

	/**
	 * The stroke weight of this circle, in case of no mouse over.
	 */
	private int DEFAULT_STROKE_WEIGHT = 1;

	/**
	 * The stroke weight of this circle, in case of mouse over.
	 */
	private int DEFAULT_STROKE_WEIGHT_MOUSE_OVER = 3;

	/**
	 * The radius of this circle.
	 */
	public static final int RADIUS = 10;

	private TreePApplet pApplet;

	/**
	 * This circle's child circles.
	 */
	private NodeCircle[] children;

	/**
	 * The line elements to the circle's children.
	 */
	private NodeLine[] outgoingLines;

	/**
	 * The coordinates of this circle in the document model.
	 */
	private Point documentCoordinate;

	/**
	 * The {@link NodeShell} object represented by this {@link NodeCircle}.
	 */
	private NodeShell representedShell;

	/**
	 * Constructor.
	 * 
	 * @param treePApplet
	 */
	public NodeCircle(TreePApplet treePApplet) {
		pApplet = treePApplet;
		outgoingLines = new NodeLine[0];
	}

	/**
	 * Makes the {@link NodeCircle} object draw itself in visual window
	 * coordinates. It uses the {@link ZoomConverter} referenced by
	 * {@link TreePApplet} to convert its document coordinates to visual window
	 * coordinates.<br>
	 * If the mouse pointer is inside this circle, this {@link NodeCircle}
	 * object is set to be the info showing circle in the {@link TreePApplet}.
	 */
	public void drawInVisualWindow() {
		Point windowCoordinates = pApplet.getZoomConverter()
				.convertToWindowCoordinates(documentCoordinate);

		if (isMouseInside(windowCoordinates)) {
			pApplet.strokeWeight(DEFAULT_STROKE_WEIGHT_MOUSE_OVER);
			pApplet.setInfoShowingCircle(this);
		} else {
			pApplet.strokeWeight(DEFAULT_STROKE_WEIGHT);
		}
		pApplet.fill(255);

		pApplet.pushMatrix();
		pApplet.translate(windowCoordinates.x, windowCoordinates.y);
		pApplet.ellipse(0, 0, NodeCircle.RADIUS * 2, NodeCircle.RADIUS * 2);
		pApplet.popMatrix();
	}

	/**
	 * @param circleCenterWindowCoordinates
	 *            The coordinates of the center of this circle in window
	 *            coordinates.
	 * @return Returns whether the mouse pointer is currently in this circle.
	 */
	private boolean isMouseInside(Point circleCenterWindowCoordinates) {
		int diffX = pApplet.mouseX - circleCenterWindowCoordinates.x;
		int diffY = pApplet.mouseY - circleCenterWindowCoordinates.y;
		if (RADIUS * RADIUS > diffX * diffX + diffY * diffY)
			return true;
		return false;
	}

	/**
	 * @param children
	 *            An array of {@link NodeCircle} objects representing the
	 *            children of this circle.
	 */
	public void setChildren(NodeCircle[] children) {
		this.children = children;
	}

	/**
	 * @return An array of {@link NodeCircle} objects representing the children
	 *         of this circle.
	 */
	public NodeCircle[] getChildren() {
		return children;
	}

	/**
	 * @return An array of {@link NodeLine} objects representing the outgoing
	 *         lines from this circle.
	 */
	public NodeLine[] getOutgoingLines() {
		return outgoingLines;
	}

	/**
	 * @param outgoingLines
	 *            An array of {@link NodeLine} objects representing the outgoing
	 *            lines from this circle.
	 */
	public void setOutgoingLines(NodeLine[] outgoingLines) {
		this.outgoingLines = outgoingLines;
	}

	/**
	 * @return The {@link NodeShell} object this circle represents.
	 */
	public NodeShell getRepresentedShell() {
		return representedShell;
	}

	/**
	 * @param representedShell
	 *            The {@link NodeShell} object this circle represents.
	 */
	public void setRepresentedShell(NodeShell representedShell) {
		this.representedShell = representedShell;
	}

	/**
	 * @return The {@link Point} object which contains the coordinates of this
	 *         circle in the document model.
	 */
	public Point getDocumentCoordinate() {
		return documentCoordinate;
	}

	/**
	 * @param documentCoordinate
	 *            The {@link Point} object which contains the coordinates of
	 *            this circle in the document model.
	 */
	public void setDocumentCoordinate(Point documentCoordinate) {
		this.documentCoordinate = documentCoordinate;
	}

}
