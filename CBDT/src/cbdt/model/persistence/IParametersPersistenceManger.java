package cbdt.model.persistence;

import cbdt.model.Parameters;

public interface IParametersPersistenceManger {

	public Parameters getParametersFromFile(String filepath);
	
	public void saveParametersToFile(String filepath, Parameters parameters);

}
