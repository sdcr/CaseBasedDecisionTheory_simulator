package cbdt.control.simulation.algorithm.dfskeeptree;

import java.util.HashMap;
import java.util.Map;

import cbdt.control.simulation.SimulationAlgorithm;
import cbdt.control.simulation.algorithm.NodeShell;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.Parameters;

public class DFStreeSimulationAlgorithm implements SimulationAlgorithm {

	@Override
	public double[] computeExpectedUtilities(Parameters parameters){
		NodeContentMaps rootContent = new NodeContentMaps();
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
