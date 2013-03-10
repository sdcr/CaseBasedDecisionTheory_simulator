package cbdt.model.persistence;

import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.Parameters;
import cbdt.model.ParametersFactory;

import com.thoughtworks.xstream.XStream;

public class ParametersPersistenceManager implements IParametersPersistenceManager {

	public static final String PARAMETERS_NODE_NAME = "parameters";
	public static final String ACTOR_ACTION_OUTCOME_NODE_NAME = "actionOutcome";
	public static final String ACTOR_ACTION_NODE_NAME = "actorAction";
	
	private XStream xStream;
	
	public ParametersPersistenceManager() {
		xStream = new XStream();
		
		xStream.registerConverter(new ParametersConverter());
		xStream.alias(PARAMETERS_NODE_NAME, Parameters.class);
		xStream.registerConverter(new ActorActionConverter());
		xStream.alias(ACTOR_ACTION_NODE_NAME, ActorAction.class);		
		xStream.registerConverter(new ActorActionOutcomeConverter());
		xStream.alias(ACTOR_ACTION_OUTCOME_NODE_NAME, ActorActionOutcome.class);
	}
	
	@Override
	public Parameters getParametersFromFile(String filepath){
		ParametersFactory factory = new ParametersFactory();
		return factory.getDefaultParameters();
	}
	
	@Override
	public void saveParametersToFile(String filepath, Parameters parameters){
		System.out.println(xStream.toXML(parameters));
	}
	
	public String convertToXML(Parameters params){
		return xStream.toXML(params);
	}
	
	public Parameters parseXML(String parametersXML){
		return (Parameters)xStream.fromXML(parametersXML);
	}
}
