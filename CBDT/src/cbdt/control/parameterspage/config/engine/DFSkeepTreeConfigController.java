package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.DFSkeepTreeEngineConfig;

public class DFSkeepTreeConfigController implements AbstractEngineConfigController {

	private DFSkeepTreeEngineConfig config;

	public void setEngineConfigModel(AbstractEngineConfig engineConfig) {
		config = (DFSkeepTreeEngineConfig) engineConfig;
	}
	
	public void setSaveTree(boolean selection) {
		config.setSaveTreeStructure(selection);
	}

	public void setSaveAspirationLevels(boolean selection) {
		config.setSaveAspirationLevels(selection);
	}

	public void setSaveActionNames(boolean selection) {
		config.setSaveActionNames(selection);
	}

}
