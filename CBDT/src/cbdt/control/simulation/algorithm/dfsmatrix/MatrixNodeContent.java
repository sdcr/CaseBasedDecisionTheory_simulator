package cbdt.control.simulation.algorithm.dfsmatrix;


/**
 * This class models the values a node of the tree holds, for the DFSmatrix
 * algorithm only. This implementation is different from the implementation of
 * NodeContent objects of the tree mode.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class MatrixNodeContent implements IAlgoNodeContent {

	public double probabilityProduct;

	public int[] numberOfOccurances;

	public double[] sumOfUtilities;

	public double aspirationLevel;

}
