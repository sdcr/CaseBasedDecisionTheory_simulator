package cbdt.control.simulation.process;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.model.result.Result;

public class ComputationRunnableWithProgress implements IRunnableWithProgress {

	private SimulationAlgorithm algorithm;

	private Result result;
	
	public ComputationRunnableWithProgress(Result result, SimulationAlgorithm algorithm) {
		this.result = result;
		this.algorithm = algorithm;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		algorithm.setMonitor(monitor);
		algorithm.computeResult(result);
	}

}
