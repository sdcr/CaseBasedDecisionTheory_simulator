package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;

public class DFSkeepTreeConfigController implements IEngineConfigController {

	private DFSkeepTreeEngineConfig config;

	@Override
	public void setEngineConfigModel(AbstractEngineConfig engineConfig) {
		config = (DFSkeepTreeEngineConfig) engineConfig;
	}

	/**
	 * @param selection
	 *            The value whether the tree should be computed for the user.
	 */
	public void setSaveTree(boolean selection) {
		config.setSaveTreeStructure(selection);
	}

	/**
	 * @param selection
	 *            The value whether in each node the aspiration level should be
	 *            calculated.
	 */
	public void setSaveAspirationLevels(boolean selection) {
		config.setSaveAspirationLevels(selection);
	}

	/**
	 * @param selection
	 *            The value whether in each node the name of the last action
	 *            should be saved.
	 */
	public void setSaveActionNames(boolean selection) {
		config.setSaveActionNames(selection);
	}

}
