package cbdt.model.config.engine;

//GREEN
/**
 * The engine config which models the configuration for the DFSkeepTree
 * algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSkeepTreeEngineConfig extends AbstractEngineConfig {

	/**
	 * Determines if the algorithm should save the tree structure during
	 * computation.
	 */
	private boolean saveTreeStructure;

	/**
	 * Determines whether the algorithm should save the names of the selected
	 * actions in each node of the tree.
	 */
	private boolean saveActionNames;

	/**
	 * Determines whether the aspiration level in each node should be saved.
	 */
	private boolean saveAspirationLevels;

	@Override
	public String getName() {
		return "DFS with tree structure";
	}

	public boolean isSaveActionNames() {
		return saveActionNames;
	}

	public void setSaveActionNames(boolean saveActionNames) {
		this.saveActionNames = saveActionNames;
		setChanged();
		notifyObservers();
	}

	public boolean isSaveAspirationLevels() {
		return saveAspirationLevels;
	}

	public void setSaveAspirationLevels(boolean saveAspirationLevels) {
		this.saveAspirationLevels = saveAspirationLevels;
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
