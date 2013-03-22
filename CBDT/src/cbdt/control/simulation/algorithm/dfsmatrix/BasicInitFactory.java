package cbdt.control.simulation.algorithm.dfsmatrix;

import java.util.List;

import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public class BasicInitFactory extends AbstractInitFactory {

	public BasicInitFactory(Parameters parameters, AbstractEngineConfiguration config) {
		super(parameters, config);
	}

	@Override
	public ActorActionOutcome[][] getOutcomeMatrix() {
		ActorActionOutcome[][] outcomeMatrix = new ActorActionOutcome[parameters
				.getActorActions().size()][];
		for (int i = 0; i < outcomeMatrix.length; i++) {
			List<ActorActionOutcome> actionOutcomes = parameters
					.getActorActions().get(i).getActionOutcomes();
			int numberOfOutcomes = actionOutcomes.size();
			outcomeMatrix[i] = new ActorActionOutcome[numberOfOutcomes];
			for (int j = 0; j < numberOfOutcomes; j++) {
				outcomeMatrix[i][j] = actionOutcomes.get(j);
			}
		}

		return outcomeMatrix;
	}

	@Override
	public NodeContent[][] getInitialContentsMatrix() {
		int numberOfOutcomes = getNumberOfOutcomes();
		NodeContent[][] contentsMatrix = new NodeContent[config
				.getNumberOfRequestedExpectedUtilityValues() + 1][numberOfOutcomes];
		for (int i = 0; i < contentsMatrix.length; i++) {
			for (int j = 0; j < contentsMatrix[i].length; j++) {
				contentsMatrix[i][j] = new NodeContent();
				contentsMatrix[i][j].numberOfOccurances = getInitContentsNumberOfOccurences();
				contentsMatrix[i][j].sumOfUtilities = getInitContentsSumOfUtilities();
			}
		}

		// root element in contentsMatrix
		NodeContent rootContent = contentsMatrix[0][0];
		rootContent.aspirationLevel = parameters.getInitialAspirationLevel();
		rootContent.probabilityProduct = 1.0;
		return contentsMatrix;
	}

	private int[] getInitContentsNumberOfOccurences() {
		int[] numberOfOccurances = new int[parameters.getActorActions().size()];
		for (int i = 0; i < parameters.getActorActions().size(); i++) {
			numberOfOccurances[i] = 0;
		}
		return numberOfOccurances;
	}

	private double[] getInitContentsSumOfUtilities() {
		double[] sumOfUtilities = new double[parameters.getActorActions()
				.size()];
		for (int i = 0; i < parameters.getActorActions().size(); i++) {
			sumOfUtilities[i] = 0.0;
		}
		return sumOfUtilities;
	}

	@Override
	public Double[] getInitExpectedUtilities() {
		Double[] expectedUtilities = new Double[config
				.getNumberOfRequestedExpectedUtilityValues()];
		for (int i = 0; i < expectedUtilities.length; i++)
			expectedUtilities[i] = (double) 0;
		return expectedUtilities;
	}

}
