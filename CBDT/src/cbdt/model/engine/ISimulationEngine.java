package cbdt.model.engine;

import cbdt.model.Parameters;
import cbdt.model.Result;

public interface ISimulationEngine {
	
	/**
	 * @param parameters
	 * @return
	 */
	public Result computeSimulation(Parameters parameters);

}
