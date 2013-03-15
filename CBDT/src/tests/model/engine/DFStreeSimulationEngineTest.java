package tests.model.engine;

import org.junit.Before;
import org.junit.Test;

import cbdt.control.algorithm.dfsmatrix.DFSmatrixStyleSimulationEngine;
import cbdt.control.algorithm.dfstree.DFStreeSimulationEngine;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class DFStreeSimulationEngineTest {

	private Parameters parameters;

	@Before
	public void setup(){
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
		
		parameters.setNumberOfRequestedExpectedUtilities(25);
	}
	
	@Test
	public void computeExpectedUtilitiesTest(){
		DFStreeSimulationEngine engine = new DFStreeSimulationEngine();
		double[] computedUtilities = engine.computeExpectedUtilities(parameters);
		for(double expectedUtility : computedUtilities){
			System.out.println(expectedUtility);
		}
//		double[] expected = {1.5, 1.5, 1.5, 1.5};
//		assertArrayEquals(computedUtilities, expected, Double.MIN_VALUE);
	}
}
