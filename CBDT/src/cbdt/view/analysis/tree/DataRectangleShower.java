package cbdt.view.analysis.tree;

import cbdt.control.simulation.algorithm.dfskeeptree.NodeContentKeepTree;

public class DataRectangleShower {

	private TreePApplet pApplet;
	
	public DataRectangleShower(TreePApplet pApplet) {
		this.pApplet = pApplet;
	}
	
	public void showDataRectangle(NodeContentKeepTree contentToShow){
		pApplet.fill(255);
		pApplet.rect(pApplet.mouseX+10, pApplet.mouseY+10, 150, 50);
		
		pApplet.fill(0);
		int offsetY = 0;			

		if(contentToShow.getLastAction() != null){
			String lastActionName = contentToShow.getLastAction().getActionName();
			pApplet.text("Last action: "+lastActionName, pApplet.mouseX+30, pApplet.mouseY+25);
			offsetY = 15;
		}

		double aspLevel = contentToShow.getAspirationLevel();
		pApplet.text("Asp. level: "+aspLevel, pApplet.mouseX+30, pApplet.mouseY+25 + offsetY);

		double probProd = contentToShow.getProbabilityProduct();
		pApplet.text("Prob.: "+probProd, pApplet.mouseX+30, pApplet.mouseY+40 + offsetY);

	}
}
