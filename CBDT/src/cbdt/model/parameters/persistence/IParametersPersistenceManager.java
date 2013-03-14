package cbdt.model.parameters.persistence;

import java.io.FileNotFoundException;

import cbdt.model.parameters.Parameters;

public interface IParametersPersistenceManager {

	public Parameters getParametersFromFile(String filepath) throws FileNotFoundException;
	
	public void saveParametersToFile(String filepath, Parameters parameters);

}
