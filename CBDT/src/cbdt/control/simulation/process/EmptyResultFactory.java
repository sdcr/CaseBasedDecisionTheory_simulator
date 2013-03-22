package cbdt.control.simulation.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.CommonEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class EmptyResultFactory {

	public Result getEmptyResult(AbstractEngineConfiguration config, 
			CommonEngineConfiguration commonConfig, Parameters parameters) {
		Result result = new Result();
		List<StageResult> stageResults = new ArrayList<StageResult>();
		result.setStageResults(stageResults);
		
		if(config instanceof DFSmatrixHighPrecEngineConfig)
			setBigDecimalStageResults(commonConfig, parameters, stageResults);
		else
			setNonBigDecimalStageResults(commonConfig, parameters, stageResults);
		return result;
	}

	private void setBigDecimalStageResults(CommonEngineConfiguration commonConfig,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i < commonConfig.getNumberOfRequestedExpectedUtilityValues(); i++) {
			BigDecimalStageResult stageResult = new BigDecimalStageResult();
			stageResult.setStage(i);
			stageResult.setExpectedBigDecimalUtility(new BigDecimal(0));
			if(commonConfig.isCalculateRelativeActionOccurances() || commonConfig.isCalculateAbsoluteActionOccurances())
				stageResult.setAbsoluteActionOccurances(getEmptyAbsoluteActionOccuranceMap(parameters));
			stageResults.add(stageResult);
		}
	}

	private void setNonBigDecimalStageResults(CommonEngineConfiguration commonConfig,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i < commonConfig.getNumberOfRequestedExpectedUtilityValues(); i++) {
			StageResult stageResult = new StageResult();
			stageResult.setStage(i);
			stageResult.setExpectedUtility(0);
			if(commonConfig.isCalculateRelativeActionOccurances() || commonConfig.isCalculateAbsoluteActionOccurances())
				stageResult.setAbsoluteActionOccurances(getEmptyAbsoluteActionOccuranceMap(parameters));
			stageResults.add(stageResult);
		}
	}
	
	private Map<ActorAction, BigDecimal> getEmptyAbsoluteActionOccuranceMap(Parameters parameters) {
		Map<ActorAction, BigDecimal> occurancesMap = new HashMap<ActorAction, BigDecimal>();
		for(ActorAction action : parameters.getActorActions()){
			occurancesMap.put(action, new BigDecimal(0));
		}
		return occurancesMap;
	}
}
