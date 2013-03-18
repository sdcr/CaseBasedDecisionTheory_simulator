package cbdt.control.simulation;

import cbdt.model.parameters.Parameters;

public interface CBDTAlgorithm {

	public double[] computeExpectedUtilities(Parameters parameters);
}
