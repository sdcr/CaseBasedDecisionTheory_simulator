package cbdt.model.persistence;

import com.thoughtworks.xstream.XStream;

import cbdt.model.Parameters;

public class ParametersPersistenceManager implements IParametersPersistenceManger {

	@Override
	public Parameters getParametersFromFile(String filepath){
		return null;
	}
	
	@Override
	public void saveParametersToFile(String filepath, Parameters parameters){
		XStream xs = new XStream();
		String xml = xs.toXML(parameters);
		System.out.println(xml);
	}
}
