package cbdt.control.simulation.algorithm.dfsmatrix;

import cbdt.control.simulation.algorithm.INodeContent;


public class NodeContent implements INodeContent {
	
	public double probabilityProduct;
	
	public int[] numberOfOccurances;
	
	public double[] sumOfUtilities;
	
	public double aspirationLevel;
	
	public double getProbabilityProduct() {
		return probabilityProduct;
	}

	public void setProbabilityProduct(double probabilityProduct) {
		this.probabilityProduct = probabilityProduct;
	}

	public int[] getNumberOfOccurances() {
		return numberOfOccurances;
	}

	public void setNumberOfOccurances(int[] numberOfOccurances) {
		this.numberOfOccurances = numberOfOccurances;
	}

	public double[] getSumOfUtilities() {
		return sumOfUtilities;
	}

	public void setSumOfUtilities(double[] sumOfUtilities) {
		this.sumOfUtilities = sumOfUtilities;
	}

	public double getAspirationLevel() {
		return aspirationLevel;
	}

	public void setAspirationLevel(double aspirationLevel) {
		this.aspirationLevel = aspirationLevel;
	}

}
