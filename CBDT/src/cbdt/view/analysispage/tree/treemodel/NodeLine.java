package cbdt.view.analysispage.tree.treemodel;

import java.awt.Point;

import cbdt.view.analysispage.tree.TreePApplet;
import cbdt.view.analysispage.tree.ZoomConverter;

/**
 * This class represents a line in the document model. It has references to the
 * 'to'- and 'from'- {@link NodeCircle} objects, and is able to draw itself in
 * visual window coordinates.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class NodeLine {

	/**
	 * The stroke weight of this line.
	 */
	private static final float DEFAULT_STROKE_WEIGHT = 1;

	NodeCircle from;
	NodeCircle to;
	private TreePApplet pApplet;

	/**
	 * Constructor.
	 * 
	 * @param pApplet
	 * @param from
	 * @param to
	 */
	public NodeLine(TreePApplet pApplet, NodeCircle from, NodeCircle to) {
		this.pApplet = pApplet;
		this.from = from;
		this.to = to;
	}

	/**
	 * Makes this {@link NodeLine} object draw itself in visual window
	 * coordinates. For that, it uses the {@link ZoomConverter} referenced by
	 * the {@link TreePApplet}.<br>
	 * <br>
	 * The start and end coordinates of the line are the lower end of the
	 * 'from'-circle and the upper end of the 'to'-circle, respectively.
	 */
	public void drawInVisualWindow() {
		Point fromWindowCoordinates = pApplet.getZoomConverter()
				.convertToWindowCoordinates(from.getDocumentCoordinate());
		Point toWindowCoordinates = pApplet.getZoomConverter()
				.convertToWindowCoordinates(to.getDocumentCoordinate());

		fromWindowCoordinates.y += NodeCircle.RADIUS;
		toWindowCoordinates.y -= NodeCircle.RADIUS;

		pApplet.strokeWeight(DEFAULT_STROKE_WEIGHT);
		pApplet.line(fromWindowCoordinates.x, fromWindowCoordinates.y,
				toWindowCoordinates.x, toWindowCoordinates.y);
	}
}
