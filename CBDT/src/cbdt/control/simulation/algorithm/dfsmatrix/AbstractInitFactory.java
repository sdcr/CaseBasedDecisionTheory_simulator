package cbdt.control.simulation.algorithm.dfsmatrix;

import java.math.BigDecimal;

import cbdt.control.simulation.algorithm.INodeContent;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public abstract class AbstractInitFactory {

	protected Parameters parameters;
	protected AbstractEngineConfiguration config;
	
	public AbstractInitFactory(Parameters parameters, AbstractEngineConfiguration config) {
		this.parameters = parameters;
		this.config = config;
	}
	
	public abstract INodeContent[][] getInitialContentsMatrix();
	
	public abstract Object[] getInitExpectedUtilities();
	
	public abstract ActorActionOutcome[][] getOutcomeMatrix();
	
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
