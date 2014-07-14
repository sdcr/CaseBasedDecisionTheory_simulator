package cbdt.model.result;

import java.math.BigDecimal;
import java.util.Map;

import cbdt.model.parameters.ActorAction;

/**
 * This class i similar to {@link StageResult}. Its main difference is that
 * {@link BigDecimal} objects are used where integer values are used in
 * {@link StageResult}.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class BigDecimalStageResult extends StageResult {

	private BigDecimal expectedBigDecimalUtility;

	private Map<ActorAction, BigDecimal> relativeBigDecimalActionOccurances;

	private BigDecimal lowestBigDecimalAspirationLevel;

	/**
	 * See {@link StageResult#getRelativeActionOccurances()}
	 * 
	 * @return
	 */
	public Map<ActorAction, BigDecimal> getRelativeBigDecimalActionOccurances() {
		return relativeBigDecimalActionOccurances;
	}

	/**
	 * See {@link StageResult#setRelativeActionOccurances(Map)}
	 * 
	 * @param relativeBigDecimalActionOccurances
	 */
	public void setRelativeBigDecimalActionOccurances(
			Map<ActorAction, BigDecimal> relativeBigDecimalActionOccurances) {
		this.relativeBigDecimalActionOccurances = relativeBigDecimalActionOccurances;
	}

	/**
	 * See {@link StageResult#getExpectedUtility()}
	 * 
	 * @return
	 */
	public BigDecimal getExpectedBigDecimalUtility() {
		return expectedBigDecimalUtility;
	}

	/**
	 * See {@link StageResult#setExpectedUtility(double)}
	 * 
	 * @param expectedBigDecimalUtility
	 */
	public void setExpectedBigDecimalUtility(
			BigDecimal expectedBigDecimalUtility) {
		this.expectedBigDecimalUtility = expectedBigDecimalUtility;
	}

	/**
	 * See {@link StageResult#getLowestAspirationLevel()}
	 * 
	 * @return
	 */
	public BigDecimal getLowestBigDecimalAspirationLevel() {
		return lowestBigDecimalAspirationLevel;
	}

	/**
	 * See {@link StageResult#setLowestAspirationLevel(double)}
	 * 
	 * @param lowestBigDecimalAspirationLevel
	 */
	public void setLowestBigDecimalAspirationLevel(
			BigDecimal lowestBigDecimalAspirationLevel) {
		this.lowestBigDecimalAspirationLevel = lowestBigDecimalAspirationLevel;
	}

}
