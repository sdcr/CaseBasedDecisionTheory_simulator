package cbdt.control.validators;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

/**
 * This class allows to check whether a {@link Parameters} object is valid.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ParameterValidator {

	public void checkValidity(Parameters parameters)
			throws InvalidActorActionException {
		if (parameters.getActorActions() == null
				|| parameters.getActorActions().isEmpty())
			throw new InvalidActorActionException(
					"There must be at least one actor action defined.");
		for (ActorAction action : parameters.getActorActions()) {
			if (!hasValidProbabilityDistribution(action)) {
				throw new InvalidActorActionException(
						"The probability distribution of the ActorAction "
								+ action + " is invalid.");
			}
		}
	}

	/**
	 * @return Returns whether the probabilities of the ActorActionOutcomes
	 *         associated with this ActorAction form a valid probability
	 *         distribution.
	 * 
	 *         Validity is assumed if the sum of the probabilities equals one.
	 */
	public boolean hasValidProbabilityDistribution(ActorAction action) {
		double totalProbability = 0;

		for (ActorActionOutcome pair : action.getActionOutcomes()) {
			totalProbability += pair.getProbability();
		}

		return totalProbability == 1;
	}
}
