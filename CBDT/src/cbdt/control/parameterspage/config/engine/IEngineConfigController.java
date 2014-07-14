package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * A class implementing this interface is supposed to handle requests (from user
 * interaction with the GUI) to change the state of a respective
 * {@link AbstractEngineConfig}.<br>
 * <br>
 * 
 * Every subclass of {@link AbstractEngineConfig} is supposed to have its own
 * {@link IEngineConfigController} implementation.
 * 
 * @author Stephan da Costa Ribeiro
 */
public interface IEngineConfigController {

	/**
	 * @param engineConfig
	 *            The {@link AbstractEngineConfig} object this
	 *            {@link IEngineConfigController} implementation is responsible
	 *            of.
	 */
	public void setEngineConfigModel(AbstractEngineConfig engineConfig);

}
