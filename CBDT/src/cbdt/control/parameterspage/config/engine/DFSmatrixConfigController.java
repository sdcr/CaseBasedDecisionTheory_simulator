package cbdt.control.parameterspage.config.engine;

import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSmatrixEngineConfig;

/**
 * The config controller for the {@link DFSmatrixEngineConfig}.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class DFSmatrixConfigController implements IEngineConfigController {

	@Override
	public void setEngineConfigModel(AbstractEngineConfig engineConfig) {
		// no changes on the model class done for this configuration anyway
	}

}
