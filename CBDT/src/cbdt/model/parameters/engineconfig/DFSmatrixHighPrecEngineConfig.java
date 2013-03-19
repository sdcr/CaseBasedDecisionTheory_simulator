package cbdt.model.parameters.engineconfig;

public class DFSmatrixHighPrecEngineConfig extends AbstractEngineConfiguration {

	private int numberOfDecimalPlaces;
	
	@Override
	public String getName() {
		return "Depth first search with matrix, arbitrary precision";
	}
	
	public int getNumberOfDecimalPlaces() {
		return numberOfDecimalPlaces;
	}

	public void setNumberOfDecimalPlaces(int numberOfDecimalPlaces) {
		this.numberOfDecimalPlaces = numberOfDecimalPlaces;
	}
	
}
