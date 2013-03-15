package cbdt.control.algorithm.dfstree;

import java.util.HashMap;
import java.util.Map;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;

public class DFStreeSimulationEngine {

	public double[] computeExpectedUtilities(Parameters parameters){
		NodeContent rootContent = new NodeContent();
		rootContent.setProbabilityProduct(1);
		Map<ActorAction, Integer> numberOfOccurances = new HashMap<ActorAction, Integer>();
		Map<ActorAction, Double> sumOfUtilities = new HashMap<ActorAction, Double>();
		
		for(ActorAction action : parameters.getActorActions()){
			numberOfOccurances.put(action, 0);
			sumOfUtilities.put(action, 0.0);
		}
		rootContent.setNumberOfOccurances(numberOfOccurances);
		rootContent.setSumOfUtilities(sumOfUtilities);
		rootContent.setAspirationLevel(parameters.getInitialAspirationLevel());

		NodeShell rootShell = new NodeShell(rootContent);

		double[] expectedUtilities = new double[parameters.getNumberOfRequestedExpectedUtilities()];
		rootShell.computeChildren(parameters, expectedUtilities, 0);
		return expectedUtilities;
	}
}
