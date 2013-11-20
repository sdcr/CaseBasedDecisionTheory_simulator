package cbdt.model.config.engine;

//GREEN
/**
 * The class modeling the engine configuration for an execution with the
 * DFSmatrixHighPrecision algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSmatrixHighPrecEngineConfig extends AbstractEngineConfig {

	/**
	 * The required accuracy in terms of the number of decimal places after the
	 * comma.
	 */
	private int numberOfDecimalPlaces;

	@Override
	public String getName() {
		return "DFS with matrix structure, arbitrary precision";
	}

	public int getNumberOfDecimalPlaces() {
		return numberOfDecimalPlaces;
	}

	public void setNumberOfDecimalPlaces(int numberOfDecimalPlaces) {
		this.numberOfDecimalPlaces = numberOfDecimalPlaces;
	}

}
