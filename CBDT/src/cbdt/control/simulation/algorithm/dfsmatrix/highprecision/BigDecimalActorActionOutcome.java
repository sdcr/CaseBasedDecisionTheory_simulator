package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;

import cbdt.model.parameters.ActorActionOutcome;

public class BigDecimalActorActionOutcome extends ActorActionOutcome {

	BigDecimal probabilty;
	
	BigDecimal utility;
	
	public BigDecimalActorActionOutcome(BigDecimal  probability, BigDecimal utility) {
		super(probability.doubleValue(), utility.doubleValue());
		probabilty = probability;
		this.utility = utility;
	}

	
}
