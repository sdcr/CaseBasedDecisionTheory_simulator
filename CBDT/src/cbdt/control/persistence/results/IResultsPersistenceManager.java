package cbdt.control.persistence.results;

import java.io.IOException;

import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.CommonConfig;
import cbdt.model.result.Result;

//GREEN
/**
 * An interface for a manager which is able to store results and configs to a
 * file. It does not require he ability to load results or configs.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public interface IResultsPersistenceManager {

	public void saveResultToFile(String filepath, Result result,
			CommonConfig commonConfig, AbstractEngineConfig engineConfig)
			throws IOException;
}
