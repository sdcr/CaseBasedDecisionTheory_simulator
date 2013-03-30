package cbdt.model.parameters;


public class ParametersFactory {

	public Parameters getDefaultParameters(){
		Parameters defaultParams = new Parameters();
		defaultParams.setAspirationLevelIncrement(10);
		defaultParams.setInitialAspirationLevel(100);
		defaultParams.setWeightingFactorAlpha(0.5);
		defaultParams.addActorAction(getDefaultActorAction());
		return defaultParams;
	}
	
	public ActorAction getDefaultActorAction(){
		ActorAction defaultAction = new ActorAction("");
		ActorActionOutcome firstOutcome = new ActorActionOutcome(1, 0);
		defaultAction.addActionOutcome(firstOutcome);
		return defaultAction;
	}
	
	public ActorActionOutcome getDefaultActorActionOutcome(){
		ActorActionOutcome defaultOutcome = new ActorActionOutcome(0, 0);
		return defaultOutcome;
	}
	
	
}
