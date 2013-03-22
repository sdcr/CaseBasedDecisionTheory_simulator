package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.INodeContent;


public class BigDecimalNodeContent implements INodeContent {
	
	BigDecimal probabilityProduct;
	
	BigDecimal[] numberOfOccurances;
	
	BigDecimal[] sumOfUtilities;
	
	BigDecimal aspirationLevel;

}
