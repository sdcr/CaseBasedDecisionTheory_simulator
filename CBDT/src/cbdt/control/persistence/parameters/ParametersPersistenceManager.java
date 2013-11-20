package cbdt.control.persistence.parameters;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

import com.thoughtworks.xstream.XStream;

//GREEN
/**
 * A IParametersPersistenceManager which uses XML to store Parameter objects.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ParametersPersistenceManager implements
		IParametersPersistenceManager {

	public static final String PARAMETERS_NODE_NAME = "parameters";
	public static final String ACTOR_ACTION_OUTCOME_NODE_NAME = "actionOutcome";
	public static final String ACTOR_ACTION_NODE_NAME = "actorAction";

	private XStream xStream;

	public ParametersPersistenceManager() {
		xStream = new XStream();
		xStream.setClassLoader(getClass().getClassLoader());

		xStream.registerConverter(new ParametersConverter());
		xStream.alias(PARAMETERS_NODE_NAME, Parameters.class);
		xStream.registerConverter(new ActorActionConverter());
		xStream.alias(ACTOR_ACTION_NODE_NAME, ActorAction.class);
		xStream.registerConverter(new ActorActionOutcomeConverter());
		xStream.alias(ACTOR_ACTION_OUTCOME_NODE_NAME, ActorActionOutcome.class);
	}

	@Override
	public Parameters getParametersFromFile(String filepath)
			throws FileNotFoundException {
		File file = new File(filepath);
		if (!file.exists())
			throw new FileNotFoundException();
		return (Parameters) xStream.fromXML(file);
	}

	@Override
	public void saveParametersToFile(String filepath, Parameters parameters) {
		FileOutputStream outStream = null;
		try {
			outStream = new FileOutputStream(new File(filepath));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		xStream.toXML(parameters, outStream);
		try {
			outStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String convertToXML(Parameters params) {
		return xStream.toXML(params);
	}

	public Parameters parseXML(String parametersXML) {
		return (Parameters) xStream.fromXML(parametersXML);
	}
}
