package cbdt.control.parameterspage.config.common;

import cbdt.model.config.common.CommonConfig;

/**
 * The controller which handles all requests to change the {@link CommonConfig}
 * model object.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class CommonConfigController {

	/**
	 * The managed {@link CommonConfig} model.
	 */
	private CommonConfig commonConfig;

	/**
	 * Set the {@link CommonConfig} object.
	 * @param commonConfig
	 */
	public void setCommonConfig(CommonConfig commonConfig) {
		this.commonConfig = commonConfig;
	}

	/**
	 * TODO more details
	 * 
	 * @param selection
	 *            The value whether absolute action occurrences shall be
	 *            calculated.
	 */
	public void setCalcAbsActionOccurances(boolean selection) {
		commonConfig.setCalculateAbsoluteActionOccurances(selection);
	}

	/**
	 * TODO more details
	 * 
	 * @param selection
	 *            The value whether relative action occurrences shall be
	 *            calculated.
	 */
	public void setCalcRelActionOccurances(boolean selection) {
		commonConfig.setCalculateRelativeActionOccurances(selection);
	}

	/**
	 * @param numOfRequestedValues
	 *            The number of simulation steps that should be calculated.
	 */
	public void setRequestedNumberOfExpectedUtilities(int numOfRequestedValues) {
		commonConfig
				.setNumberOfRequestedExpectedUtilityValues(numOfRequestedValues);
	}

	/**
	 * TODO more details
	 * 
	 * @param selection
	 *            The value whether at each simulation step the lowest
	 *            aspiration level should be calculated.
	 */
	public void setCalcLowestAspirationLevels(boolean selection) {
		commonConfig.setCalculateLowestAspirationLevels(selection);
	}
}
