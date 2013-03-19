package cbdt.control.pages.engineconfig;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public abstract class AbstractEngineConfigController {

	private AbstractEngineConfiguration engineConfig;

	public void setEngineConfigModel(AbstractEngineConfiguration engineConfig){
		this.engineConfig = engineConfig;
	}
	
	public void setRequestedNumberOfExpectedUtilities(int num) {
		engineConfig.setRequestedExpectedUtilityValues(num);
	}

	public void setCalcAbsActionOccurances(boolean selection) {
		engineConfig.setCalculateAbsoluteActionOccurances(selection);
	}

	public void setCalcRelActionOccurances(boolean selection) {
		engineConfig.setCalculateRelativeActionOccurances(selection);
	}

}
