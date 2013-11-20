package cbdt.control.persistence.parameters;

import cbdt.model.parameters.ActorActionOutcome;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

//GREEN
/**
 * Converts ActorActionOutcomes to and from XML.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ActorActionOutcomeConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class arg0) {
		return arg0.equals(ActorActionOutcome.class);
	}

	@Override
	public void marshal(Object arg0, HierarchicalStreamWriter writer,
			MarshallingContext context) {
		ActorActionOutcome actorActionOutcome = (ActorActionOutcome) arg0;
		writer.startNode("probability");
		context.convertAnother(actorActionOutcome.getProbability());
		writer.endNode();

		writer.startNode("utility");
		context.convertAnother(actorActionOutcome.getUtility());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader,
			UnmarshallingContext context) {

		ActorActionOutcome actorActionOutcome = new ActorActionOutcome(0, 0);
		reader.moveDown();
		actorActionOutcome
				.setProbability(Double.parseDouble(reader.getValue()));
		reader.moveUp();

		reader.moveDown();
		actorActionOutcome.setUtility(Double.parseDouble(reader.getValue()));
		reader.moveUp();

		return actorActionOutcome;
	}

}
