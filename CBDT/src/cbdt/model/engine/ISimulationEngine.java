package cbdt.model.engine;

import cbdt.model.Result;
import cbdt.model.parameters.Parameters;

public interface ISimulationEngine {
	
	/**
	 * @param parameters
	 * @return
	 */
	public Result computeSimulation(Parameters parameters);

}
