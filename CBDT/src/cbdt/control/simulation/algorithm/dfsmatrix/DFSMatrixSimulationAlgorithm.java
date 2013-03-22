package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import cbdt.control.simulation.algorithm.SimulationAlgorithm;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.engineconfig.DFSmatrixEngineConfig;
import cbdt.model.result.Result;
import cbdt.model.result.StageResult;

public class DFSMatrixSimulationAlgorithm extends SimulationAlgorithm {

	private DFSmatrixEngineConfig config;
	
	private BigDecimal[][] absoluteActionOccurances;
	
	public BigDecimal[][] getChoosenActionNumbers() {
		return absoluteActionOccurances;
	}

	@Override
	public void computeResult(Result initResult) {
		InitFactory factory = new InitFactory(parameters,config);
		absoluteActionOccurances = factory.getInitialAbsoluteActionOccurances();
		double[] expectedUtilities = factory.getInitExpectedUtilities();
		NodeContent[][] contentsMatrix = factory.getInitialContentsMatrix();
		
		VirtualNodeContentVisitor visitor = new VirtualNodeContentVisitor(parameters, config, contentsMatrix, factory, expectedUtilities, absoluteActionOccurances);
		visitor.visitRecursively(0, 0);
		
		for(int i=0; i<initResult.getStageResults().size(); i++){
			StageResult stageResult = initResult.getStageResults().get(i);
			stageResult.setStage(i);
			stageResult.setExpectedUtility(expectedUtilities[i]);
			Map<ActorAction, BigDecimal> absoluteActionOccurancesMap = new HashMap<ActorAction, BigDecimal>();
			for(int j=0; j<parameters.getActorActions().size(); j++){
				absoluteActionOccurancesMap.put(parameters.getActorActions().get(i), absoluteActionOccurances[i][j]);
			}
			stageResult.setAbsoluteActionOccurances(absoluteActionOccurancesMap);
		}
	}


	public DFSmatrixEngineConfig getConfig() {
		return config;
	}

	public void setConfig(DFSmatrixEngineConfig config) {
		this.config = config;
	}
}
