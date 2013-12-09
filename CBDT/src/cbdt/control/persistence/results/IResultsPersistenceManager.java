package cbdt.control.persistence.results;

import java.io.IOException;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.result.Result;

//GREEN
/**
 * An interface for a manager which is able to store {@link Result}s and configs
 * to a file. It does not require he ability to load results or configs.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public interface IResultsPersistenceManager {

	/**
	 * Saves results and config objects to a file.
	 * 
	 * @param filepath
	 * @param result
	 * @param commonConfig
	 * @param engineConfig
	 * @throws IOException
	 */
	public void saveResultToFile(String filepath, Result result,
			CommonConfig commonConfig, AbstractEngineConfig engineConfig)
			throws IOException;
}
