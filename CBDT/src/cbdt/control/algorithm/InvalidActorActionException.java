package cbdt.control.algorithm;

import cbdt.model.parameters.ActorAction;

public class InvalidActorActionException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4712815090863685994L;
	
	private ActorAction actorAction;

	public InvalidActorActionException(ActorAction actorAction) {
		this.actorAction = actorAction;
	}
	
	@Override
	public String toString() {
		return super.toString() + "The probability distribution of the ActorAction "+actorAction+" is invalid.";
	}
}
