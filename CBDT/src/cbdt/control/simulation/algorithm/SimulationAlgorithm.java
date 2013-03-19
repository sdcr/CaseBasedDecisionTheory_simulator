package cbdt.control.simulation.algorithm;

import cbdt.model.parameters.Parameters;
import cbdt.model.result.Result;

/**
 * This class is the interface of the strategies in the strategy pattern. 
 * The different algorithms implementing this interface are the concrete strategies.
 * @author S-lenovo
 */
public interface SimulationAlgorithm {

	public Result computeExpectedUtilities(Parameters parameters);
}
