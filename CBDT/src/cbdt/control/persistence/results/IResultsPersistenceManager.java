package cbdt.control.persistence.results;

import java.io.IOException;

import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.CommonConfig;
import cbdt.model.result.Result;

public interface IResultsPersistenceManager {

	public void saveResultToFile(String filepath, Result result, CommonConfig commonConfig, AbstractEngineConfig config) throws IOException;
}
