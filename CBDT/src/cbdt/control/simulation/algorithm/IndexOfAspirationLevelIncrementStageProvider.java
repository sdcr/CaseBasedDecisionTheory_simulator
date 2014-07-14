package cbdt.control.simulation.algorithm;

import java.util.HashSet;
import java.util.Set;

/**
 * This class provides the indexes of the stages, in which the aspiration level
 * should be increased. The indexes are the potentials of 2: 1, 2, 4, 8, ...
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class IndexOfAspirationLevelIncrementStageProvider {

	private final int numberOfElementsToExtendSetWith = 6;

	private int highestIncreaseStageIndex = 1;

	private Set<Integer> increaseIndexSet;

	/**
	 * Constructor.
	 */
	public IndexOfAspirationLevelIncrementStageProvider() {
		increaseIndexSet = new HashSet<Integer>();
		extendIncreaseIndexSet();
	}

	/**
	 * Creates more indexes in which the aspiration level should be increased.
	 */
	private void extendIncreaseIndexSet() {
		for (int i = 0; i < numberOfElementsToExtendSetWith; i++) {
			highestIncreaseStageIndex *= 2;
			increaseIndexSet.add(highestIncreaseStageIndex);
		}
	}

	/**
	 * Returns whether in the passed index of the stage, the aspiration level
	 * should be increased.s
	 * 
	 * @param indexOfStage
	 * @return
	 */
	public boolean isStageToIncreaseAspirationLevel(int indexOfStage) {
		while (indexOfStage > highestIncreaseStageIndex)
			extendIncreaseIndexSet();
		return increaseIndexSet.contains(indexOfStage);
	}
}
