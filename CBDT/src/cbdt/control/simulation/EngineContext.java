package cbdt.control.simulation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.control.simulation.algorithm.dfskeeptree.DFSKeepTreeSimulationAlgorithm;
import cbdt.control.simulation.algorithm.dfsmatrix.DFSMatrixSimulationAlgorithm;
import cbdt.control.simulation.algorithm.dfsmatrix.highprecision.DFSHighPrecMatrixSimulationAlgorithm;
import cbdt.control.simulation.process.ComputationRunnableWithProgress;
import cbdt.control.simulation.process.EmptyResultFactory;
import cbdt.control.validators.InvalidActorActionException;
import cbdt.control.validators.ParameterValidator;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.config.engine.DFSmatrixEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.Result;

/**
 * This class constitutes the context class of the strategy pattern.
 * It chooses the simulation algorithm based on the engine config choice model.
 * @author S-lenovo
 */
public class EngineContext {

	private Shell shell;

	private AbstractEngineConfig engineConfig;
	
	private CommonConfig commonConfig;
	
	public EngineContext(Shell shell) {
		this.shell = shell;
	}

	public void setEngineConfig(AbstractEngineConfig engineConfig, CommonConfig commonConfig){
		this.engineConfig = engineConfig;
		this.commonConfig = commonConfig;
	}
	
	public Result performSimulation(Parameters parameters) throws InterruptedException, InvocationTargetException, InvalidActorActionException{
		ParameterValidator parameterValidator = new ParameterValidator();
		parameterValidator.checkValidity(parameters);

		EmptyResultFactory resultFactory = new EmptyResultFactory();
		Result result = resultFactory.getEmptyResult(engineConfig, commonConfig, parameters);
		
		SimulationAlgorithm algorithm = determineAlgorithm();
		algorithm.setParameters(parameters);
		ComputationRunnableWithProgress runnable = new ComputationRunnableWithProgress(result, algorithm);
		ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell);
		progressDialog.run(true, true, runnable);

		return result;
	}

	private SimulationAlgorithm determineAlgorithm() {
		SimulationAlgorithm algorithm = null;
		if(engineConfig instanceof DFSkeepTreeEngineConfig){
			DFSKeepTreeSimulationAlgorithm keepTreeAlgorithm = new DFSKeepTreeSimulationAlgorithm();
			keepTreeAlgorithm.setConfig((DFSkeepTreeEngineConfig) engineConfig);
			algorithm = keepTreeAlgorithm;
		}
		if(engineConfig instanceof DFSmatrixEngineConfig){
			DFSMatrixSimulationAlgorithm matrixAlgorithm = new DFSMatrixSimulationAlgorithm();
			matrixAlgorithm.setMatrixConfig((DFSmatrixEngineConfig) engineConfig);
			algorithm = matrixAlgorithm;
		}
		if(engineConfig instanceof DFSmatrixHighPrecEngineConfig){
			DFSHighPrecMatrixSimulationAlgorithm matrixHighPrecAlgorithm = new DFSHighPrecMatrixSimulationAlgorithm();
			matrixHighPrecAlgorithm.setMatrixConfig((DFSmatrixHighPrecEngineConfig) engineConfig);
			algorithm = matrixHighPrecAlgorithm;
		}
		if(algorithm != null)
			algorithm.setCommonConfig(commonConfig);
		return algorithm;
	}
}

