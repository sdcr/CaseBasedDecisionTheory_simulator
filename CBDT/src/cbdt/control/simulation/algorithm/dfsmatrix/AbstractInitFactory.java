package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.INodeContent;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;

public abstract class AbstractInitFactory {

	protected Parameters parameters;
	protected CommonConfig commonConfig;
	
	public AbstractInitFactory(Parameters parameters, CommonConfig commonConfig) {
		this.parameters = parameters;
		this.commonConfig = commonConfig;
	}
	
	public abstract INodeContent[][] getInitialContentsMatrix();
	
	public abstract Object[] getInitExpectedUtilities();

	public abstract Object[] getInitLowestAspirationLevels();
	
//	public abstract Object[][] getInitialRelativeActionOccurances();
	
	public abstract ActorActionOutcome[][] getOutcomeMatrix();
	
	public BigDecimal[][] getInitialActionOccurances() {
		int numberOfActorActions = parameters.getActorActions().size();
		BigDecimal[][] absoluteActionOccurances = new BigDecimal[commonConfig.getNumberOfRequestedExpectedUtilityValues()+1][numberOfActorActions];
		for(int i=0; i<absoluteActionOccurances.length; i++){
			for (int j=0; j<numberOfActorActions; j++) {
				absoluteActionOccurances[i][j] = new BigDecimal(0);
			}
		}
		return absoluteActionOccurances;
	}
		
	protected int getNumberOfOutcomes() {
		int numOfOutcomes = 0;
		int maxNumberOfOutcomes = 0;
		for (ActorAction action : parameters.getActorActions()) {
			maxNumberOfOutcomes = Math.max(action.getActionOutcomes().size(),
					maxNumberOfOutcomes);
			numOfOutcomes += action.getActionOutcomes().size();
		}
		return numOfOutcomes;
	}
	
}
