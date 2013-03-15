package cbdt.control.algorithm;

import cbdt.control.algorithm.dfskeeptree.DFStreeSimulationAlgorithm;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;

/**
 * This class constitutes the context class of the strategy pattern.
 * It chooses the simulation algorithm based on the engine config choice model.
 * @author S-lenovo
 */
public class EngineContext {

	private AbstractEngineConfiguration engineConfig;

	public void setEngineConfig(AbstractEngineConfiguration engineConfig){
		this.engineConfig = engineConfig;
	}
	
	public void performSimulation(Parameters parameters){
		CBDTAlgorithm algorithm = null;
		if(engineConfig instanceof DFSkeepTreeEngineConfig){
			algorithm = new DFStreeSimulationAlgorithm();
		}
		if (algorithm != null) {
			algorithm.computeExpectedUtilities(parameters);
		}
	}
}
