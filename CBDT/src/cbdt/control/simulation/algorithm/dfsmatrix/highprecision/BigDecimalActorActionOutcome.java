package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.model.parameters.ActorActionOutcome;

/**
 * A subclass of {@link ActorActionOutcome} suited for computation with
 * {@link BigDecimal}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class BigDecimalActorActionOutcome extends ActorActionOutcome {

	BigDecimal probabilty;

	BigDecimal utility;

	/**
	 * Constructor.
	 * 
	 * @param probability
	 * @param utility
	 */
	public BigDecimalActorActionOutcome(BigDecimal probability,
			BigDecimal utility) {
		super(probability.doubleValue(), utility.doubleValue());
		probabilty = probability;
		this.utility = utility;
	}

}
