package cbdt.control.persistence.parameters;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

//GREEN
/**
 * Converts ActorAction objects to and from XML.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ActorActionConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class arg0) {
		return arg0.equals(ActorAction.class);
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		ActorAction actorAction = (ActorAction) arg0;
		writer.startNode("actionName");
		context.convertAnother(actorAction.getActionName());
		writer.endNode();

		writer.startNode("outcomes");
		context.convertAnother(actorAction.getActionOutcomes());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		ActorAction actorAction = new ActorAction("");
		reader.moveDown();
		actorAction.setActionName(reader.getValue());
		reader.moveUp();

		reader.moveDown();
		while (reader.hasMoreChildren()) {
			reader.moveDown();
			if (ParametersPersistenceManager.ACTOR_ACTION_OUTCOME_NODE_NAME
					.equals(reader.getNodeName())) {
				ActorActionOutcome actorActionOutcome = (ActorActionOutcome) context
						.convertAnother(actorAction, ActorActionOutcome.class);
				actorAction.addActionOutcome(actorActionOutcome);
			}
			reader.moveUp();
		}
		reader.moveUp();

		return actorAction;
	}

}
