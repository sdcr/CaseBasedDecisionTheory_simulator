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
		monitor.beginTask("Simulation computation", 5);
		algorithm.computeResult(result);
		monitor.done();
		
//		for(int i=0; i<5; i++){
//			monitor.worked(1);
////			if(monitor.isCanceled()){
////				throw new InterruptedException("The computation was cancelled.");
////			}
////			if(i==3){
////				throw new OutOfMemoryError();
////			}
//			Thread.sleep(500);
//		}
		
	}

}
