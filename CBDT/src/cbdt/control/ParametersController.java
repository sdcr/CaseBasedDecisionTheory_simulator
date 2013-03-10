package cbdt.control;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;
import simulation.extensionpoint.simulationplugin.resources.IForegroundManager;
import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.Parameters;
import cbdt.model.persistence.IParametersPersistenceManger;
import cbdt.model.persistence.ParametersPersistenceManager;
import cbdt.view.parameters.ParametersPageFactory;

public class ParametersController implements IPageController {

	private Parameters parametersModel;
	private ParametersPageFactory parametersPageWrapper;
	private IForegroundManager foregroundManager;
	private IParametersPersistenceManger parametersPersistenceManager;

	public ParametersController(IForegroundManager foregroundManager) {
		this.foregroundManager = foregroundManager;
		parametersModel = new Parameters();
		parametersPageWrapper = new ParametersPageFactory(this);
		parametersPersistenceManager = new ParametersPersistenceManager();
	}
	
	@Override
	public AbstractPluginPageCompositeWrapper getPageWrapper(){
		return parametersPageWrapper;
	}
	
	public ActorAction addDefaultActorActionToModel(){
		ActorAction defaultActorAction = new ActorAction("");
		ActorActionOutcome defaultActorActionOutcome = createDefaultActorActionOutcome();
		defaultActorAction.addActionOutcome(defaultActorActionOutcome);
		parametersModel.addActorAction(defaultActorAction);//getActorActions().add(defaultActorAction);
		return defaultActorAction;
	}
	
	public void removeActorActionFromModel(ActorAction actorAction){
		parametersModel.removeActorAction(actorAction);//getActorActions().remove(actorAction);
	}
	
	public Parameters getParametersModel(){
		return parametersModel;
	}

	public ActorActionOutcome addDefaultActorActionOutcomeToModel(ActorAction actorAction){
		ActorActionOutcome defaultOutcome = createDefaultActorActionOutcome(); 
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
	
	private ActorActionOutcome createDefaultActorActionOutcome(){
		return new ActorActionOutcome(0, 0);
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

	public void getParametersFromFile(String filepath) {
		parametersModel = parametersPersistenceManager.getParametersFromFile(filepath);
		foregroundManager.setToForeground(parametersPageWrapper);
		//TODO update the view
	}

	public void saveParametersToFile(String filepath) {
		parametersPersistenceManager.saveParametersToFile(filepath, parametersModel);
	}
	
}
