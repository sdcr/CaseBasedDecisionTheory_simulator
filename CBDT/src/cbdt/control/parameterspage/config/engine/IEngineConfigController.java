package cbdt.control.parameterspage.config.engine;

//GREEN
import cbdt.model.config.AbstractEngineConfig;

/**
 * Every AbstractEngineConfig has its own AbstractEngineConfigController. 
 * It is supposed to receive any requests by the views to change the state of the object modeling
 * the respective AbstractEngineConfig.
 * @author Stephan da Costa Ribeiro
 *
 */
public interface IEngineConfigController {

	public void setEngineConfigModel(AbstractEngineConfig engineConfig);

}
