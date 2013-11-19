package cbdt.model.parameters.engineconfig;

public class DFSmatrixHighPrecEngineConfig extends AbstractEngineConfig {

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
