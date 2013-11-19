package cbdt.control.parameterspage.config.common;

import cbdt.model.config.CommonConfig;

public class CommonConfigController {
	
	private CommonConfig commonConfig;

	public void setCommonConfig(CommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}
	
	public void setCalcAbsActionOccurances(boolean selection) {
		commonConfig.setCalculateAbsoluteActionOccurances(selection);
	}

	public void setCalcRelActionOccurances(boolean selection) {
		commonConfig.setCalculateRelativeActionOccurances(selection);
	}

	public void setRequestedNumberOfExpectedUtilities(int numOfRequestedValues) {
		commonConfig.setNumberOfRequestedExpectedUtilityValues(numOfRequestedValues);
	}
	
	public void setCalcLowestAspirationLevels(boolean selection) {
		commonConfig.setCalculateLowestAspirationLevels(selection);
	}
}
