package cbdt.control.simulation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;

public class ComputationRunnableWithProgress implements IRunnableWithProgress {

	@SuppressWarnings("unused")
	private SimulationAlgorithm algorithm;

	public ComputationRunnableWithProgress(SimulationAlgorithm algorithm) {
		this.algorithm = algorithm;
	}

	@Override
	public void run(IProgressMonitor monitor) throws InvocationTargetException,
			InterruptedException {
		monitor.beginTask("Simulation computation", 5);
		for(int i=0; i<5; i++){
			monitor.worked(1);
			if(monitor.isCanceled()){
				throw new InterruptedException("The computation was cancelled.");
			}
			if(i==3){
				throw new OutOfMemoryError();
			}
			Thread.sleep(500);
		}
	}

}
