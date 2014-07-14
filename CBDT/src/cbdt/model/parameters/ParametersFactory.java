package cbdt.model.parameters;

/**
 * This factory class is able to produce default objects of the
 * {@link Parameters}, {@link ActorAction} and {@link ActorActionOutcome} model
 * classes.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ParametersFactory {

	/**
	 * @return A {@link Parameters} object with the following default settings:<br>
	 *         Aspiration level increment: 10<br>
	 *         Initial aspiration level: 100<br>
	 *         Aspiration level decrement factor: 0.5<br>
	 * <br>
	 *         In addition, one default {@link ActorAction} is added.
	 */
	public Parameters getDefaultParameters() {
		Parameters defaultParams = new Parameters();
		defaultParams.setAspirationLevelIncrement(10);
		defaultParams.setInitialAspirationLevel(100);
		defaultParams.setAspirationLevelDecrementFactor(0.5);
		defaultParams.addActorAction(getDefaultActorAction());
		return defaultParams;
	}

	/**
	 * @return An {@link ActorAction} object with one {@link ActorActionOutcome}
	 *         with probability 1 and utility 0.
	 */
	public ActorAction getDefaultActorAction() {
		ActorAction defaultAction = new ActorAction("");
		ActorActionOutcome firstOutcome = new ActorActionOutcome(1, 0);
		defaultAction.addActionOutcome(firstOutcome);
		return defaultAction;
	}

	/**
	 * @return An {@link ActorActionOutcome} object with probability 0 and
	 *         utility 0.
	 */
	public ActorActionOutcome getDefaultActorActionOutcome() {
		ActorActionOutcome defaultOutcome = new ActorActionOutcome(0, 0);
		return defaultOutcome;
	}

}
