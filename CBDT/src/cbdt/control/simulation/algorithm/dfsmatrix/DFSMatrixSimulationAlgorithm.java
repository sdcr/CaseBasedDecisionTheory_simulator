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
	
	protected BigDecimal[][] absoluteActionOccurances;
	
	public BigDecimal[][] getChoosenActionNumbers() {
		return absoluteActionOccurances;
	}

	@Override
	public void computeResult(Result initResult) throws InterruptedException {
		BasicInitFactory factory = new BasicInitFactory(parameters,config);
		absoluteActionOccurances = factory.getInitialAbsoluteActionOccurances();
		Double[] expectedUtilities = factory.getInitExpectedUtilities();
		NodeContent[][] contentsMatrix = factory.getInitialContentsMatrix();
		
		VirtualNodeContentVisitor visitor = new VirtualNodeContentVisitor(parameters, config, contentsMatrix, factory, expectedUtilities, absoluteActionOccurances, monitor);

		computeWithVisitor(initResult, visitor);
		
		
		for(int stage=0; stage<initResult.getStageResults().size(); stage++){
			StageResult stageResult = initResult.getStageResults().get(stage);
			stageResult.setStage(stage);
			stageResult.setExpectedUtility(expectedUtilities[stage]);
			Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = new HashMap<ActorAction, BigDecimal>();
			for(int actionIndex=0; actionIndex<parameters.getActorActions().size(); actionIndex++){
				absoluteActionOccurancesMap.put(parameters.getActorActions().get(actionIndex), absoluteActionOccurances[stage][actionIndex]);
			}
			stageResult.setAbsoluteActionOccurances(absoluteActionOccurancesMap);
		}
	}

	protected void computeWithVisitor(Result initResult,
			VirtualNodeContentVisitor visitor) throws InterruptedException {
		boolean computeWithProgressShowing = true;
		
		int stageNumber=0;
		int numberOfLeafs = 1;
		while(stageNumber<15 && numberOfLeafs<15){
			stageNumber++;
			if(stageNumber==config.getNumberOfRequestedExpectedUtilityValues()){
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
			visitor.visitRecursively(0, 0, 1);
		}

	}

	public AbstractEngineConfiguration getMatrixConfig() {
		return config;
	}

	public void setMatrixConfig(AbstractEngineConfiguration config) {
		this.config = config;
	}
}
