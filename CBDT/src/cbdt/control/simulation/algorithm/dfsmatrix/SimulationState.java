package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

/**
 * Represents the state of the computation with the DFSmatrix algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SimulationState {

	BigDecimal[][] absoluteActionOccurances;
	Double[][] relativeActionOccurances;
	Double[] expectedUtilities;
	Double[] lowestAspirationLevels;
	MatrixNodeContent[][] contentsMatrix;
}
