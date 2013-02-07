package model.simulation.tests;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.CBDTSimulationParameters;
import cbdt.model.CBDTSimulationResult;
import cbdt.model.CBDTSimulationResultAnalyser;
import cbdt.model.engine.ConcreteCBDTSimulationEngine;

public class CBDTEngineTest {

	ConcreteCBDTSimulationEngine cbdtEngine;
	CBDTSimulationParameters cbdtParameters;
	CBDTSimulationResultAnalyser cbdtAnalyser;
	
	@Before
	public void engineSetUp(){
		cbdtEngine = new ConcreteCBDTSimulationEngine();
	}
	
	@Test
	public void testCBDTsimulation(){
		cbdtParameters = new CBDTSimulationParameters();
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
		CBDTSimulationResult result = cbdtEngine.computeSimulation(cbdtParameters);
		
		System.out.println("Start analysation");
		
		cbdtAnalyser = new CBDTSimulationResultAnalyser();
		List<Double> cbuList = cbdtAnalyser.calculateCbu(result);
		
//		System.out.println(result);
		System.out.println(cbuList);
		
	}
}
