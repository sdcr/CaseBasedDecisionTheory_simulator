package cbdt.control.simulation;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.simulation.algorithm.AbstractSimulationAlgorithm;
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
 * This class constitutes the context class of the strategy pattern. It chooses
 * the simulation algorithm based on the chosen {@link AbstractEngineConfig}.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class EngineContext {

	private Shell shell;

	private AbstractEngineConfig chosenEngineConfig;

	private CommonConfig commonConfig;

	/**
	 * The constructor.
	 * 
	 * @param shell
	 */
	public EngineContext(Shell shell) {
		this.shell = shell;
	}

	/**
	 * Sets the chosen {@link AbstractEngineConfig} and the {@link CommonConfig}
	 * .
	 * 
	 * @param chosenEngineConfig
	 * @param commonConfig
	 */
	public void setEngineConfig(AbstractEngineConfig chosenEngineConfig,
			CommonConfig commonConfig) {
		this.chosenEngineConfig = chosenEngineConfig;
		this.commonConfig = commonConfig;
	}

	/**
	 * Determines the correct algorithm, based on the set
	 * {@link AbstractEngineConfig} object and starts the simulation computation
	 * in a separate thread.
	 * 
	 * @param parameters
	 * @return
	 * @throws InterruptedException
	 * @throws InvocationTargetException
	 * @throws InvalidActorActionException
	 */
	public Result performSimulation(Parameters parameters)
			throws InterruptedException, InvocationTargetException,
			InvalidActorActionException {
		ParameterValidator parameterValidator = new ParameterValidator();
		parameterValidator.checkValidity(parameters);

		EmptyResultFactory resultFactory = new EmptyResultFactory();
		Result result = resultFactory.getEmptyResult(chosenEngineConfig,
				commonConfig, parameters);

		AbstractSimulationAlgorithm algorithm = determineAlgorithm();
		algorithm.setParameters(parameters);
		ComputationRunnableWithProgress runnable = new ComputationRunnableWithProgress(
				result, algorithm);
		ProgressMonitorDialog progressDialog = new ProgressMonitorDialog(shell);
		progressDialog.run(true, true, runnable);

		return result;
	}

	/**
	 * Determines the {@link AbstractSimulationAlgorithm} according to the set
	 * {@link AbstractEngineConfig}.
	 * 
	 * @return
	 */
	private AbstractSimulationAlgorithm determineAlgorithm() {
		AbstractSimulationAlgorithm algorithm = null;
		if (chosenEngineConfig instanceof DFSkeepTreeEngineConfig) {
			DFSKeepTreeSimulationAlgorithm keepTreeAlgorithm = new DFSKeepTreeSimulationAlgorithm();
			keepTreeAlgorithm
					.setConfig((DFSkeepTreeEngineConfig) chosenEngineConfig);
			algorithm = keepTreeAlgorithm;
		}
		if (chosenEngineConfig instanceof DFSmatrixEngineConfig) {
			DFSMatrixSimulationAlgorithm matrixAlgorithm = new DFSMatrixSimulationAlgorithm();
			matrixAlgorithm
					.setMatrixConfig((DFSmatrixEngineConfig) chosenEngineConfig);
			algorithm = matrixAlgorithm;
		}
		if (chosenEngineConfig instanceof DFSmatrixHighPrecEngineConfig) {
			DFSHighPrecMatrixSimulationAlgorithm matrixHighPrecAlgorithm = new DFSHighPrecMatrixSimulationAlgorithm();
			matrixHighPrecAlgorithm
					.setMatrixConfig((DFSmatrixHighPrecEngineConfig) chosenEngineConfig);
			algorithm = matrixHighPrecAlgorithm;
		}
		if (algorithm != null)
			algorithm.setCommonConfig(commonConfig);
		return algorithm;
	}
}
