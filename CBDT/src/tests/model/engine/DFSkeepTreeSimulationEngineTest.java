package tests.model.engine;

import org.junit.Before;
import org.junit.Test;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;

public class DFSkeepTreeSimulationEngineTest {

	private Parameters parameters;

	private DFSkeepTreeEngineConfig config;
	
	@Before
	public void setup(){
		config = new DFSkeepTreeEngineConfig();
		parameters = new Parameters();
		ActorAction actionA = new ActorAction("A");
		actionA.addActionOutcome(new ActorActionOutcome(1, 1));
		
		ActorAction actionB = new ActorAction("B");
		actionB.addActionOutcome(new ActorActionOutcome(0.5, 0));
		actionB.addActionOutcome(new ActorActionOutcome(0.5, 4));
		
		parameters.addActorAction(actionA);
		parameters.addActorAction(actionB);
		
		parameters.setInitialAspirationLevel(100);
		parameters.setWeightingFactorAlpha(0.5);
		
		config.setNumberOfRequestedExpectedUtilityValues(25);
	}
	
	@Test
	public void computeExpectedUtilitiesTest(){
//		DFStreeSimulationAlgorithm engine = new DFStreeSimulationAlgorithm();
//		engine.setParameters(parameters);
//		DFStreeResult result = (DFStreeResult) engine.computeExpectedUtilities();
//		for(double expectedUtility : result.getExpectedUtilities()){
//			System.out.println(expectedUtility);
//		}
//		double[] expected = {1.5, 1.5, 1.5, 1.5};
//		assertArrayEquals(computedUtilities, expected, Double.MIN_VALUE);
	}
}
