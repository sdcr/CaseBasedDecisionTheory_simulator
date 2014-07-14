package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.dfsmatrix.IAlgoNodeContent;

/**
 * This class models the values a node of the tree holds, for the
 * DFSmatrixHighPrecision algorithm only. This implementation is different from
 * the implementation of NodeContent objects of the tree mode.
 * 
 * This class uses matrix on the basis of {@link BigDecimal} objects.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class BigDecimalMatrixNodeContent implements IAlgoNodeContent {

	BigDecimal probabilityProduct;

	BigDecimal[] numberOfOccurances;

	BigDecimal[] sumOfUtilities;

	BigDecimal aspirationLevel;

}
