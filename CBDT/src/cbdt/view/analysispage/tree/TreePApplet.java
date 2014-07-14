package cbdt.view.analysispage.tree;

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import processing.core.PApplet;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.result.tree.NodeShell;
import cbdt.view.analysispage.tree.treemodel.NodeCircle;
import cbdt.view.analysispage.tree.treemodel.NodeFrame;
import cbdt.view.analysispage.tree.treemodel.factory.DocumentLayoutManager;
import cbdt.view.analysispage.tree.treemodel.factory.NodeCircleFactory;
import cbdt.view.analysispage.tree.treemodel.factory.NodeLineFactory;

/**
 * This class extends {@link PApplet} and is responsible for the animation of
 * the tree representing the results of a simulation computation.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class TreePApplet extends PApplet {

	private static final long serialVersionUID = 1L;

	/**
	 * Width of this {@link PApplet}. In this implementation not relevant.
	 */
	public static final int WIDTH = 800;

	/**
	 * Height of this {@link PApplet}. In this implementation not relevant.
	 */
	public static final int HEIGHT = 800;

	/**
	 * The root {@link NodeCircle} of the tree in the document model.
	 */
	private NodeCircle documentRootCircle;

	/**
	 * The {@link NodeFrame} representing the document model.
	 */
	private NodeFrame documentFrame;

	/**
	 * The designated node about which the {@link DataRectangleShower} should
	 * show information.
	 */
	private NodeCircle infoShowingCircle;

	/**
	 * The class responsible for showing a rectangle with information about a
	 * node in the tree.
	 */
	private DataRectangleShower dataRectangleShower;

	/**
	 * The {@link ZoomConverter} to convert between the document and the visual
	 * model.
	 */
	private ZoomConverter zoomConverter;

	/**
	 * The visitor to draw the tree.
	 */
	private NodeDrawVisitor drawVisitor;

	/**
	 * The constructor.
	 */
	public TreePApplet() {
		documentFrame = new NodeFrame();
		documentFrame.setWidth(500);
		documentFrame.setHeight(500);
		documentFrame.setMarginLeft(50);
		documentFrame.setMarginTop(50);

		NodeFrame visualWindow = new NodeFrame();
		visualWindow.setWidth(500);
		visualWindow.setHeight(500);
		visualWindow.setMarginLeft(0);
		visualWindow.setMarginTop(0);

		zoomConverter = new ZoomConverter(documentFrame, visualWindow);
		drawVisitor = new NodeDrawVisitor();
	}

	@Override
	public void setup() {
		size(WIDTH, HEIGHT, JAVA2D);
		background(255);

		addMouseWheelListener(new MouseWheelListener() {
			public void mouseWheelMoved(MouseWheelEvent mwe) {
				zoomConverter
						.updateZoom(mwe.getWheelRotation(), mwe.getPoint());
			}
		});
	}

	@Override
	public void draw() {
		background(255);
		if (documentRootCircle != null) {
			drawVisitor.drawSubtree(documentRootCircle);
		}
		if (infoShowingCircle != null)
			dataRectangleShower.drawDataRectangle(infoShowingCircle
					.getRepresentedShell());
		infoShowingCircle = null;
	}

	/**
	 * Sets the animation with the computed tree model and engine configuration.
	 * The document model is created from the given model by use of the
	 * {@link DocumentLayoutManager}, the {@link NodeCircleFactory} and the
	 * {@link NodeLineFactory}. In addition, the {@link DataRectangleShower} is
	 * instantiated for the given engine configuration.
	 * 
	 * @param rootShell
	 * @param config
	 */
	public void setTreeModel(NodeShell rootShell, DFSkeepTreeEngineConfig config) {
		DocumentLayoutManager layoutManager = new DocumentLayoutManager(
				documentFrame);
		NodeCircleFactory factory = new NodeCircleFactory(this, layoutManager);
		documentRootCircle = factory.createDocumentNodeCircles(rootShell);
		NodeLineFactory lineFactory = new NodeLineFactory(this);
		lineFactory.createDocumentNodeLinesRecursively(documentRootCircle);
		dataRectangleShower = new DataRectangleShower(this, config);
	}

	/**
	 * Set the {@link NodeCircle} object of which information is to be displayed
	 * by the {@link DataRectangleShower}.
	 * 
	 * @param nodeCircle
	 */
	public void setInfoShowingCircle(NodeCircle nodeCircle) {
		this.infoShowingCircle = nodeCircle;
	}

	/**
	 * @return the {@link ZoomConverter} converting between the document and the
	 *         visual window model.
	 */
	public ZoomConverter getZoomConverter() {
		return zoomConverter;
	}
}
