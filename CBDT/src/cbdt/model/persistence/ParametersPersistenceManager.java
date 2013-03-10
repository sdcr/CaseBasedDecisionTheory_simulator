package cbdt.model.persistence;

import cbdt.model.Parameters;
import cbdt.model.ParametersFactory;

import com.thoughtworks.xstream.XStream;

public class ParametersPersistenceManager implements IParametersPersistenceManager {

	XStream xs;
	
	@Override
	public Parameters getParametersFromFile(String filepath){
		ParametersFactory factory = new ParametersFactory();
		return factory.getDefaultParameters();
	}
	
	@Override
	public void saveParametersToFile(String filepath, Parameters parameters){
		if(xs==null)
			xs = new XStream();
		System.out.println(xs.toXML(new String("Blaaa")));
	}
}
