package cbdt.control.simulation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.control.simulation.algorithm.dfskeeptree.DFStreeSimulationAlgorithm;
import cbdt.control.simulation.process.ComputationRunnableWithProgress;
import cbdt.control.simulation.process.EmptyResultFactory;
import cbdt.control.simulation.process.PostProcessor;
import cbdt.control.validators.InvalidActorActionException;
import cbdt.control.validators.ParameterValidator;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;

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
	
	public Result performSimulation(Parameters parameters) throws InterruptedException, InvocationTargetException, InvalidActorActionException{
		ParameterValidator parameterValidator = new ParameterValidator();
		parameterValidator.checkValidity(parameters);

		EmptyResultFactory resultFactory = new EmptyResultFactory();
		Result result = resultFactory.getEmptyResult(engineConfig, parameters);
		
		SimulationAlgorithm algorithm = determineAlgorithm();
		algorithm.setParameters(parameters);
		ComputationRunnableWithProgress runnable = new ComputationRunnableWithProgress(result, algorithm);
		ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell);
		progressDialog.run(true, true, runnable);

		PostProcessor postProcessor = new PostProcessor();
		postProcessor.postProcess(result, engineConfig);
		
		return result;
	}

	private SimulationAlgorithm determineAlgorithm() {
		SimulationAlgorithm algorithm = null;
		if(engineConfig instanceof DFSkeepTreeEngineConfig){
			DFStreeSimulationAlgorithm keepTreeAlgorithm = new DFStreeSimulationAlgorithm();
			keepTreeAlgorithm.setConfig((DFSkeepTreeEngineConfig) engineConfig);
			algorithm = keepTreeAlgorithm;
		}
		return algorithm;
	}
}

