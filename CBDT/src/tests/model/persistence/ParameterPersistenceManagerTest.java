package tests.model.persistence;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.persistence.ParametersPersistenceManager;

public class ParameterPersistenceManagerTest {

	private Parameters params;
	private ParametersPersistenceManager persistenceManager;

	private static String testfiles_folder = "testfiles/";
	private static String params_filepath = "testfiles/params_file.xml";

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
	
	@AfterClass
	public static void teardown(){
		File params_file = new File(params_filepath);
		params_file.delete();
		File folder = new File(testfiles_folder);
		folder.delete();
	}
	
	@Test
	public void testParameterXMLConversion(){
		String inXML = persistenceManager.convertToXML(params);
		Parameters parsedParams = persistenceManager.parseXML(inXML);
		assertTrue(parsedParams.equals(params));
	}
	
	@Test
	public void testParameterSaveToFile(){		
		persistenceManager.saveParametersToFile(params_filepath, params);
		File params_file = new File(params_filepath);
		assertTrue(params_file.exists());

		Parameters fromFile = null;
		try {
			fromFile = persistenceManager.getParametersFromFile(params_filepath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertTrue(params.equals(fromFile));
	}
	

}
