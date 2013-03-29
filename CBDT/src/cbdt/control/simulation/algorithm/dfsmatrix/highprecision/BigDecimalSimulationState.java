package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

public class BigDecimalSimulationState {

	BigDecimal[][] absoluteActionOccurances;
	BigDecimal[][] relativeActionOccurances;
	BigDecimal[] expectedUtilities;
	BigDecimal[] lowestAspirationLevels;
	BigDecimalNodeContent[][] contentsMatrix;
}
