package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cbdt.control.simulation.algorithm.AbstractSimulationAlgorithm;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.parameters.ActorAction;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

/**
 * This algorithm class is able to compute the simulation {@link Result} without
 * instantiating an actual tree. The goal of this algorithm is increased
 * performance in case the actual tree is not needed.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSMatrixSimulationAlgorithm extends AbstractSimulationAlgorithm {

	protected AbstractEngineConfig engineConfig;

	private SimulationState simState;

	@Override
	public void computeResult(Result initResult) throws InterruptedException {
		IntDoubleInitFactory factory = new IntDoubleInitFactory(parameters,
				commonConfig);
		simState = factory.getInitSimulationState();

		VirtualNodeContentVisitor visitor = new VirtualNodeContentVisitor(
				parameters, commonConfig, monitor, simState);

		computeWithVisitor(initResult, visitor);

		for (int stage = 0; stage < initResult.getStageResults().size(); stage++) {
			StageResult stageResult = initResult.getStageResults().get(stage);
			stageResult.setStage(stage);
			stageResult.setExpectedUtility(simState.expectedUtilities[stage]);
			stageResult
					.setLowestAspirationLevel(simState.lowestAspirationLevels[stage]);
			Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = new HashMap<ActorAction, BigDecimal>();
			for (int actionIndex = 0; actionIndex < parameters
					.getActorActions().size(); actionIndex++) {
				absoluteActionOccurancesMap.put(parameters.getActorActions()
						.get(actionIndex),
						simState.absoluteActionOccurances[stage][actionIndex]);
			}
			stageResult
					.setAbsoluteActionOccurances(absoluteActionOccurancesMap);
			Map<ActorAction, Double> relativeActionOccurancesMap = new HashMap<ActorAction, Double>();
			for (int actionIndex = 0; actionIndex < parameters
					.getActorActions().size(); actionIndex++) {
				relativeActionOccurancesMap.put(parameters.getActorActions()
						.get(actionIndex),
						simState.relativeActionOccurances[stage][actionIndex]);
			}
			stageResult
					.setRelativeActionOccurances(relativeActionOccurancesMap);
		}
	}

	/**
	 * Starts the computation of the {@link SimulationState} recursively with
	 * the {@link VirtualNodeContentVisitor}.
	 * 
	 * @param initResult
	 * @param visitor
	 * @throws InterruptedException
	 */
	protected void computeWithVisitor(Result initResult,
			VirtualNodeContentVisitor visitor) throws InterruptedException {
		boolean computeWithProgressShowing = true;

		int stageNumber = 0;
		int numberOfLeafs = 1;
		while (stageNumber < 15 && numberOfLeafs < 15) {
			stageNumber++;
			if (stageNumber == commonConfig
					.getNumberOfRequestedExpectedUtilityValues() + 1) {
				computeWithProgressShowing = false;
				break;
			}
			numberOfLeafs = visitor.calculteNumberOfLeafs(stageNumber);
		}

		if (computeWithProgressShowing) {
			monitor.beginTask("Computation with DFS and matrix datastructure",
					numberOfLeafs);
			visitor.visitRecursively(0, 0, stageNumber);
			monitor.done();
		} else {
			visitor.visitRecursively(0, 0, 0);
		}

	}

	/**
	 * Sets the {@link AbstractEngineConfig} for this algorithm.
	 * 
	 * @param config
	 */
	public void setMatrixConfig(AbstractEngineConfig config) {
		this.engineConfig = config;
	}
}
