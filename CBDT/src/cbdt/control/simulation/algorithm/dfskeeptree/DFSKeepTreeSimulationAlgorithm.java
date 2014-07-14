package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;

import cbdt.control.simulation.algorithm.AbstractSimulationAlgorithm;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;
import cbdt.model.result.tree.NodeContent;
import cbdt.model.result.tree.NodeShell;

/**
 * This algorithm is able to compute the simulation {@link Result} in a way
 * which allows to keep the decision tree in the {@link Result}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSKeepTreeSimulationAlgorithm extends AbstractSimulationAlgorithm {

	private static final int MIN_NUMBER_OF_MONITOR_WORK_UNITS = 10;
	private DFSkeepTreeEngineConfig engineConfig;

	private int monitoredStage;

	@Override
	public void computeResult(Result result) throws InterruptedException {
		NodeContentKeepTreeFactory factory = new NodeContentKeepTreeFactory();
		NodeContent rootContent = factory.getInitRootContent(parameters);
		NodeShell rootShell = new NodeShell(rootContent);

		NodeShellVisitor nodeShellVisitor = new NodeShellVisitor(parameters,
				engineConfig, commonConfig, result, factory, monitor);

		List<NodeShell> monitoredStageNodeShells = computeMonitoredStageNodeShells(
				result, rootShell, nodeShellVisitor);
		monitor.beginTask("Computation with DFS and tree datastructure",
				monitoredStageNodeShells.size());

		for (NodeShell nodeShell : monitoredStageNodeShells) {
			nodeShellVisitor.visitRecursively(nodeShell, monitoredStage);
			monitor.worked(1);
		}

		if (engineConfig.isSaveTreeStructure())
			result.setRootNode(rootShell);
		monitor.done();
	}

	/**
	 * Computes the subtree of the decision tree from the root down to the stage
	 * where the number of nodes in a stage reaches a minimum threshold, but not
	 * more stages than specified for computation by the {@link CommonConfig}.
	 * 
	 * @param result
	 * @param rootShell
	 * @param nodeShellVisitor
	 * @return The {@link List} of {@link NodeShell} objects which are the
	 *         leaves of the computed subtree.
	 */
	private List<NodeShell> computeMonitoredStageNodeShells(Result result,
			NodeShell rootShell, NodeShellVisitor nodeShellVisitor) {
		List<NodeShell> parentNodeShells = new ArrayList<NodeShell>();
		parentNodeShells.add(rootShell);
		monitoredStage = 1;

		while (parentNodeShells.size() < MIN_NUMBER_OF_MONITOR_WORK_UNITS
				&& monitoredStage <= commonConfig
						.getNumberOfRequestedExpectedUtilityValues()) {
			List<NodeShell> childrenShells = new ArrayList<NodeShell>();
			StageResult childrensStageResult = result.getStageResults().get(
					monitoredStage);
			for (NodeShell parentShell : parentNodeShells) {
				nodeShellVisitor.computeChildren(parentShell,
						childrensStageResult, monitoredStage);
				childrenShells.addAll(parentShell.getChildren());
			}
			parentNodeShells = childrenShells;
			monitoredStage++;
		}
		return parentNodeShells;
	}

	/**
	 * Sets the {@link DFSkeepTreeEngineConfig}.
	 * 
	 * @param engineConfig
	 */
	public void setConfig(DFSkeepTreeEngineConfig engineConfig) {
		this.engineConfig = engineConfig;
	}

}
