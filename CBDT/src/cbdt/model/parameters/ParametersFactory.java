package cbdt.model.parameters;

//GREEN
/**
 * This factory class is able to produce default objects for the Parameters
 * model.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ParametersFactory {

	/**
	 * @return A Parameters object with one default ActorAction object and:<br>
	 *         aspiration level increment: 10<br>
	 *         initial aspiration level: 100<br>
	 *         weighting factor alpha: 0.5
	 */
	public Parameters getDefaultParameters() {
		Parameters defaultParams = new Parameters();
		defaultParams.setAspirationLevelIncrement(10);
		defaultParams.setInitialAspirationLevel(100);
		defaultParams.setWeightingFactorAlpha(0.5);
		defaultParams.addActorAction(getDefaultActorAction());
		return defaultParams;
	}

	/**
	 * @return An ActorAction object with an ActorActionOutcome which has
	 *         probability 1 and utility 0.
	 */
	public ActorAction getDefaultActorAction() {
		ActorAction defaultAction = new ActorAction("");
		ActorActionOutcome firstOutcome = new ActorActionOutcome(1, 0);
		defaultAction.addActionOutcome(firstOutcome);
		return defaultAction;
	}

	/**
	 * @return An ActorActionOutcome object with probability 0 and utility 0.
	 */
	public ActorActionOutcome getDefaultActorActionOutcome() {
		ActorActionOutcome defaultOutcome = new ActorActionOutcome(0, 0);
		return defaultOutcome;
	}

}
