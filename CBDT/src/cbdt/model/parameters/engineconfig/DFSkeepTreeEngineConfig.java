package cbdt.model.parameters.engineconfig;

public class DFSkeepTreeEngineConfig extends AbstractEngineConfiguration {
	
	private boolean calculateActionNames;
	
	private boolean calculateAspirationLevels;
	
	@Override
	public String getName() {
		return "Depth first search with tree structure";
	}

	public boolean isCalculateActionNames() {
		return calculateActionNames;
	}

	public void setCalculateActionNames(boolean calculateActionNames) {
		this.calculateActionNames = calculateActionNames;
	}

	public boolean isCalculateAspirationLevels() {
		return calculateAspirationLevels;
	}

	public void setCalculateAspirationLevels(boolean calculateAspirationLevels) {
		this.calculateAspirationLevels = calculateAspirationLevels;
	}

}
