package cbdt.control.simulation.process;

import java.util.HashMap;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;

public class PostProcessor {
	
	public void postProcess(Result result, AbstractEngineConfiguration config){
		if(config.isCalculateRelativeActionOccurances()){
			Map<ActorAction, Double> relActionOccurancesMap = null;
			for (int i = 0; i < config.getNumberOfRequestedExpectedUtilityValues(); i++) {
				relActionOccurancesMap = getRelativeActionOccuranceMaps(result.getStageResults().get(i).getAbsoluteActionOccurances());
				result.getStageResults().get(i).setRelativeActionOccurances(relActionOccurancesMap);
			}
		}
	}
	
	private Map<ActorAction, Double> getRelativeActionOccuranceMaps(
			Map<ActorAction, Integer> absActionOccurancesMap) {
		Map<ActorAction, Double> relativeOccuranceMap = new HashMap<ActorAction, Double>();
		int totalOccurances = 0;
		for (int occurances : absActionOccurancesMap.values()) {
			totalOccurances += occurances;
		}
		for (ActorAction action : absActionOccurancesMap.keySet()) {
			relativeOccuranceMap.put(action,
					(double) absActionOccurancesMap.get(action)
							/ totalOccurances);
		}
		return relativeOccuranceMap;
	}
	
}
