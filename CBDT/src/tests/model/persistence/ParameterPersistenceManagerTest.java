package tests.model.persistence;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.persistence.ParametersPersistenceManager;

public class ParameterPersistenceManagerTest {

	private Parameters params;
	private ParametersPersistenceManager persistenceManager;

	private String testfiles_folder = "testfiles/";
	private String filepath = "testfiles/params_file2.xml";

	@Before
	public void setup(){
		params = new Parameters();
		params.setInitialAspirationLevel(123);
		ActorAction actorActionA = new ActorAction("ActionA");
		actorActionA.addActionOutcome(new ActorActionOutcome(0, 1));
		params.addActorAction(actorActionA);		
		
		File folder = new File(testfiles_folder);
		folder.mkdirs();

		persistenceManager = new ParametersPersistenceManager();
	}
	
	@Test
	public void testParameterXMLConversion(){
		String inXML = persistenceManager.convertToXML(params);
		Parameters parsedParams = persistenceManager.parseXML(inXML);
		assertTrue(parsedParams.equals(params));
	}
	
	@Test
	public void testParameterSaveToFile(){		
		persistenceManager.saveParametersToFile(filepath, params);
		Parameters fromFile = null;
		try {
			fromFile = persistenceManager.getParametersFromFile(filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(params.equals(fromFile));
	}
	
	@Test
	public void testEquality(){
		ActorAction a = new ActorAction("A");
		ActorAction b = new ActorAction("A");
		System.out.println(a.equals(b));
		
		ActorActionOutcome o1 = new ActorActionOutcome(1, 0);
		ActorActionOutcome o2 = new ActorActionOutcome(1, 0);
		System.out.println(o1.equals(o2));
		
		a.addActionOutcome(o1);
		b.addActionOutcome(o2);
		System.out.println(a.equals(b));
		
		System.out.println(a.hashCode() + " " + b.hashCode());
		System.out.println(a==b);
	}
}
