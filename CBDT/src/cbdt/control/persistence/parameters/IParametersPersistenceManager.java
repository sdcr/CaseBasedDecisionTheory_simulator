package cbdt.control.persistence.parameters;

import java.io.FileNotFoundException;

import cbdt.model.parameters.Parameters;

/**
 * An interface for a manager which is able to store and load {@link Parameters}
 * objects to a file. It is not required to be able to store or load configs.
 * 
 * @author Stephan da Costa Ribeiro
 */
public interface IParametersPersistenceManager {

	/**
	 * Returns a {@link Parameters} object generated from a file found under the
	 * passed path.
	 * 
	 * @param filepath
	 * @return
	 * @throws FileNotFoundException
	 */
	public Parameters getParametersFromFile(String filepath)
			throws FileNotFoundException;

	/**
	 * Saves a {@link Parameters} object in a file under the passed path.
	 * 
	 * @param filepath
	 * @param parameters
	 */
	public void saveParametersToFile(String filepath, Parameters parameters);

}
