package cbdt.control.pages.engineconfig;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;

public class DFSkeepTreeConfigController implements AbstractEngineConfigController {

	private DFSkeepTreeEngineConfig config;

	public void setEngineConfigModel(AbstractEngineConfiguration engineConfig) {
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
