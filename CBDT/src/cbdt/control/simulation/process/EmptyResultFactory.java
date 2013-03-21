package cbdt.control.simulation.process;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class EmptyResultFactory {

	public Result getEmptyResult(AbstractEngineConfiguration config, Parameters parameters) {
		Result result = new Result();
		List<StageResult> stageResults = new ArrayList<StageResult>();
		result.setStageResults(stageResults);
		
		for (int i = 0; i < config.getNumberOfRequestedExpectedUtilityValues(); i++) {
			StageResult stageResult = new StageResult();
			stageResult.setStage(i);
			stageResult.setExpectedUtility(0);
			if(config.isCalculateRelativeActionOccurances() || config.isCalculateAbsoluteActionOccurances())
				stageResult.setAbsoluteActionOccurances(getEmptyAbsoluteActionOccuranceMap(parameters));
			stageResults.add(stageResult);
		}
		return result;
	}
	
	private Map<ActorAction, Integer> getEmptyAbsoluteActionOccuranceMap(Parameters parameters) {
		Map<ActorAction, Integer> occurancesMap = new HashMap<ActorAction, Integer>();
		for(ActorAction action : parameters.getActorActions()){
			occurancesMap.put(action, 0);
		}
		return occurancesMap;
	}
}