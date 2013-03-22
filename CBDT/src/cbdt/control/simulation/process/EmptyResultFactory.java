package cbdt.control.simulation.process;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class EmptyResultFactory {

	public Result getEmptyResult(AbstractEngineConfiguration config, Parameters parameters) {
		Result result = new Result();
		List<StageResult> stageResults = new ArrayList<StageResult>();
		result.setStageResults(stageResults);
		
		if(config instanceof DFSmatrixHighPrecEngineConfig)
			setBigDecimalStageResults(config, parameters, stageResults);
		else
			setNonBigDecimalStageResults(config, parameters, stageResults);
		return result;
	}

	private void setBigDecimalStageResults(AbstractEngineConfiguration config,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i < config.getNumberOfRequestedExpectedUtilityValues(); i++) {
			BigDecimalStageResult stageResult = new BigDecimalStageResult();
			stageResult.setStage(i);
			stageResult.setExpectedBigDecimalUtility(new BigDecimal(0));
			if(config.isCalculateRelativeActionOccurances() || config.isCalculateAbsoluteActionOccurances())
				stageResult.setAbsoluteActionOccurances(getEmptyAbsoluteActionOccuranceMap(parameters));
			stageResults.add(stageResult);
		}
	}

	private void setNonBigDecimalStageResults(AbstractEngineConfiguration config,
			Parameters parameters, List<StageResult> stageResults) {
		for (int i = 0; i < config.getNumberOfRequestedExpectedUtilityValues(); i++) {
			StageResult stageResult = new StageResult();
			stageResult.setStage(i);
			stageResult.setExpectedUtility(0);
			if(config.isCalculateRelativeActionOccurances() || config.isCalculateAbsoluteActionOccurances())
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
