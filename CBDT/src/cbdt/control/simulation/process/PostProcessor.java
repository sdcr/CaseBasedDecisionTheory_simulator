package cbdt.control.simulation.process;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.HashMap;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;

public class PostProcessor {
	
	public void postProcess(Result result, AbstractEngineConfiguration config){
		if(config.isCalculateRelativeActionOccurances()){
			if(config instanceof DFSmatrixHighPrecEngineConfig){
				Map<ActorAction, BigDecimal> relActionOccurancesMap = null;
				for (int i = 0; i < config.getNumberOfRequestedExpectedUtilityValues(); i++) {
					relActionOccurancesMap = getBigDecimalRelativeActionOccuranceMaps(result.getStageResults().get(i).getAbsoluteActionOccurances());
					((BigDecimalStageResult)result.getStageResults().get(i)).setRelativeBigDecimalActionOccurances(relActionOccurancesMap);
				}
				
			} else{
				Map<ActorAction, Double> relActionOccurancesMap = null;
				for (int i = 0; i < config.getNumberOfRequestedExpectedUtilityValues(); i++) {
					relActionOccurancesMap = getRelativeActionOccuranceMaps(result.getStageResults().get(i).getAbsoluteActionOccurances());
					result.getStageResults().get(i).setRelativeActionOccurances(relActionOccurancesMap);
				}
			}
		}
	}
	
	private Map<ActorAction, Double> getRelativeActionOccuranceMaps(
			Map<ActorAction, BigDecimal> absActionOccurancesMap) {
		Map<ActorAction, Double> relativeOccuranceMap = new HashMap<ActorAction, Double>();
		MathContext mathContext = new MathContext(30);
		BigDecimal totalOccurances = computeTotalOccurances(absActionOccurancesMap,
				mathContext);
		for (ActorAction action : absActionOccurancesMap.keySet()) {
			relativeOccuranceMap.put(action,
					absActionOccurancesMap.get(action).divide(totalOccurances, mathContext).doubleValue());
		}
		return relativeOccuranceMap;
	}

	private Map<ActorAction, BigDecimal> getBigDecimalRelativeActionOccuranceMaps(
			Map<ActorAction, BigDecimal> absActionOccurancesMap) {
		Map<ActorAction, BigDecimal> relativeOccuranceMap = new HashMap<ActorAction, BigDecimal>();
		MathContext mathContext = new MathContext(30);
		BigDecimal totalOccurances = computeTotalOccurances(absActionOccurancesMap,
				mathContext);
		for (ActorAction action : absActionOccurancesMap.keySet()) {
			relativeOccuranceMap.put(action,
					absActionOccurancesMap.get(action).divide(totalOccurances, mathContext));
		}
		return relativeOccuranceMap;
	}

	private BigDecimal computeTotalOccurances(
			Map<ActorAction, BigDecimal> absActionOccurancesMap,
			MathContext mathContext) {
		BigDecimal totalOccurances = new BigDecimal(0);
		for (BigDecimal occurances : absActionOccurancesMap.values()) {
			totalOccurances = totalOccurances.add(occurances, mathContext);
		}
		return totalOccurances;
	}
	
}
