package cbdt.model.result;

import java.math.BigDecimal;
import java.util.Map;

import cbdt.model.parameters.ActorAction;

public class StageResult {

	private int stage;
	
	private double expectedUtility;
	
	private Map<ActorAction, BigDecimal> absoluteActionOccurances;
	
	private Map<ActorAction, Double> relativeActionOccurances;

	private double lowestAspirationLevel;
	
	public double getExpectedUtility() {
		return expectedUtility;
	}

	public void setExpectedUtility(double expectedUtility) {
		this.expectedUtility = expectedUtility;
	}

	public Map<ActorAction, BigDecimal> getAbsoluteActionOccurances() {
		return absoluteActionOccurances;
	}

	public void setAbsoluteActionOccurances(Map<ActorAction, BigDecimal> absoluteActionOccurances) {
		this.absoluteActionOccurances = absoluteActionOccurances;
	}

	public Map<ActorAction, Double> getRelativeActionOccurances() {
		return relativeActionOccurances;
	}

	public void setRelativeActionOccurances(Map<ActorAction, Double> relativeActionOccurances) {
		this.relativeActionOccurances = relativeActionOccurances;
	}

	public int getStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public double getLowestAspirationLevel() {
		return lowestAspirationLevel;
	}

	public void setLowestAspirationLevel(double lowestAspirationLevel) {
		this.lowestAspirationLevel = lowestAspirationLevel;
	}
}
