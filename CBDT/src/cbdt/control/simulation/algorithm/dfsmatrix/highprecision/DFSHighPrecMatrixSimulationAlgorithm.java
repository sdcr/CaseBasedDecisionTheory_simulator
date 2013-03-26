package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cbdt.control.simulation.algorithm.dfsmatrix.DFSMatrixSimulationAlgorithm;
import cbdt.model.parameters.ActorAction;
import cbdt.model.result.BigDecimalStageResult;
import cbdt.model.result.Result;

public class DFSHighPrecMatrixSimulationAlgorithm extends DFSMatrixSimulationAlgorithm {

	
	@Override
	public void computeResult(Result initResult) throws InterruptedException {
		BigDecimalInitFactory factory = new BigDecimalInitFactory(parameters, commonConfig);
		absoluteActionOccurances = factory.getInitialActionOccurances();
		BigDecimal[][] relativeActionOccurances = factory.getInitialActionOccurances();
		BigDecimal[] expectedUtilities = (BigDecimal[])factory.getInitExpectedUtilities();
		BigDecimalNodeContent[][] contentsMatrix = (BigDecimalNodeContent[][]) factory.getInitialContentsMatrix();
		
		BigDecimalVirtualNodeContentVisitor visitor = new BigDecimalVirtualNodeContentVisitor(parameters, 
				commonConfig, contentsMatrix, factory, expectedUtilities, 
				absoluteActionOccurances, relativeActionOccurances, monitor);

		computeWithVisitor(initResult, visitor);
		
		for(int stage=0; stage<initResult.getStageResults().size(); stage++){
			BigDecimalStageResult stageResult = (BigDecimalStageResult)initResult.getStageResults().get(stage);
			stageResult.setStage(stage);
			stageResult.setExpectedBigDecimalUtility(expectedUtilities[stage]);
			Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = makeToBigDecimalMap(stage, absoluteActionOccurances);
			stageResult.setAbsoluteActionOccurances(absoluteActionOccurancesMap);
			Map<ActorAction, BigDecimal> relativeActionOccurancesMap = makeToBigDecimalMap(stage, relativeActionOccurances);
			stageResult.setRelativeBigDecimalActionOccurances(relativeActionOccurancesMap);
		}
	}

	private Map<ActorAction, BigDecimal> makeToBigDecimalMap(int stage, BigDecimal[][] actionOccurances) {
		Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = new HashMap<ActorAction, BigDecimal>();
		for(int actionIndex=0; actionIndex<parameters.getActorActions().size(); actionIndex++){
			absoluteActionOccurancesMap.put(parameters.getActorActions().get(actionIndex), actionOccurances[stage][actionIndex]);
		}
		return absoluteActionOccurancesMap;
	}
//
//	private Map<ActorAction, Double> makeToDoubleMap(int stage, BigDecimal[][] actionOccurances) {
//		Map<ActorAction, Double> actionOccurancesMap = new HashMap<ActorAction, Double>();
//		for(int actionIndex=0; actionIndex<parameters.getActorActions().size(); actionIndex++){
//			actionOccurancesMap.put(parameters.getActorActions().get(actionIndex), actionOccurances[stage][actionIndex].doubleValue());
//		}
//		return actionOccurancesMap;
//	}

}
