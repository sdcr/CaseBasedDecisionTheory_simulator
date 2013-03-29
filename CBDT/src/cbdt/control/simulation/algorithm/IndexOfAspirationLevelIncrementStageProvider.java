package cbdt.control.simulation.algorithm;

import java.util.HashSet;
import java.util.Set;

public class IndexOfAspirationLevelIncrementStageProvider {

	private final int numberOfElementsToExtendSetWith = 6;
	
	private int highestIncreaseStageIndex = 1;

	private Set<Integer> increaseIndexSet;
	
	public IndexOfAspirationLevelIncrementStageProvider() {
		increaseIndexSet = new HashSet<Integer>();
		extendIncreaseIndexSet();
	}

	private void extendIncreaseIndexSet() {
		for(int i=0; i<numberOfElementsToExtendSetWith; i++){
			highestIncreaseStageIndex *= 2;
			increaseIndexSet.add(highestIncreaseStageIndex);
		}
	}
	
	public boolean isStageToIncreaseAspirationLevel(int indexOfStage){
		while(indexOfStage>highestIncreaseStageIndex)
			extendIncreaseIndexSet();
		return increaseIndexSet.contains(indexOfStage);
	}
}
