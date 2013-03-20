package cbdt.control.pages;

import java.io.FileNotFoundException;

import cbdt.control.pages.engineconfig.EngineConfigControllerFactory;
import cbdt.control.persistence.parameters.IParametersPersistenceManager;
import cbdt.control.persistence.parameters.ParametersPersistenceManager;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.ParametersFactory;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
import cbdt.model.parameters.engineconfig.EngineConfigChoiceFactory;
import cbdt.view.parameters.ParametersPageReference;

public class ParametersPageController extends AbstractPageController {

	private Parameters parametersModel;
	private EngineConfigChoice configChoice;
	private ParametersPageReference parametersPageWrapper;
	private IParametersPersistenceManager parametersPersistenceManager;
	private EngineConfigControllerFactory configControllerFactory;

	public ParametersPageController() {
		ParametersFactory factory = new ParametersFactory();
		parametersModel = factory.getDefaultParameters();
		parametersPageWrapper = new ParametersPageReference(this);
		parametersPersistenceManager = new ParametersPersistenceManager();

		EngineConfigChoiceFactory configChoiceFactory = new EngineConfigChoiceFactory();
		configChoice = configChoiceFactory.getDefaultConfigChoice();

		configControllerFactory = new EngineConfigControllerFactory();
	}

	@Override
	public ParametersPageReference getPageWrapper() {
		return parametersPageWrapper;
	}

	public ActorAction addDefaultActorActionToModel() {
		ParametersFactory factory = new ParametersFactory();
		ActorAction defaultActorAction = factory.getDefaultActorAction();
		parametersModel.addActorAction(defaultActorAction);
		return defaultActorAction;
	}

	public void removeActorActionFromModel(ActorAction actorAction) {
		parametersModel.removeActorAction(actorAction);
	}

	public Parameters getParametersModel() {
		return parametersModel;
	}

	public ActorActionOutcome addDefaultActorActionOutcomeToModel(
			ActorAction actorAction) {
		ParametersFactory factory = new ParametersFactory();
		ActorActionOutcome defaultOutcome = factory
				.getDefaultActorActionOutcome();
		actorAction.addActionOutcome(defaultOutcome);
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
		parametersModel.setInitialAspirationLevel(newInitAspirationLevel);
	}

	public void setAspirationLevelIncrement(Double newAspirationLevelIncrement) {
		parametersModel
				.setAspirationLevelIncrement(newAspirationLevelIncrement);
	}

	public void setAspirationDiscountFactor(
			Double newAspirationLevelDiscountFactor) {
		parametersModel
				.setWeightingFactorAlpha(newAspirationLevelDiscountFactor);
	}

	public void loadParametersFromFile(String filepath) {
		try {
			parametersModel = parametersPersistenceManager
					.getParametersFromFile(filepath);
			goToForeground();
			parametersPageWrapper.getParametersPage().setParametersModel(
					parametersModel);
		} catch (FileNotFoundException e) {
			getMessageBoxManager().showErrorMessage(
					"The stated file could not be found.");
		}
	}

	public void saveParametersToFile(String filepath) {
		goToForeground();
		try {
			parametersPersistenceManager.saveParametersToFile(filepath,
					parametersModel);
		} catch (Exception e) {
			getMessageBoxManager().showErrorMessage(
					"An error occured while saving the parameters.");
			e.printStackTrace();
		}
	}

	public EngineConfigChoice getConfigChoiceModel() {
		return configChoice;
	}

	public EngineConfigControllerFactory getConfigControllerFactory() {
		return configControllerFactory;
	}

	public void startComputation() {
		getMainController().computeCDBTSimulation(parametersModel, configChoice.getCurrentlyChoosenConfig());
	}

	public void setChoosenConfig(AbstractEngineConfiguration config) {
		configChoice.setCurrentlyChoosenConfig(config);
	}

}
