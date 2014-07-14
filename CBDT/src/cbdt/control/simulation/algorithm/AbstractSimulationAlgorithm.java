package cbdt.control.simulation.algorithm;

import org.eclipse.core.runtime.IProgressMonitor;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.Result;

/**
 * This class is the interface of the strategies in the strategy pattern. The
 * different algorithms implementing this interface are the concrete strategies.
 * 
 * @author Stephan da Costa Ribeiro
 */
public abstract class AbstractSimulationAlgorithm {

	protected IProgressMonitor monitor;

	protected Parameters parameters;

	protected CommonConfig commonConfig;

	/**
	 * Starts the computation of the simulation result.
	 * 
	 * @param initResult
	 * @throws InterruptedException
	 */
	public abstract void computeResult(Result initResult)
			throws InterruptedException;

	/**
	 * Sets an {@link IProgressMonitor}, which can be used by implementing
	 * classes to show the computation progress.
	 * 
	 * @param monitor
	 */
	public void setMonitor(IProgressMonitor monitor) {
		this.monitor = monitor;
	}

	/**
	 * Sets the simulation {@link Parameters}.
	 * 
	 * @param parameters
	 */
	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	/**
	 * Sets the {@link CommonConfig} of the simulation.
	 * 
	 * @param commonConfig
	 */
	public void setCommonConfig(CommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}
}
