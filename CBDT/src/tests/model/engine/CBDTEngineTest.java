package tests.model.engine;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cbdt.control.algorithm.TreeStyleSimulationEngine;
import cbdt.model.Result;
import cbdt.model.ResultAnalyser;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class CBDTEngineTest {

	TreeStyleSimulationEngine cbdtEngine;
	Parameters cbdtParameters;
	ResultAnalyser cbdtAnalyser;
	
	@Before
	public void engineSetUp(){
		cbdtEngine = new TreeStyleSimulationEngine();
	}
	
	@Test
	public void testCBDTsimulation(){
		cbdtParameters = new Parameters();
		cbdtParameters.setInitialAspirationLevel(100);
		cbdtParameters.setWeightingFactorAlpha(0.5);
		ActorAction actionA = new ActorAction("a");
		actionA.addActionOutcome(new ActorActionOutcome(1, 1));
		cbdtParameters.getActorActions().add(actionA);
		ActorAction actionB = new ActorAction("b");
		actionB.addActionOutcome(new ActorActionOutcome(0.5, 0));
		actionB.addActionOutcome(new ActorActionOutcome(0.5, 4));
		cbdtParameters.getActorActions().add(actionB);
		
		cbdtEngine.setMaxSimulationSteps(20);
		System.out.println("Start computation");
		Result result = cbdtEngine.computeSimulation(cbdtParameters);
		
		System.out.println("Start analysation");
		
		cbdtAnalyser = new ResultAnalyser();
		List<Double> cbuList = cbdtAnalyser.calculateCbu(result);
		
//		System.out.println(result);
		System.out.println(cbuList);
		
	}
}
