package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.AbstractEngineConfig;

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
