package cbdt.control.validators;

import cbdt.model.parameters.ActorAction;

/**
 * An exception reporting that an {@link ActorAction} is not valid.
 * 
 * @author Stephan da Costa Ribeiro
 *
 */
public class InvalidActorActionException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidActorActionException(String message) {
		super(message);
	}
	
}
