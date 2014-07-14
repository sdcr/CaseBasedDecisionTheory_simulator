package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cbdt.control.simulation.algorithm.dfsmatrix.DFSMatrixSimulationAlgorithm;
import cbdt.model.parameters.ActorAction;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;

/**
 * This algorithm class is able to compute the simulation {@link Result} without
 * instantiating an actual tree. It furthermore uses BigDecimal objects to allow
 * an arbitrarily high precision.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSHighPrecMatrixSimulationAlgorithm extends
		DFSMatrixSimulationAlgorithm {

	private BigDecimalSimulationState simState;

	@Override
	public void computeResult(Result initResult) throws InterruptedException {
		BigDecimalInitFactory factory = new BigDecimalInitFactory(parameters,
				commonConfig);
		simState = factory.getInitBigDecimalSimulationState();

		BigDecimalVirtualNodeContentVisitor visitor = new BigDecimalVirtualNodeContentVisitor(
				parameters, commonConfig, monitor, simState);

		computeWithVisitor(initResult, visitor);

		for (int stage = 0; stage < initResult.getStageResults().size(); stage++) {
			BigDecimalStageResult stageResult = (BigDecimalStageResult) initResult
					.getStageResults().get(stage);
			stageResult.setStage(stage);
			stageResult
					.setExpectedBigDecimalUtility(simState.expectedUtilities[stage]);
			stageResult
					.setLowestBigDecimalAspirationLevel(simState.lowestAspirationLevels[stage]);
			Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = makeToBigDecimalMap(
					stage, simState.absoluteActionOccurances);
			stageResult
					.setAbsoluteActionOccurances(absoluteActionOccurancesMap);
			Map<ActorAction, BigDecimal> relativeActionOccurancesMap = makeToBigDecimalMap(
					stage, simState.relativeActionOccurances);
			stageResult
					.setRelativeBigDecimalActionOccurances(relativeActionOccurancesMap);
		}
	}

	private Map<ActorAction, BigDecimal> makeToBigDecimalMap(int stage,
			BigDecimal[][] actionOccurances) {
		Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = new HashMap<ActorAction, BigDecimal>();
		for (int actionIndex = 0; actionIndex < parameters.getActorActions()
				.size(); actionIndex++) {
			absoluteActionOccurancesMap.put(
					parameters.getActorActions().get(actionIndex),
					actionOccurances[stage][actionIndex]);
		}
		return absoluteActionOccurancesMap;
	}

}
