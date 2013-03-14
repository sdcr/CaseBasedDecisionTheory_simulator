package cbdt.model.parameters.persistence;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ParametersConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class arg0) {
		return arg0.equals(Parameters.class);
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		Parameters params = (Parameters)arg0;
		writer.startNode("initialAspirationLevel");
		context.convertAnother(params.getInitialAspirationLevel());
		writer.endNode();
		
		writer.startNode("aspirationLevelIncrement");
		context.convertAnother(params.getAspirationLevelIncrement());
		writer.endNode();

		writer.startNode("aspirationLevelDiscountFactor");
		context.convertAnother(params.getWeightingFactorAlpha());
		writer.endNode();
		
		writer.startNode("actions");
		context.convertAnother(params.getActorActions());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {
		
        Parameters parameters = new Parameters();
        reader.moveDown();
        parameters.setInitialAspirationLevel(Double.parseDouble(reader.getValue()));
        reader.moveUp();
        
        reader.moveDown();
        parameters.setAspirationLevelIncrement(Double.parseDouble(reader.getValue()));
        reader.moveUp();
        
        reader.moveDown();
        parameters.setWeightingFactorAlpha(Double.parseDouble(reader.getValue()));
        reader.moveUp();
        
        reader.moveDown();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            if (ParametersPersistenceManager.ACTOR_ACTION_NODE_NAME.equals(reader.getNodeName())) {
                    ActorAction actorAction = (ActorAction)context.convertAnother(parameters, ActorAction.class);
                    parameters.addActorAction(actorAction);
            }
            reader.moveUp();
        }
        reader.moveUp();
        
        return parameters;
	}

}
