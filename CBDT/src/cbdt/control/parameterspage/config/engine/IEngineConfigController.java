package cbdt.control.parameterspage.config.engine;

//GREEN
import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * Every AbstractEngineConfig has its own IEngineConfigController. All classes
 * implementing this class are supposed to receive any requests by the views to
 * change the state of the respective AbstractEngineConfig.
 * 
 * @author Stephan da Costa Ribeiro
 */
public interface IEngineConfigController {

	public void setEngineConfigModel(AbstractEngineConfig engineConfig);

}
