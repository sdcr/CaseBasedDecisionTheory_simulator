package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;
import java.util.List;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public class InitFactory {

	private Parameters parameters;
	private AbstractEngineConfiguration config;

	public InitFactory(Parameters parameters, AbstractEngineConfiguration config) {
		this.parameters = parameters;
		this.config = config;
	}

	public ActorActionOutcome[][] getOutcomeMatrix() {
		ActorActionOutcome[][] outcomeMatrix = new ActorActionOutcome[parameters.getActorActions().size()][];
		for (int i = 0; i < outcomeMatrix.length; i++) {
			List<ActorActionOutcome> actionOutcomes = parameters.getActorActions().get(i).getActionOutcomes();
			int numberOfOutcomes = actionOutcomes.size();
			outcomeMatrix[i] = new ActorActionOutcome[numberOfOutcomes];
			for (int j = 0; j < numberOfOutcomes; j++) {
				outcomeMatrix[i][j] = actionOutcomes.get(j);
			}
		}
		
		return outcomeMatrix;
	}

	public NodeContent[][] getInitialContentsMatrix() {
		int numberOfOutcomes = getNumberOfOutcomes(parameters);
		NodeContent[][] contentsMatrix = new NodeContent[config.getNumberOfRequestedExpectedUtilityValues() + 1][numberOfOutcomes];		
		for (int i = 0; i < contentsMatrix.length; i++) {
			for (int j = 0; j < contentsMatrix[i].length; j++) {
				contentsMatrix[i][j] = new NodeContent();
				contentsMatrix[i][j].numberOfOccurances = getInitContentsNumberOfOccurences();
				contentsMatrix[i][j].sumOfUtilities = getInitContentsSumOfUtilities();
			}
		}
		
		//root element in contentsMatrix
		NodeContent rootContent = contentsMatrix[0][0];
		rootContent.aspirationLevel = parameters.getInitialAspirationLevel();
		rootContent.probabilityProduct = 1.0;
		return contentsMatrix;
	}
	
	private int getNumberOfOutcomes(Parameters parameters) {
		int numOfOutcomes = 0;
		int maxNumberOfOutcomes = 0;
		for(ActorAction action : parameters.getActorActions()){
			maxNumberOfOutcomes = Math.max(action.getActionOutcomes().size(), maxNumberOfOutcomes);
			numOfOutcomes += action.getActionOutcomes().size();
		}
		return numOfOutcomes;
	}
	
	
	private int[] getInitContentsNumberOfOccurences() {
		int[] numberOfOccurances = new int[parameters.getActorActions().size()];
		for(int i=0; i<parameters.getActorActions().size(); i++){
			numberOfOccurances[i] = 0;
		}
		return numberOfOccurances;
	}

	private double[] getInitContentsSumOfUtilities() {
		double[] sumOfUtilities = new double[parameters.getActorActions().size()];
		for(int i=0; i<parameters.getActorActions().size(); i++){
			sumOfUtilities[i] = 0.0;
		}
		return sumOfUtilities;
	}
	
	public BigDecimal[][] getInitialAbsoluteActionOccurances() {
		int numberOfActorActions = parameters.getActorActions().size();
		BigDecimal[][] absoluteActionOccurances = new BigDecimal[config.getNumberOfRequestedExpectedUtilityValues()][numberOfActorActions];
		for(int i=0; i<config.getNumberOfRequestedExpectedUtilityValues(); i++){
			for (int j=0; j<numberOfActorActions; j++) {
				absoluteActionOccurances[i][j] = new BigDecimal(0);
			}
		}
		return absoluteActionOccurances;
	}
	
	public double[] getInitExpectedUtilities() {
		double[] expectedUtilities = new double[config.getNumberOfRequestedExpectedUtilityValues()];
		for(int i=0; i< expectedUtilities.length; i++)
			expectedUtilities[i] = 0;
		return expectedUtilities;
	}

}
