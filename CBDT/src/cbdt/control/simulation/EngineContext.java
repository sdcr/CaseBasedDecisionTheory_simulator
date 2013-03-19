package cbdt.control.simulation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.control.simulation.algorithm.dfskeeptree.DFStreeSimulationAlgorithm;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;

/**
 * This class constitutes the context class of the strategy pattern.
 * It chooses the simulation algorithm based on the engine config choice model.
 * @author S-lenovo
 */
public class EngineContext {

	private Shell shell;

	private AbstractEngineConfiguration engineConfig;

	public EngineContext(Shell shell) {
		this.shell = shell;
	}

	public void setEngineConfig(AbstractEngineConfiguration engineConfig){
		this.engineConfig = engineConfig;
	}
	
	public void performSimulation(Parameters parameters) throws InterruptedException, InvocationTargetException{
		SimulationAlgorithm algorithm = determineAlgorithm();
		ComputationRunnableWithProgress runnable = new ComputationRunnableWithProgress(algorithm);

		ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell);
		progressDialog.run(true, true, runnable);

		
//		if (algorithm != null) {
//			algorithm.computeExpectedUtilities(parameters);
//		}
	}

	private SimulationAlgorithm determineAlgorithm() {
		SimulationAlgorithm algorithm = null;
		if(engineConfig instanceof DFSkeepTreeEngineConfig){
			algorithm = new DFStreeSimulationAlgorithm();
		}
		return algorithm;
	}
}

