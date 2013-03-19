package cbdt.control.simulation;

import cbdt.model.parameters.Parameters;

/**
 * This class is the interface of the strategies in the strategy pattern. 
 * The different algorithms implementing this interface are the concrete strategies.
 * @author S-lenovo
 */
public interface SimulationAlgorithm {

	public double[] computeExpectedUtilities(Parameters parameters);
}
