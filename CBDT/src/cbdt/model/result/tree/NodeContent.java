package cbdt.model.result.tree;

import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;

/**
 * This class models the values a node of the tree holds.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class NodeContent {

	/**
	 * The probability of this node on its stage.
	 */
	private double probabilityProduct;

	/**
	 * A map, mapping from {@link ActorAction}s to the number of how much it was
	 * selected up to this point in the computation.
	 */
	private Map<ActorAction, Integer> numberOfOccurances;

	/**
	 * A map, mapping from {@link ActorAction}s to the sum of utilities
	 * accumulated by selected the respective action.
	 */
	private Map<ActorAction, Double> sumOfUtilities;

	/**
	 * The aspiration level in this node.
	 */
	private double aspirationLevel;

	/**
	 * The {@link ActorActionOutcome} which directly lead to this node.
	 */
	private ActorActionOutcome lastOutcome;

	/**
	 * @return The probability of this node on its stage.
	 */
	public double getProbabilityProduct() {
		return probabilityProduct;
	}

	/**
	 * @param probabilityProduct
	 *            The probability of this node on its stage.
	 */
	public void setProbabilityProduct(double probabilityProduct) {
		this.probabilityProduct = probabilityProduct;
	}

	/**
	 * @return A map, mapping from {@link ActorAction}s to the number of how
	 *         much it was selected up to this point in the computation.
	 */
	public Map<ActorAction, Integer> getNumberOfOccurances() {
		return numberOfOccurances;
	}

	/**
	 * @param numberOfOccurances
	 *            A map, mapping from {@link ActorAction}s to the number of how
	 *            much it was selected up to this point in the computation.
	 */
	public void setNumberOfOccurances(
			Map<ActorAction, Integer> numberOfOccurances) {
		this.numberOfOccurances = numberOfOccurances;
	}

	/**
	 * @return A map, mapping from {@link ActorAction}s to the sum of utilities
	 *         accumulated by selected the respective action.
	 */
	public Map<ActorAction, Double> getSumOfUtilities() {
		return sumOfUtilities;
	}

	/**
	 * @param sumOfUtilities
	 *            A map, mapping from {@link ActorAction}s to the sum of
	 *            utilities accumulated by selected the respective action.
	 */
	public void setSumOfUtilities(Map<ActorAction, Double> sumOfUtilities) {
		this.sumOfUtilities = sumOfUtilities;
	}

	/**
	 * @return The aspiration level in this node.
	 */
	public double getAspirationLevel() {
		return aspirationLevel;
	}

	/**
	 * @param aspirationLevel
	 *            The aspiration level in this node.
	 */
	public void setAspirationLevel(double aspirationLevel) {
		this.aspirationLevel = aspirationLevel;
	}

	/**
	 * @return The {@link ActorActionOutcome} which directly lead to this node.
	 */
	public ActorActionOutcome getLastActionOutcome() {
		return lastOutcome;
	}

	/**
	 * @param lastOutcome
	 *            The {@link ActorActionOutcome} which directly lead to this
	 *            node.
	 */
	public void setLastActionOutcome(ActorActionOutcome lastOutcome) {
		this.lastOutcome = lastOutcome;
	}
}
