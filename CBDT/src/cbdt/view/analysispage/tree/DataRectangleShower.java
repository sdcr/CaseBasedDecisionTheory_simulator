package cbdt.view.analysispage.tree;

import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.result.tree.NodeContent;
import cbdt.model.result.tree.NodeShell;

/**
 * This class is able to draw a rectangle containing information about a node.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class DataRectangleShower {

	private static final float DEFAULT_STROKE_WEIGHT = 1;
	private TreePApplet pApplet;
	private DFSkeepTreeEngineConfig config;

	/**
	 * Constructor.
	 * 
	 * @param pApplet
	 * @param config
	 */
	public DataRectangleShower(TreePApplet pApplet,
			DFSkeepTreeEngineConfig config) {
		this.pApplet = pApplet;
		this.config = config;
	}

	/**
	 * If the given {@link NodeShell} has content to display, it will be
	 * displayed as a rectangle.
	 * 
	 * @param representedShell
	 */
	public void drawDataRectangle(NodeShell representedShell) {
		NodeContent content = representedShell.getContent();
		if (content != null) {
			drawDataRectangle(content);
		}
	}

	/**
	 * Draw a rectangle with information of the given {@link NodeContent}
	 * object. To determine which information to display, the
	 * {@link DFSkeepTreeEngineConfig} of this object is used.
	 * 
	 * @param contentToShow
	 */
	private void drawDataRectangle(NodeContent contentToShow) {
		pApplet.fill(255);
		boolean showLastAction = (contentToShow.getLastActionOutcome() != null)
				&& config.isSaveActionNames();

		int rectangleHeight = 20;
		if (showLastAction)
			rectangleHeight += 35;
		if (config.isSaveAspirationLevels())
			rectangleHeight += 15;

		pApplet.strokeWeight(DEFAULT_STROKE_WEIGHT);
		pApplet.rect(pApplet.mouseX + 10, pApplet.mouseY + 10, 220,
				rectangleHeight);
		pApplet.fill(0);

		int offsetY = 0;
		if (showLastAction) {
			String lastActionName = contentToShow.getLastActionOutcome()
					.getAction().getActionName();
			pApplet.text("Last action: " + lastActionName, pApplet.mouseX + 30,
					pApplet.mouseY + 25);
			double lastUtility = contentToShow.getLastActionOutcome()
					.getUtility();
			pApplet.text("Utility last action: " + lastUtility,
					pApplet.mouseX + 30, pApplet.mouseY + 40);
			offsetY += 30;
		}

		if (config.isSaveAspirationLevels()) {
			double aspLevel = contentToShow.getAspirationLevel();
			pApplet.text("Asp. level: " + aspLevel, pApplet.mouseX + 30,
					pApplet.mouseY + 25 + offsetY);
			offsetY += 15;
		}

		double probProd = contentToShow.getProbabilityProduct();
		pApplet.text("Prob.: " + probProd, pApplet.mouseX + 30, pApplet.mouseY
				+ 25 + offsetY);
	}

}
