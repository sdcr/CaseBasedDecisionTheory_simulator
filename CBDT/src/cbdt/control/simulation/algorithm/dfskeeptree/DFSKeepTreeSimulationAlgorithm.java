package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.List;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class DFSKeepTreeSimulationAlgorithm extends SimulationAlgorithm {

	private static final int MIN_NUMBER_OF_MONITOR_WORK_UNITS = 10;
	private DFSkeepTreeEngineConfig config;

	private int monitoredStage;
	
	@Override
	public void computeResult(Result result) throws InterruptedException{
		NodeContentKeepTreeFactory factory = new NodeContentKeepTreeFactory();
		NodeContentKeepTree rootContent = factory.getInitRootContent(parameters);
		NodeShell rootShell = new NodeShell(rootContent);

		NodeShellVisitor nodeShellVisitor = new NodeShellVisitor(parameters, config, commonConfig, result, factory, monitor);
		
		List<NodeShell> monitoredStageNodeShells = computeMonitoredStageNodeShells(
				result, rootShell, nodeShellVisitor);
		monitor.beginTask("Computation with DFS and tree datastructure", monitoredStageNodeShells.size());
		
		for(NodeShell nodeShell : monitoredStageNodeShells){
			nodeShellVisitor.visitRecursively(nodeShell, monitoredStage);
			monitor.worked(1);
		}
	
		if(config.isSaveTreeStructure())
			result.setRootNode(rootShell);
		monitor.done();
	}

	private List<NodeShell> computeMonitoredStageNodeShells(Result result,
			NodeShell rootShell, NodeShellVisitor nodeShellVisitor) {
		List<NodeShell> stageNodeShells = new ArrayList<NodeShell>();
		stageNodeShells.add(rootShell);
		monitoredStage = 0;
		
		while(stageNodeShells.size()<MIN_NUMBER_OF_MONITOR_WORK_UNITS 
				&& monitoredStage<commonConfig.getNumberOfRequestedExpectedUtilityValues()){
			List<NodeShell> childrenShells = new ArrayList<NodeShell>();
			StageResult childrensStageResult = result.getStageResults().get(monitoredStage);
			for(NodeShell parentShell : stageNodeShells){
				nodeShellVisitor.computeChildren(parentShell, childrensStageResult);
				childrenShells.addAll(parentShell.getChildren());
			}
			stageNodeShells = childrenShells;
			monitoredStage++;
		}
		return stageNodeShells;
	}

	public DFSkeepTreeEngineConfig getConfig() {
		return config;
	}

	public void setConfig(DFSkeepTreeEngineConfig config) {
		this.config = config;
	}

}
