package cbdt.control.simulation.algorithm.dfsmatrix;

import java.util.List;

import cbdt.model.config.common.CommonConfig;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

/**
 * This class produces initial objects for the {@link SimulationState}, with
 * {@link Integer} or {@link Double} values.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class IntDoubleInitFactory extends AbstractInitFactory {

	/**
	 * Constructor.
	 * 
	 * @param parameters
	 * @param commonConfig
	 */
	public IntDoubleInitFactory(Parameters parameters, CommonConfig commonConfig) {
		super(parameters, commonConfig);
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
	public MatrixNodeContent[][] getInitialContentsMatrix() {
		int numberOfOutcomes = getNumberOfOutcomes();
		MatrixNodeContent[][] contentsMatrix = new MatrixNodeContent[commonConfig
				.getNumberOfRequestedExpectedUtilityValues() + 1][numberOfOutcomes];
		for (int i = 0; i < contentsMatrix.length; i++) {
			for (int j = 0; j < contentsMatrix[i].length; j++) {
				contentsMatrix[i][j] = new MatrixNodeContent();
				contentsMatrix[i][j].numberOfOccurances = getInitContentsNumberOfOccurences();
				contentsMatrix[i][j].sumOfUtilities = getInitContentsSumOfUtilities();
			}
		}

		// root element in contentsMatrix
		MatrixNodeContent rootContent = contentsMatrix[0][0];
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
		Double[] expectedUtilities = new Double[commonConfig
				.getNumberOfRequestedExpectedUtilityValues() + 1];
		for (int i = 0; i < expectedUtilities.length; i++)
			expectedUtilities[i] = (double) 0;
		return expectedUtilities;
	}

	@Override
	public Double[] getInitLowestAspirationLevels() {
		Double[] lowestAspirationLevels = new Double[commonConfig
				.getNumberOfRequestedExpectedUtilityValues() + 1];
		lowestAspirationLevels[0] = parameters.getInitialAspirationLevel();
		for (int i = 1; i < lowestAspirationLevels.length; i++)
			lowestAspirationLevels[i] = Double.MAX_VALUE;
		return lowestAspirationLevels;
	}

	/**
	 * @return an initial matrix of {@link Double} objects to compute the
	 *         relative action occurrences.
	 */
	public Double[][] getInitialRelativeActionOccurances() {
		int numberOfActorActions = parameters.getActorActions().size();
		Double[][] relativeActionOccurances = new Double[commonConfig
				.getNumberOfRequestedExpectedUtilityValues() + 1][numberOfActorActions];
		for (int i = 0; i < relativeActionOccurances.length; i++) {
			for (int j = 0; j < numberOfActorActions; j++) {
				relativeActionOccurances[i][j] = new Double(0);
			}
		}
		return relativeActionOccurances;
	}

	/**
	 * @return an initial {@link SimulationState} object.
	 */
	public SimulationState getInitSimulationState() {
		SimulationState simState = new SimulationState();
		simState.absoluteActionOccurances = this.getInitialActionOccurrences();
		simState.relativeActionOccurances = this
				.getInitialRelativeActionOccurances();
		simState.expectedUtilities = this.getInitExpectedUtilities();
		simState.lowestAspirationLevels = this.getInitLowestAspirationLevels();
		simState.contentsMatrix = this.getInitialContentsMatrix();
		return simState;
	}
}
