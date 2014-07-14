package cbdt.control.simulation.process;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import cbdt.control.simulation.algorithm.AbstractSimulationAlgorithm;
import cbdt.model.result.Result;

/**
 * This runnable class allows to compute the simulation in a separate thread.
 * When it is started, it sets the algorithm with an {@link IProgressMonitor},
 * and initiates the execution of an algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ComputationRunnableWithProgress implements IRunnableWithProgress {

	private AbstractSimulationAlgorithm algorithm;

	private Result result;

	/**
	 * Constructor.
	 * 
	 * @param emptyResult The initial empty result before executing the algorithm.
	 * @param algorithm The algorithm to be used.
	 */
	public ComputationRunnableWithProgress(Result emptyResult,
			AbstractSimulationAlgorithm algorithm) {
		this.result = emptyResult;
		this.algorithm = algorithm;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		algorithm.setMonitor(monitor);
		algorithm.computeResult(result);
	}

}
