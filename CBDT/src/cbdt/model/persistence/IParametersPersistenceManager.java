package cbdt.model.persistence;

import cbdt.model.parameters.Parameters;

public interface IParametersPersistenceManager {

	public Parameters getParametersFromFile(String filepath);
	
	public void saveParametersToFile(String filepath, Parameters parameters);

}
