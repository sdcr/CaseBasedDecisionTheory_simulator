package cbdt.view.analysis.tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbdt.control.simulation.algorithm.dfskeeptree.NodeContentKeepTree;
import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;
import cbdt.model.parameters.ActorAction;

public class NodeCircleFactory {
	
	private DataRectangleShower dataRectangleShower;
	private ZoomConverter coordinateConverter;
	private TreePApplet pApplet;
	
	public NodeCircleFactory(TreePApplet pApplet, ZoomConverter coordinateConverter, DataRectangleShower dataRectangleShower) {
		this.pApplet = pApplet;
		this.coordinateConverter = coordinateConverter;
		this.dataRectangleShower = dataRectangleShower;
	}

	private NodeCircle getNodeCirclesRecursively(NodeShell nodeShell, int stage){
		NodeCircle nodeCircle = new NodeCircle(pApplet, dataRectangleShower, coordinateConverter);
		nodeCircle.setRepresentedShell(nodeShell);
		
		NodeCircle[] children = new NodeCircle[nodeShell.getChildren().size()];
		NodeLine[] linesToChildren = new NodeLine[nodeShell.getChildren().size()];
		
		Map<ActorAction, List<NodeCircle>> actionOccuranceMap = new HashMap<ActorAction, List<NodeCircle>>(); 
		
		int i=0;
		for(NodeShell shell : nodeShell.getChildren()){
			NodeContentKeepTree childContent = shell.getContent();
			if(childContent!=null && childContent.getLastAction()!=null)
//				children[i] = getNodeCircle(shell, stage+1);
				if(!actionOccuranceMap.containsKey(childContent.getLastAction())){
//					linesToChildren[i] = new NodeLine(this, nodeCircle, children[i]);
					List<NodeCircle> sameActionList = new ArrayList<NodeCircle>();
					sameActionList.add(children[i]);
					actionOccuranceMap.put(childContent.getLastAction(), sameActionList);
				}else{
					actionOccuranceMap.get(childContent.getLastAction()).add(children[i]);
				}
			i++;
		}
		
		nodeCircle.setChildren(children);
		nodeCircle.setLinesTochildren(linesToChildren);
		return nodeCircle;
	}
}
