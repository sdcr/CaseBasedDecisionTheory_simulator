package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

/**
 * Represents the state of the computation with the DFSmatrixHighPrecision
 * algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class BigDecimalSimulationState {

	BigDecimal[][] absoluteActionOccurances;
	BigDecimal[][] relativeActionOccurances;
	BigDecimal[] expectedUtilities;
	BigDecimal[] lowestAspirationLevels;
	BigDecimalMatrixNodeContent[][] contentsMatrix;
}
