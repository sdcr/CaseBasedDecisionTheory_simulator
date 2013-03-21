package cbdt.control.simulation.algorithm;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.model.parameters.Parameters;
import cbdt.model.result.Result;

/**
 * This class is the interface of the strategies in the strategy pattern. 
 * The different algorithms implementing this interface are the concrete strategies.
 * @author S-lenovo
 */
public abstract class SimulationAlgorithm {

	protected IProgressMonitor monitor;
	
	protected Parameters parameters;
	
	public abstract void computeResult(Result initResult);

	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}
}
