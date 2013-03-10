package tests.model.persistence;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.Parameters;
import cbdt.model.persistence.ParametersPersistenceManager;

public class ParameterPersistenceManagerTest {

	private Parameters params;

	@Before
	public void setup(){
		params = new Parameters();
		params.setInitialAspirationLevel(123);
		
		ActorAction actorActionA = new ActorAction("ActionA");
		actorActionA.addActionOutcome(new ActorActionOutcome(0, 1));
		
		params.addActorAction(actorActionA);		
	}
	
	@Test
	public void testParameterPersistenceManager(){
		ParametersPersistenceManager persistenceManager = new ParametersPersistenceManager();
		
		String inXML = persistenceManager.convertToXML(params);
		System.out.println(inXML);
		Parameters parsedParams = persistenceManager.parseXML(inXML);
		
		assertEquals(parsedParams, params);
		
		persistenceManager.saveParametersToFile(null, params);
	}
}
