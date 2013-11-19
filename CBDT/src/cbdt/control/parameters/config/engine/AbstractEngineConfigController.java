package cbdt.control.parameters.config.engine;

import cbdt.model.parameters.engineconfig.AbstractEngineConfig;

/**
 * Every AbstractEngineConfig has its own AbstractEngineConfigController. 
 * It is supposed to receive any requests by the views to change the state of the object modelling
 * the respective AbstractEngineConfig.
 * @author Stephan da Costa Ribeiro
 *
 */
public interface AbstractEngineConfigController {

	public void setEngineConfigModel(AbstractEngineConfig engineConfig);

}
