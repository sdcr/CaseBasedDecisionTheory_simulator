package cbdt.control.simulation.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfig;
import cbdt.model.parameters.engineconfig.CommonConfig;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class EmptyResultFactory {

	public Result getEmptyResult(AbstractEngineConfig config, 
			CommonConfig commonConfig, Parameters parameters) {
		Result result = new Result();
		List<StageResult> stageResults = new ArrayList<StageResult>();
		result.setStageResults(stageResults);
		if(config instanceof DFSmatrixHighPrecEngineConfig)
			setBigDecimalStageResults(commonConfig, parameters, stageResults);
		else
			setNonBigDecimalStageResults(commonConfig, parameters, stageResults);
		return result;
	}

	private void setBigDecimalStageResults(CommonConfig commonConfig,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i <= commonConfig.getNumberOfRequestedExpectedUtilityValues(); i++) {
			BigDecimalStageResult stageResult = new BigDecimalStageResult();
			stageResult.setExpectedBigDecimalUtility(new BigDecimal(0));
			stageResult.setLowestBigDecimalAspirationLevel(new BigDecimal(Double.MAX_VALUE));
			initStageResult(commonConfig, parameters, i, stageResult);
			stageResults.add(stageResult);
		}
		((BigDecimalStageResult)stageResults.get(0)).setLowestBigDecimalAspirationLevel(
				new BigDecimal(parameters.getInitialAspirationLevel()));
	}

	private void setNonBigDecimalStageResults(CommonConfig commonConfig,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i <= commonConfig.getNumberOfRequestedExpectedUtilityValues(); i++) {
			StageResult stageResult = new StageResult();
			stageResult.setExpectedUtility(0);
			stageResult.setLowestAspirationLevel(Double.MAX_VALUE);
			initStageResult(commonConfig, parameters, i, stageResult);
			stageResults.add(stageResult);
		}
		stageResults.get(0).setLowestAspirationLevel(parameters.getInitialAspirationLevel());
	}

	private void initStageResult(CommonConfig commonConfig,
			Parameters parameters, int stage, StageResult stageResult) {
		stageResult.setStage(stage);
		if(commonConfig.isCalculateAbsoluteActionOccurances())// || commonConfig.isCalculateAbsoluteActionOccurances())
			stageResult.setAbsoluteActionOccurances(getEmptyAbsoluteActionOccuranceMap(parameters));
		if(commonConfig.isCalculateRelativeActionOccurances())
			stageResult.setRelativeActionOccurances(getEmptyRelativeActionOccuranceMap(parameters));
	}
	
	private Map<ActorAction, BigDecimal> getEmptyAbsoluteActionOccuranceMap(Parameters parameters) {
		Map<ActorAction, BigDecimal> occurancesMap = new HashMap<ActorAction, BigDecimal>();
		for(ActorAction action : parameters.getActorActions()){
			occurancesMap.put(action, new BigDecimal(0));
		}
		return occurancesMap;
	}
	
	private Map<ActorAction, Double> getEmptyRelativeActionOccuranceMap(Parameters parameters) {
		Map<ActorAction, Double> occurancesMap = new HashMap<ActorAction, Double>();
		for(ActorAction action : parameters.getActorActions()){
			occurancesMap.put(action, 0.0);
		}
		return occurancesMap;
	}
	
	
}
