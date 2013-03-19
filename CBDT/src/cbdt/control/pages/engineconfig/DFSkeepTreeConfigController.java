package cbdt.control.pages.engineconfig;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;

public class DFSkeepTreeConfigController extends AbstractEngineConfigController {

	private DFSkeepTreeEngineConfig config;

	public void setEngineConfigModel(AbstractEngineConfiguration engineConfig) {
		DFSkeepTreeEngineConfig config = (DFSkeepTreeEngineConfig) engineConfig;
		super.setEngineConfigModel(config);
	}
	
	public void setSaveTree(boolean selection) {
		config.setSaveTreeStructure(selection);
		if(selection == false){
			config.setSaveActionNames(false);
			config.setSaveAspirationLevels(false);
		}
	}

	public void setSaveAspirationLevels(boolean selection) {
		config.setSaveAspirationLevels(selection);
		if(selection)
			config.setSaveTreeStructure(true);
	}

	public void setSaveActionNames(boolean selection) {
		config.setSaveActionNames(selection);
		if(selection)
			config.setSaveTreeStructure(true);
	}

}
