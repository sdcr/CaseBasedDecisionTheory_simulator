package cbdt.model;

import java.util.ArrayList;



public class ResultAnalyser {

	public ArrayList<Double> calculateCbu(Result result){
		TreeNode root = ((Result)result).getRootNode();
		ArrayList<Double> cbuList = new ArrayList<Double>();
		calculateCbuRecursively(cbuList, root, 0, 1.0);
		return cbuList;
	}
	
	private void calculateCbuRecursively(ArrayList<Double> cbuList, TreeNode sourceNode, int treeDepth, double previousProbability){
		Double probabilityToSourceNode = 1.0;
		if(sourceNode.getLastActorActionOutcome()!=null){
			while(cbuList.size()<treeDepth+1){
				cbuList.add(0.0);
			}
			
			probabilityToSourceNode = previousProbability * sourceNode.getActionSelectionProbability() 
					* sourceNode.getLastActorActionOutcome().getProbability();
			Double cbuIncrement = probabilityToSourceNode
					* sourceNode.getLastActorActionOutcome().getUtility();
			cbuList.set(treeDepth, cbuList.get(treeDepth) + cbuIncrement);
		}
		for(TreeNode child : sourceNode.getChildNodes())
			calculateCbuRecursively(cbuList, child, treeDepth + 1, probabilityToSourceNode);
	}
}
