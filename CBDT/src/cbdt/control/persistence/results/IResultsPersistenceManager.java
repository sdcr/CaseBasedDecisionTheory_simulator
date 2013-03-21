package cbdt.control.persistence.results;

import java.io.IOException;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.result.Result;

public interface IResultsPersistenceManager {

	public void saveResultToFile(String filepath, Result result, AbstractEngineConfiguration config) throws IOException;
}
