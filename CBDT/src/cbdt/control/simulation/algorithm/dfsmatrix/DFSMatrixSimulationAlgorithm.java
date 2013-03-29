package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class DFSMatrixSimulationAlgorithm extends SimulationAlgorithm {

	protected AbstractEngineConfiguration config;
	
	private SimulationState simState;
	
	public BigDecimal[][] getChoosenActionNumbers() {
		return simState.absoluteActionOccurances;
	}

	@Override
	public void computeResult(Result initResult) throws InterruptedException {
		BasicInitFactory factory = new BasicInitFactory(parameters, commonConfig);
		simState = factory.getInitSimulationState();
		
		VirtualNodeContentVisitor visitor = new VirtualNodeContentVisitor(parameters, 
				commonConfig, monitor, simState);

		computeWithVisitor(initResult, visitor);
		
		
		for(int stage=0; stage<initResult.getStageResults().size(); stage++){
			StageResult stageResult = initResult.getStageResults().get(stage);
			stageResult.setStage(stage);
			stageResult.setExpectedUtility(simState.expectedUtilities[stage]);
			stageResult.setLowestAspirationLevel(simState.lowestAspirationLevels[stage]);
			Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = new HashMap<ActorAction, BigDecimal>();
			for(int actionIndex=0; actionIndex<parameters.getActorActions().size(); actionIndex++){
				absoluteActionOccurancesMap.put(parameters.getActorActions().get(actionIndex), simState.absoluteActionOccurances[stage][actionIndex]);
			}
			stageResult.setAbsoluteActionOccurances(absoluteActionOccurancesMap);
			Map<ActorAction, Double> relativeActionOccurancesMap = new HashMap<ActorAction, Double>();
			for(int actionIndex=0; actionIndex<parameters.getActorActions().size(); actionIndex++){
				relativeActionOccurancesMap.put(parameters.getActorActions().get(actionIndex), simState.relativeActionOccurances[stage][actionIndex]);
			}
			stageResult.setRelativeActionOccurances(relativeActionOccurancesMap);
		}
	}

	protected void computeWithVisitor(Result initResult,
			VirtualNodeContentVisitor visitor) throws InterruptedException {
		boolean computeWithProgressShowing = true;
		
		int stageNumber=0;
		int numberOfLeafs = 1;
		while(stageNumber<15 && numberOfLeafs<15){
			stageNumber++;
			if(stageNumber==commonConfig.getNumberOfRequestedExpectedUtilityValues()+1){
				computeWithProgressShowing = false;
				break;
			}
			numberOfLeafs = visitor.calculteNumberOfLeafs(stageNumber);
		}
		
		if(computeWithProgressShowing){
			monitor.beginTask("Computation with DFS and matrix datastructure", numberOfLeafs);
			visitor.visitRecursively(0, 0, stageNumber);
			monitor.done();
		}else{
			visitor.visitRecursively(0, 0, 0);
		}

	}

	public AbstractEngineConfiguration getMatrixConfig() {
		return config;
	}

	public void setMatrixConfig(AbstractEngineConfiguration config) {
		this.config = config;
	}
}
