package cbdt.model.config.engine;

//GREEN
/**
 * This class models the engine configuration for an execution with the
 * DFSmatrixHighPrecision algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSmatrixHighPrecEngineConfig extends AbstractEngineConfig {

	/**
	 * The required accuracy in terms of the number of decimal places after the
	 * decimal point.
	 */
	private int numberOfDecimalPlaces;

	@Override
	public String getName() {
		return "DFS with matrix structure, arbitrary precision";
	}

	/**
	 * @return Returns the number of decimal places after the decimal point, to
	 *         describe the accuracy with which the algorithm computes the
	 *         simulation results.
	 */
	public int getNumberOfDecimalPlaces() {
		return numberOfDecimalPlaces;
	}

	/**
	 * Sets the number of decimal places after the decimal point, to describe
	 * the accuracy with which the algorithm computes the simulation results.
	 * 
	 * @param numberOfDecimalPlaces
	 */
	public void setNumberOfDecimalPlaces(int numberOfDecimalPlaces) {
		this.numberOfDecimalPlaces = numberOfDecimalPlaces;
	}

}
