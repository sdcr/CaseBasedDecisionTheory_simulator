package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

/**
 * This abstract class allows to produce initial objects for the
 * {@link SimulationState}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public abstract class AbstractInitFactory {

	protected Parameters parameters;
	protected CommonConfig commonConfig;

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 * @param commonConfig
	 */
	public AbstractInitFactory(Parameters parameters, CommonConfig commonConfig) {
		this.parameters = parameters;
		this.commonConfig = commonConfig;
	}

	public abstract IAlgoNodeContent[][] getInitialContentsMatrix();

	public abstract Object[] getInitExpectedUtilities();

	public abstract Object[] getInitLowestAspirationLevels();

	public abstract ActorActionOutcome[][] getOutcomeMatrix();

	/**
	 * A {@link BigDecimal} matrix which can be used to count the absolute
	 * action occurrences.
	 * 
	 * @return
	 */
	public BigDecimal[][] getInitialActionOccurrences() {
		int numberOfActorActions = parameters.getActorActions().size();
		BigDecimal[][] absoluteActionOccurances = new BigDecimal[commonConfig
				.getNumberOfRequestedExpectedUtilityValues() + 1][numberOfActorActions];
		for (int i = 0; i < absoluteActionOccurances.length; i++) {
			for (int j = 0; j < numberOfActorActions; j++) {
				absoluteActionOccurances[i][j] = new BigDecimal(0);
			}
		}
		return absoluteActionOccurances;
	}

	/**
	 * @return The number of outcomes over all {@link ActorAction}s.
	 */
	protected int getNumberOfOutcomes() {
		int numOfOutcomes = 0;
		for (ActorAction action : parameters.getActorActions()) {
			numOfOutcomes += action.getActionOutcomes().size();
		}
		return numOfOutcomes;
	}

}
