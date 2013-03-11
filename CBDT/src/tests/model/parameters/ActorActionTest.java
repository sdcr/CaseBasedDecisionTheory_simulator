package tests.model.parameters;

import static org.junit.Assert.*;

import org.junit.Test;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;

public class ActorActionTest {

	@Test
	public void testEquality(){
		ActorAction a = new ActorAction("A");
		ActorAction b = new ActorAction("A");
		assertTrue(a.equals(b));
		
		ActorActionOutcome o1 = new ActorActionOutcome(1, 0);
		ActorActionOutcome o2 = new ActorActionOutcome(1, 0);
		assertTrue(o1.equals(o2));
		
		a.addActionOutcome(o1);
		b.addActionOutcome(o2);
		assertTrue(a.equals(b));
		
		assertFalse(a==b);
	}
}
