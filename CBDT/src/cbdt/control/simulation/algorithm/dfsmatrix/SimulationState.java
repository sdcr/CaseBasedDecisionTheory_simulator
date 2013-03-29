package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

public class SimulationState {

	BigDecimal[][] absoluteActionOccurances;
	Double[][] relativeActionOccurances;
	Double[] expectedUtilities;
	Double[] lowestAspirationLevels;
	NodeContent[][] contentsMatrix;
}
