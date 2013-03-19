package cbdt.model.parameters.engineconfig;

public class DFSkeepTreeEngineConfig extends AbstractEngineConfiguration {
	
	private boolean saveTreeStructure;
	
	private boolean saveActionNames;
	
	private boolean saveAspirationLevels;
	
	@Override
	public String getName() {
		return "Depth first search with tree structure";
	}

	public boolean isSaveActionNames() {
		return saveActionNames;
	}

	public void setSaveActionNames(boolean calculateActionNames) {
		this.saveActionNames = calculateActionNames;
		setChanged();
		notifyObservers();
	}

	public boolean isSaveAspirationLevels() {
		return saveAspirationLevels;
	}

	public void setSaveAspirationLevels(boolean calculateAspirationLevels) {
		this.saveAspirationLevels = calculateAspirationLevels;
		setChanged();
		notifyObservers();
	}

	public boolean isSaveTreeStructure() {
		return saveTreeStructure;
	}

	public void setSaveTreeStructure(boolean saveTreeStructure) {
		this.saveTreeStructure = saveTreeStructure;
		setChanged();
		notifyObservers();
	}

}
