package tests.model.engine;

import java.math.BigDecimal;
import java.math.MathContext;
import java.text.DecimalFormat;

import org.junit.Before;
import org.junit.Test;

import cbdt.control.simulation.algorithm.dfsmatrix.DFSallAllMatrixStyleSimulationEngine;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.DFSmatrixEngineConfig;

public class DFSMatrixSimulationEngineTest {

	private Parameters parameters;
	
	private DFSmatrixEngineConfig config;

	@Before
	public void setup() {
		parameters = new Parameters();
		ActorAction actionA = new ActorAction("A");
		actionA.addActionOutcome(new ActorActionOutcome(1, 1));

		ActorAction actionB = new ActorAction("B");
		actionB.addActionOutcome(new ActorActionOutcome(0.5, 0));
		actionB.addActionOutcome(new ActorActionOutcome(0.5, 1));

		parameters.addActorAction(actionA);
		parameters.addActorAction(actionB);

		parameters.setInitialAspirationLevel(100);
		parameters.setWeightingFactorAlpha(0.5);

		config.setNumberOfRequestedExpectedUtilityValues(50);
	}

	// @Test
	public void bigDecimalTest() {
		BigDecimal bigDivident = new BigDecimal(
				"1000000000000000000000000000000");
		BigDecimal bigDivisor = new BigDecimal(
				"3000000000000000000000000000000");

		BigDecimal bigQuotient = bigDivident.divide(bigDivisor,
				new MathContext(50));
		System.out.println(bigQuotient);
	}

	@Test
	public void computeExpectedUtilitiesTest() {
		MathContext mathContext = new MathContext(30);
		DFSallAllMatrixStyleSimulationEngine engine = new DFSallAllMatrixStyleSimulationEngine();
		double[] computedUtilities = engine
				.computeExpectedUtilities(parameters);
		for (double expectedUtility : computedUtilities) {
			System.out.println(expectedUtility);
		}
		DecimalFormat df = new DecimalFormat("#.#####");
		for (BigDecimal[] stageChoosenActionNumbers : engine
				.getChoosenActionNumbers()) {
			BigDecimal sum = new BigDecimal(0);
			for (int i = 0; i < stageChoosenActionNumbers.length; i++) {
				System.out.print(parameters.getActorActions().get(i)
						.getActionName()
						+ ": " + stageChoosenActionNumbers[i] + ", ");
				sum = sum.add(stageChoosenActionNumbers[i], mathContext);
			}
			System.out.print("Anteile: ");
			for (int i = 0; i < stageChoosenActionNumbers.length; i++) {
				BigDecimal anteil = stageChoosenActionNumbers[i].divide(sum,
						mathContext);
				System.out.print(parameters.getActorActions().get(i)
						.getActionName()
						+ ": " + df.format(anteil) + ", ");
			}

			System.out.println();
		}
		// List<List<Character>> choosenActions = engine.getChoosenActions();
		// for(int i=0; i<5; i++){
		// List<Character> stageActions = choosenActions.get(i);
		// for(int j=0; j<stageActions.size(); j++){
		// System.out.print(parameters.getActorActions().get(stageActions.get(j)).getActionName()
		// + " ");
		// }
		// System.out.println();
		// }

		// double[] expected = {1.5, 1.5, 1.5, 1.5};
		// assertArrayEquals(computedUtilities, expected, Double.MIN_VALUE);
	}
}
