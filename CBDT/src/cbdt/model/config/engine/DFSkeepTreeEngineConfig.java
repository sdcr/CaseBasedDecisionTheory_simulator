package cbdt.model.config.engine;

import cbdt.model.parameters.ActorAction;

//GREEN
/**
 * This class models the configuration for the DFSkeepTree algorithm.
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

	/**
	 * Returns whether the names of the {@link ActorAction}s taken at a node in
	 * the tree structure should be saved.
	 * 
	 * @return
	 */
	public boolean isSaveActionNames() {
		return saveActionNames;
	}

	/**
	 * Set whether the names of the {@link ActorAction}s taken at a node in the
	 * tree structure should be saved.
	 * 
	 * @param saveActionNames
	 */
	public void setSaveActionNames(boolean saveActionNames) {
		this.saveActionNames = saveActionNames;
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns whether the aspiration level in each node should is saved during
	 * the computation.
	 * 
	 * @return
	 */
	public boolean isSaveAspirationLevels() {
		return saveAspirationLevels;
	}

	/**
	 * Sets whether the aspiration level in each node should is saved during the
	 * computation.
	 * 
	 * @param saveAspirationLevels
	 */
	public void setSaveAspirationLevels(boolean saveAspirationLevels) {
		this.saveAspirationLevels = saveAspirationLevels;
		setChanged();
		notifyObservers();
	}

	/**
	 * Returns whether the the tree structure is saved during the computation.
	 * 
	 * @return
	 */
	public boolean isSaveTreeStructure() {
		return saveTreeStructure;
	}

	/**
	 * Sets whether the tree structure is saved during the computation.
	 * 
	 * @param saveTreeStructure
	 */
	public void setSaveTreeStructure(boolean saveTreeStructure) {
		this.saveTreeStructure = saveTreeStructure;
		setChanged();
		notifyObservers();
	}

}
