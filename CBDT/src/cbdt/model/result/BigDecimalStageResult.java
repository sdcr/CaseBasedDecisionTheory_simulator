package cbdt.model.result;

import java.math.BigDecimal;
import java.util.Map;

import cbdt.model.parameters.ActorAction;

public class BigDecimalStageResult extends StageResult {

	private BigDecimal expectedBigDecimalUtility;
	
	private Map<ActorAction, BigDecimal> relativeBigDecimalActionOccurances;
	
	private BigDecimal lowestBigDecimalAspirationLevel;

	public Map<ActorAction, BigDecimal> getRelativeBigDecimalActionOccurances() {
		return relativeBigDecimalActionOccurances;
	}

	public void setRelativeBigDecimalActionOccurances(
			Map<ActorAction, BigDecimal> relativeBigDecimalActionOccurances) {
		this.relativeBigDecimalActionOccurances = relativeBigDecimalActionOccurances;
	}

	public BigDecimal getExpectedBigDecimalUtility() {
		return expectedBigDecimalUtility;
	}

	public void setExpectedBigDecimalUtility(BigDecimal expectedBigDecimalUtility) {
		this.expectedBigDecimalUtility = expectedBigDecimalUtility;
	}

	public BigDecimal getLowestBigDecimalAspirationLevel() {
		return lowestBigDecimalAspirationLevel;
	}

	public void setLowestBigDecimalAspirationLevel(
			BigDecimal lowestBigDecimalAspirationLevel) {
		this.lowestBigDecimalAspirationLevel = lowestBigDecimalAspirationLevel;
	}
	
}
