package cbdt.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;

public class TreeNode {

	private Set<TreeNode> childNodes;

	private TreeNode parentNode;

	private ActorActionOutcome lastActorActionOutcome;

	// is set to below 1 if the action is chosen from a set of possible actions
	// from the parent node.
	private double actionSelectionProbability;

	private double currentAstirationLevel;

	private Map<ActorAction, Double> cumulativePerformance = new HashMap<ActorAction, Double>();

	public TreeNode() {
		childNodes = new HashSet<TreeNode>();
	}

	public Set<TreeNode> getChildNodes() {
		return childNodes;
	}

	public void setChildNodes(Set<TreeNode> childNodes) {
		this.childNodes = childNodes;
	}

	public ActorActionOutcome getLastActorActionOutcome() {
		return lastActorActionOutcome;
	}

	public void setLastActorActionOutcome(
			ActorActionOutcome lastActorActionOutcome) {
		this.lastActorActionOutcome = lastActorActionOutcome;
	}

	public double getAstirationLevel() {
		return currentAstirationLevel;
	}

	public void setCurrentAstirationLevel(double currentAstirationLevel) {
		this.currentAstirationLevel = currentAstirationLevel;
	}

	public Map<ActorAction, Double> getCumulativePerformance() {
		return cumulativePerformance;
	}

	public void setCumulativePerformance(
			Map<ActorAction, Double> cumulativePerformance) {
		this.cumulativePerformance = cumulativePerformance;
	}

	public TreeNode getParentNode() {
		return parentNode;
	}

	public void setParentNode(TreeNode parentNode) {
		this.parentNode = parentNode;
	}

	public void addChildNode(TreeNode childNode) {
		childNodes.add(childNode);
	}

	public void removeChildNode(TreeNode childNode) {
		childNodes.remove(childNode);
	}

	@Override
	public String toString() {
		return "(TreeNode; Last Action Outcome: " + lastActorActionOutcome
				+ ", action selection probability: "
				+ actionSelectionProbability + ", aspiration level: "
				+ currentAstirationLevel + ", children: "
				+ childNodes.toString() + ")";
	}

	public double getActionSelectionProbability() {
		return actionSelectionProbability;
	}

	public void setActionSelectionProbability(double actionSelectionProbability) {
		this.actionSelectionProbability = actionSelectionProbability;
	}
}
