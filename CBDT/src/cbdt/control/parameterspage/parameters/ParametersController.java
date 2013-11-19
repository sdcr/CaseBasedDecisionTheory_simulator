package cbdt.control.parameterspage.parameters;

import java.io.FileNotFoundException;

import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.control.persistence.parameters.IParametersPersistenceManager;
import cbdt.control.persistence.parameters.ParametersPersistenceManager;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.ParametersFactory;

public class ParametersController {
	
	/**
	 * The parameters model as currently displayed in the view.
	 */
	private Parameters parameters;
	
	/**
	 * The persistence manager to store parameters.
	 */
	private IParametersPersistenceManager parametersPersistenceManager;
	
	private ParametersConfigPageController parametersConfigPageController;
	
	public ParametersController() {
		//create parameters persistence manager	
		parametersPersistenceManager = new ParametersPersistenceManager();

		//set parameters to default
		ParametersFactory parametersFactory = new ParametersFactory();
		parameters = parametersFactory.getDefaultParameters();
	}
	
	public Parameters getParametersModel() {
		return parameters;
	}
	
	public ParametersConfigPageController getParametersConfigPageController() {
		return parametersConfigPageController;
	}
	
	public ActorAction addDefaultActorActionToModel() {
		ParametersFactory factory = new ParametersFactory();
		ActorAction defaultActorAction = factory.getDefaultActorAction();
		parameters.addActorAction(defaultActorAction);
		return defaultActorAction;
	}

	public void removeActorActionFromModel(ActorAction actorAction) {
		parameters.removeActorAction(actorAction);
	}

	public ActorActionOutcome addDefaultActorActionOutcomeToModel(
			ActorAction actorAction) {
		ParametersFactory factory = new ParametersFactory();
		ActorActionOutcome defaultOutcome = factory
				.getDefaultActorActionOutcome();
		actorAction.addActionOutcome(defaultOutcome);
		parametersConfigPageController.getPageWrapper().getParametersPage().setFocus();
		return defaultOutcome;
	}

	public void removeActorActionOutcomeFromModel(ActorActionOutcome outcome) {
		ActorAction actorAction = outcome.getAction();
		actorAction.removeActionOutcome(outcome);
	}

	public void setActorActionName(ActorAction actorAction, String newName) {
		actorAction.setActionName(newName);
	}

	public void setInitialAspirationLevel(Double newInitAspirationLevel) {
		parameters.setInitialAspirationLevel(newInitAspirationLevel);
	}

	public void setAspirationLevelIncrement(Double newAspirationLevelIncrement) {
		parameters
				.setAspirationLevelIncrement(newAspirationLevelIncrement);
	}

	public void setAspirationDiscountFactor(
			Double newAspirationLevelDiscountFactor) {
		parameters
				.setWeightingFactorAlpha(newAspirationLevelDiscountFactor);
	}

	public void setUsingAspirationLevelIncrement(boolean selection) {
		parameters.setUsingAspirationLevelIncrement(selection);
	}
	
	public void loadParametersFromFile(String filepath) {
		try {
			parameters = parametersPersistenceManager
					.getParametersFromFile(filepath);
			parametersConfigPageController.goToForeground();
			parametersConfigPageController.getPageWrapper().getParametersPage().setParametersModel(
					parameters);
		} catch (FileNotFoundException e) {
			parametersConfigPageController.getMessageBoxManager().showErrorMessage(
					"The stated file could not be found.");
		} catch (RuntimeException e){
			parametersConfigPageController.getMessageBoxManager().showErrorMessage(
					"Loading parameters failed.");
		}
	}

	public void saveParametersToFile(String filepath) {
		parametersConfigPageController.goToForeground();
		try {
			parametersPersistenceManager.saveParametersToFile(filepath,
					parameters);
		} catch (Exception e) {
			parametersConfigPageController.getMessageBoxManager().showErrorMessage(
					"An error occured while saving the parameters.");
			e.printStackTrace();
		}
	}
}
