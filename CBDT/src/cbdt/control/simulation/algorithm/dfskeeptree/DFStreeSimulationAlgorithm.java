package cbdt.control.simulation.algorithm.dfskeeptree;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;

public class DFStreeSimulationAlgorithm extends SimulationAlgorithm {

	private DFSkeepTreeEngineConfig config;
	
	@Override
	public void computeResult(Result result){
		NodeContentFactory factory = new NodeContentFactory();
		NodeContentKeepTree rootContent = factory.getInitRootContent(parameters);
		NodeShellKeepTree rootShell = new NodeShellKeepTree(rootContent);

		rootShell.computeChildren(parameters, config, result, 0, factory);
	
		if(config.isSaveTreeStructure())
			result.setRootNode(rootShell);
	}

	public DFSkeepTreeEngineConfig getConfig() {
		return config;
	}

	public void setConfig(DFSkeepTreeEngineConfig config) {
		this.config = config;
	}

}
