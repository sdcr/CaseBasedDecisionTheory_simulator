package cbdt.control;

import java.io.FileNotFoundException;

import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.ParametersFactory;
import cbdt.model.persistence.IParametersPersistenceManager;
import cbdt.model.persistence.ParametersPersistenceManager;
import cbdt.view.parameters.ParametersPageWrapper;

public class ParametersController extends AbstractPageController {

	private Parameters parametersModel;
	private ParametersPageWrapper parametersPageWrapper;
	private IParametersPersistenceManager parametersPersistenceManager;

	public ParametersController() {
		ParametersFactory factory = new ParametersFactory();
		parametersModel = factory.getDefaultParameters();
		parametersPageWrapper = new ParametersPageWrapper(this);
		parametersPersistenceManager = new ParametersPersistenceManager();
	}
	
	@Override
	public ParametersPageWrapper getPageWrapper(){
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
		try {
			parametersModel = parametersPersistenceManager.getParametersFromFile(filepath);
			goToForeground();
			parametersPageWrapper.getParametersPage().setParametersModel(parametersModel);
		} catch (FileNotFoundException e) {
			getMessageBoxManager().showErrorMessage("The stated file could not be found.");
		}
	}

	public void saveParametersToFile(String filepath) {
		goToForeground();
		try {
			parametersPersistenceManager.saveParametersToFile(filepath, parametersModel);
		} catch (Exception e) {
			getMessageBoxManager().showErrorMessage("An error occured while saving the parameters.");
			e.printStackTrace();
		}
	}
	
	public void startComputation(){
		getMainController().computeCDBTSimulation(parametersModel);
	}
}
