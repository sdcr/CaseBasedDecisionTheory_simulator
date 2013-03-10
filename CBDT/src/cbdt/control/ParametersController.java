package cbdt.control;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;
import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.Parameters;
import cbdt.model.ParametersFactory;
import cbdt.model.persistence.IParametersPersistenceManager;
import cbdt.model.persistence.ParametersPersistenceManager;
import cbdt.view.parameters.ParametersPageWrapper;

public class ParametersController implements IPageController {

	private Parameters parametersModel;
	private ParametersPageWrapper parametersPageWrapper;
	private IForegroundManager foregroundManager;
	private IParametersPersistenceManager parametersPersistenceManager;

	public ParametersController(IForegroundManager foregroundManager) {
		this.foregroundManager = foregroundManager;
		ParametersFactory factory = new ParametersFactory();
		parametersModel = factory.getDefaultParameters();
		parametersPageWrapper = new ParametersPageWrapper(this);
		parametersPersistenceManager = new ParametersPersistenceManager();
	}
	
	@Override
	public AbstractPluginPageCompositeWrapper getPageWrapper(){
		return parametersPageWrapper;
	}
	
	public ActorAction addDefaultActorActionToModel(){
		ParametersFactory factory = new ParametersFactory();
		ActorAction defaultActorAction = factory.getDefaultActorAction();
		parametersModel.addActorAction(defaultActorAction);
		return defaultActorAction;
	}
	
	public void removeActorActionFromModel(ActorAction actorAction){
		parametersModel.removeActorAction(actorAction);
	}
	
	public Parameters getParametersModel(){
		return parametersModel;
	}

	public ActorActionOutcome addDefaultActorActionOutcomeToModel(ActorAction actorAction){
		ParametersFactory factory = new ParametersFactory();
		ActorActionOutcome defaultOutcome = factory.getDefaultActorActionOutcome(); 
		actorAction.addActionOutcome(defaultOutcome);
		return defaultOutcome;
	}
	
	public void removeActorActionOutcomeFromModel(ActorActionOutcome outcome){
		ActorAction actorAction = outcome.getAction();
		actorAction.removeActionOutcome(outcome);
	}
	
	public void setActorActionName(ActorAction actorAction, String newName){
		actorAction.setActionName(newName);
	}
	
	public void setInitialAspirationLevel(Double newInitAspirationLevel) {
		parametersModel.setInitialAspirationLevel(newInitAspirationLevel);
	}
	
	public void setAspirationLevelIncrement(Double newAspirationLevelIncrement) {
		parametersModel.setAspirationLevelIncrement(newAspirationLevelIncrement);
	}
	
	public void setAspirationDiscountFactor(Double newAspirationLevelDiscountFactor) {
		parametersModel.setWeightingFactorAlpha(newAspirationLevelDiscountFactor);
	}

	public void loadParametersFromFile(String filepath) {
		parametersModel = parametersPersistenceManager.getParametersFromFile(filepath);
		foregroundManager.setToForeground(parametersPageWrapper);
		parametersPageWrapper.getParametersPage().setParametersModel(parametersModel);
	}

	public void saveParametersToFile(String filepath) {
		parametersPersistenceManager.saveParametersToFile(filepath, parametersModel);
		foregroundManager.setToForeground(parametersPageWrapper);
	}
	
}
