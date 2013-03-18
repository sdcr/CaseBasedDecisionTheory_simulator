package tests.model.engine;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import cbdt.control.simulation.algorithm.dfsmatrix.highprecision.DFSallAllHighPrecMatrixStyleSimulationEngine;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class DFSallAllHighPrecMatrixSimulationEngineTest {

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
	
//	@Test
	public void bigDecimalTest(){
		BigDecimal bigDivident = new BigDecimal(1.0);
		BigDecimal bigDivisor = new BigDecimal(3.0);
		
		BigDecimal bigQuotient = bigDivident.divide(bigDivisor, new MathContext(500));
		System.out.println(bigQuotient);
	}
	
	@Test
	public void computeExpectedUtilitiesTest(){
		DFSallAllHighPrecMatrixStyleSimulationEngine engine = new DFSallAllHighPrecMatrixStyleSimulationEngine();
		BigDecimal[] computedUtilities = engine.computeExpectedUtilities(parameters);
		for(BigDecimal expectedUtility : computedUtilities){
			System.out.println(expectedUtility);
		}
		DecimalFormat df = new DecimalFormat("#.#####");
		for(int[] stageChoosenActionNumbers : engine.getChoosenActionNumbers()){
			double sum = 0;
			for(int i=0; i<stageChoosenActionNumbers.length; i++){
				System.out.print(parameters.getActorActions().get(i).getActionName() + ": " + stageChoosenActionNumbers[i]+ ", ");
				sum += stageChoosenActionNumbers[i];
			}
			System.out.print("Anteile: ");
			for(int i=0; i<stageChoosenActionNumbers.length; i++){
				double anteil = ((double)stageChoosenActionNumbers[i])/sum;
				System.out.print(parameters.getActorActions().get(i).getActionName() + ": " + df.format(anteil) + ", ");
			}
			
			System.out.println();
		}
		List<List<Character>> choosenActions = engine.getChoosenActions();
		for(int i=0; i<5; i++){
			List<Character> stageActions = choosenActions.get(i);
			for(int j=0; j<stageActions.size(); j++){
				System.out.print(parameters.getActorActions().get(stageActions.get(j)).getActionName() + " ");
			}
			System.out.println();
		}
		
//		double[] expected = {1.5, 1.5, 1.5, 1.5};
//		assertArrayEquals(computedUtilities, expected, Double.MIN_VALUE);
	}
}
