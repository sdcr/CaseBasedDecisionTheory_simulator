package cbdt.control.simulation.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

/**
 * This factory class produces {@link Result} objects initialized with the
 * correct but empty data structures.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class EmptyResultFactory {

	/**
	 * Produces an initial empty {@link Result} object.
	 * 
	 * @param engineConfig
	 * @param commonConfig
	 * @param parameters
	 * @return
	 */
	public Result getEmptyResult(AbstractEngineConfig engineConfig,
			CommonConfig commonConfig, Parameters parameters) {
		Result result = new Result();
		List<StageResult> stageResults = new ArrayList<StageResult>();
		result.setStageResults(stageResults);
		if (engineConfig instanceof DFSmatrixHighPrecEngineConfig)
			setBigDecimalStageResults(commonConfig, parameters, stageResults);
		else
			setNonBigDecimalStageResults(commonConfig, parameters, stageResults);
		return result;
	}

	/**
	 * Sets the list of {@link StageResult}s, with initial {@link BigDecimal}
	 * values.
	 * 
	 * @param commonConfig
	 * @param parameters
	 * @param stageResults
	 */
	private void setBigDecimalStageResults(CommonConfig commonConfig,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i <= commonConfig
				.getNumberOfRequestedExpectedUtilityValues(); i++) {
			BigDecimalStageResult stageResult = new BigDecimalStageResult();
			stageResult.setExpectedBigDecimalUtility(new BigDecimal(0));
			stageResult.setLowestBigDecimalAspirationLevel(new BigDecimal(
					Double.MAX_VALUE));
			initStageResult(commonConfig, parameters, i, stageResult);
			stageResults.add(stageResult);
		}
		((BigDecimalStageResult) stageResults.get(0))
				.setLowestBigDecimalAspirationLevel(new BigDecimal(parameters
						.getInitialAspirationLevel()));
	}

	/**
	 * Sets the list of {@link StageResult}s, with initial values.
	 * 
	 * @param commonConfig
	 * @param parameters
	 * @param stageResults
	 */
	private void setNonBigDecimalStageResults(CommonConfig commonConfig,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i <= commonConfig
				.getNumberOfRequestedExpectedUtilityValues(); i++) {
			StageResult stageResult = new StageResult();
			stageResult.setExpectedUtility(0);
			stageResult.setLowestAspirationLevel(Double.MAX_VALUE);
			initStageResult(commonConfig, parameters, i, stageResult);
			stageResults.add(stageResult);
		}
		stageResults.get(0).setLowestAspirationLevel(
				parameters.getInitialAspirationLevel());
	}

	/**
	 * Initializes a {@link StageResult} object.
	 * 
	 * @param commonConfig
	 * @param parameters
	 * @param stage
	 * @param stageResult
	 */
	private void initStageResult(CommonConfig commonConfig,
			Parameters parameters, int stage, StageResult stageResult) {
		stageResult.setStage(stage);
		if (commonConfig.isCalculateAbsoluteActionOccurances())
			stageResult
					.setAbsoluteActionOccurances(getEmptyAbsoluteActionOccuranceMap(parameters));
		if (commonConfig.isCalculateRelativeActionOccurances())
			stageResult
					.setRelativeActionOccurances(getEmptyRelativeActionOccuranceMap(parameters));
	}

	/**
	 * Produces and returns an empty initial {@link Map} for absolute action
	 * occurrences.
	 * 
	 * @param parameters
	 * @return
	 */
	private Map<ActorAction, BigDecimal> getEmptyAbsoluteActionOccuranceMap(
			Parameters parameters) {
		Map<ActorAction, BigDecimal> occurancesMap = new HashMap<ActorAction, BigDecimal>();
		for (ActorAction action : parameters.getActorActions()) {
			occurancesMap.put(action, new BigDecimal(0));
		}
		return occurancesMap;
	}

	/**
	 * Produces and returns an empty initial {@link Map} for relative action
	 * occurrences.
	 * 
	 * @param parameters
	 * @return
	 */
	private Map<ActorAction, Double> getEmptyRelativeActionOccuranceMap(
			Parameters parameters) {
		Map<ActorAction, Double> occurancesMap = new HashMap<ActorAction, Double>();
		for (ActorAction action : parameters.getActorActions()) {
			occurancesMap.put(action, 0.0);
		}
		return occurancesMap;
	}

}
