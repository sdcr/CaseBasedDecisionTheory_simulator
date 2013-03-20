package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class DFStreeSimulationAlgorithm extends SimulationAlgorithm {

	private DFSkeepTreeEngineConfig config;
	
	@Override
	public Result computeExpectedUtilities(){
		NodeContentKeepTree rootContent = getInitRootContent(parameters);
		NodeShellKeepTree rootShell = new NodeShellKeepTree(rootContent);

		double[] expectedUtilities = new double[config.getNumberOfRequestedExpectedUtilityValues()];

		List<Map<ActorAction, Integer>> absActionOccurancesMaps = getEmptyAbsoluteActionOccuranceMaps(parameters);
		rootShell.computeChildren(parameters, config, expectedUtilities, absActionOccurancesMaps, 0);
		
		List<Map<ActorAction, Double>> relActionOccurancesMaps = getRelativeActionOccuranceMaps(absActionOccurancesMaps);
		Result result = new Result();
		List<StageResult> stageResults = new ArrayList<StageResult>();
		for (int i = 0; i < config.getNumberOfRequestedExpectedUtilityValues(); i++) {
			StageResult stageResult = new StageResult();
			stageResult.setStage(i);
			stageResult.setExpectedUtility(expectedUtilities[i]);
			stageResult.setAbsoluteActionOccurances(absActionOccurancesMaps.get(i));
			stageResult.setRelativeActionOccurances(relActionOccurancesMaps.get(i));
			stageResults.add(stageResult);
		}
		result.setStageResults(stageResults);
		
//		DFStreeResult endResult = new DFStreeResult();
//		if(config.isCalculateAbsoluteActionOccurances()){
//			endResult.setAbsoluteActionOccurances(absActionOccurancesMaps);
//		}
//		if (config.isCalculateRelativeActionOccurances()) {
//			endResult.setRelativeActionOccurances(getRelativeActionOccuranceMaps(absActionOccurancesMaps));
//		}
//		if (config.isSaveTreeStructure()) {
//			endResult.setRootNode(rootShell);
//		}
		return result;
	}

	private List<Map<ActorAction, Double>> getRelativeActionOccuranceMaps(
			List<Map<ActorAction, Integer>> absActionOccurancesMaps) {
		List<Map<ActorAction, Double>> relativeActionOccurances = new ArrayList<Map<ActorAction, Double>>();
		for(int i=0; i< config.getNumberOfRequestedExpectedUtilityValues(); i++){
			Map<ActorAction, Double> relativeOccuranceMap = new HashMap<ActorAction, Double>();
			int totalOccurances=0;
			for (int occurances : absActionOccurancesMaps.get(i).values()){
				totalOccurances += occurances;
			}
			for(ActorAction action : absActionOccurancesMaps.get(i).keySet()){
				relativeOccuranceMap.put(action, (double) absActionOccurancesMaps.get(i).get(action) / totalOccurances);
			}
			relativeActionOccurances.add(relativeOccuranceMap);
		}
		return relativeActionOccurances;
	}

	private List<Map<ActorAction, Integer>> getEmptyAbsoluteActionOccuranceMaps(Parameters parameters) {
		List<Map<ActorAction, Integer>> actionOccurances = null;
		if(config.isCalculateAbsoluteActionOccurances() || config.isCalculateRelativeActionOccurances()){
			actionOccurances = new ArrayList<Map<ActorAction, Integer>>();
			for(int i=0; i<config.getNumberOfRequestedExpectedUtilityValues(); i++){
				Map<ActorAction, Integer> occurancesMap = new HashMap<ActorAction, Integer>();
				for(ActorAction action : parameters.getActorActions()){
					occurancesMap.put(action, 0);
				}
				actionOccurances.add(occurancesMap);
			}
		}
		return actionOccurances;
	}

	private NodeContentKeepTree getInitRootContent(Parameters parameters) {
		NodeContentKeepTree rootContent = new NodeContentKeepTree();
		rootContent.setProbabilityProduct(1);
		Map<ActorAction, Integer> numberOfOccurances = new HashMap<ActorAction, Integer>();
		Map<ActorAction, Double> sumOfUtilities = new HashMap<ActorAction, Double>();
		
		for(ActorAction action : parameters.getActorActions()){
			numberOfOccurances.put(action, 0);
			sumOfUtilities.put(action, 0.0);
		}
		rootContent.setNumberOfOccurances(numberOfOccurances);
		rootContent.setSumOfUtilities(sumOfUtilities);
		rootContent.setAspirationLevel(parameters.getInitialAspirationLevel());
		return rootContent;
	}

	public DFSkeepTreeEngineConfig getConfig() {
		return config;
	}

	public void setConfig(DFSkeepTreeEngineConfig config) {
		this.config = config;
	}

}
