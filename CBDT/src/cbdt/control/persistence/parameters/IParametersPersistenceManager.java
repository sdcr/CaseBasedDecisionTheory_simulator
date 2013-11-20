package cbdt.control.persistence.parameters;

import java.io.FileNotFoundException;

import cbdt.model.parameters.Parameters;

//GREEN
/**
 * An interface for a manager which is able to store and load Parameter objects
 * to a file. It does not required the ability to store or load configs.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public interface IParametersPersistenceManager {

	public Parameters getParametersFromFile(String filepath)
			throws FileNotFoundException;

	public void saveParametersToFile(String filepath, Parameters parameters);

}
