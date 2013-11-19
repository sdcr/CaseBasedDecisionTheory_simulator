package cbdt.control.simulation.algorithm.dfsmatrix.highprecision;

import java.math.BigDecimal;
import java.util.List;

import cbdt.control.simulation.algorithm.INodeContent;
import cbdt.control.simulation.algorithm.dfsmatrix.AbstractInitFactory;
import cbdt.model.config.CommonConfig;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public class BigDecimalInitFactory extends AbstractInitFactory {

	public BigDecimalInitFactory(Parameters parameters,
			CommonConfig commonConfig) {
		super(parameters, commonConfig);
	}

	@Override
	public INodeContent[][] getInitialContentsMatrix() {
		int numberOfOutcomes = getNumberOfOutcomes();
		BigDecimalNodeContent[][] contentsMatrix = new BigDecimalNodeContent[commonConfig
				.getNumberOfRequestedExpectedUtilityValues() + 1][numberOfOutcomes];
		for (int i = 0; i < contentsMatrix.length; i++) {
			for (int j = 0; j < contentsMatrix[i].length; j++) {
				contentsMatrix[i][j] = new BigDecimalNodeContent();
				contentsMatrix[i][j].numberOfOccurances = getInitContentsNumberOfOccurences();
				contentsMatrix[i][j].sumOfUtilities = getInitContentsSumOfUtilities();
			}
		}

		// root element in contentsMatrix
		BigDecimalNodeContent rootContent = contentsMatrix[0][0];
		rootContent.aspirationLevel = new BigDecimal(
				parameters.getInitialAspirationLevel());
		rootContent.probabilityProduct = new BigDecimal(1.0);
		return contentsMatrix;
	}

	private BigDecimal[] getInitContentsNumberOfOccurences() {
		BigDecimal[] numberOfOccurances = new BigDecimal[parameters
				.getActorActions().size()];
		for (int i = 0; i < parameters.getActorActions().size(); i++) {
			numberOfOccurances[i] = new BigDecimal(0);
		}
		return numberOfOccurances;
	}

	private BigDecimal[] getInitContentsSumOfUtilities() {
		BigDecimal[] sumOfUtilities = new BigDecimal[parameters
				.getActorActions().size()];
		for (int i = 0; i < parameters.getActorActions().size(); i++) {
			sumOfUtilities[i] = new BigDecimal(0);
		}
		return sumOfUtilities;
	}

	@Override
	public Object[] getInitExpectedUtilities() {
		BigDecimal[] expectedUtilities = new BigDecimal[commonConfig
				.getNumberOfRequestedExpectedUtilityValues()+1];
		for (int i = 0; i < expectedUtilities.length; i++)
			expectedUtilities[i] = new BigDecimal(0);
		return expectedUtilities;
	}

	@Override
	public BigDecimal[] getInitLowestAspirationLevels() {
		BigDecimal[] lowestAspirationLevels = new BigDecimal[commonConfig
		                                                .getNumberOfRequestedExpectedUtilityValues()+1];
		lowestAspirationLevels[0] = new BigDecimal(parameters.getInitialAspirationLevel());
		for (int i = 1; i < lowestAspirationLevels.length; i++)
			lowestAspirationLevels[i] = new BigDecimal(Integer.MAX_VALUE);
		return lowestAspirationLevels;
	}
	
	@Override
	public ActorActionOutcome[][] getOutcomeMatrix() {
		BigDecimalActorActionOutcome[][] outcomeMatrix = new BigDecimalActorActionOutcome[parameters
				.getActorActions().size()][];
		for (int i = 0; i < outcomeMatrix.length; i++) {
			List<ActorActionOutcome> actionOutcomes = parameters
					.getActorActions().get(i).getActionOutcomes();
			int numberOfOutcomes = actionOutcomes.size();
			outcomeMatrix[i] = new BigDecimalActorActionOutcome[numberOfOutcomes];
			for (int j = 0; j < numberOfOutcomes; j++) {
				outcomeMatrix[i][j] = new BigDecimalActorActionOutcome(
						new BigDecimal(actionOutcomes.get(j).getProbability()),
						new BigDecimal(actionOutcomes.get(j).getUtility()));
			}
		}

		return outcomeMatrix;
	}

	public BigDecimalSimulationState getInitBigDecimalSimulationState(){
		BigDecimalSimulationState simState = new BigDecimalSimulationState();
		simState.absoluteActionOccurances = this.getInitialActionOccurances();
		simState.relativeActionOccurances = this.getInitialActionOccurances();
		simState.expectedUtilities = (BigDecimal[]) this.getInitExpectedUtilities();
		simState.lowestAspirationLevels = this.getInitLowestAspirationLevels();
		simState.contentsMatrix = (BigDecimalNodeContent[][]) this.getInitialContentsMatrix();
		return simState;
	}
	
//	@Override
//	public BigDecimal[][] getInitialRelativeActionOccurances() {
//		int numberOfActorActions = parameters.getActorActions().size();
//		BigDecimal[][] relativeActionOccurances = new BigDecimal[commonConfig.getNumberOfRequestedExpectedUtilityValues()][numberOfActorActions];
//		for(int i=0; i<commonConfig.getNumberOfRequestedExpectedUtilityValues(); i++){
//			for (int j=0; j<numberOfActorActions; j++) {
//				relativeActionOccurances[i][j] = new BigDecimal(0);
//			}
//		}
//		return relativeActionOccurances;
//	}

}
