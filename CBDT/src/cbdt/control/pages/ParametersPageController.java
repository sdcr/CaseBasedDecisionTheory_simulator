package cbdt.control.pages;

import java.io.FileNotFoundException;

import cbdt.control.pages.engineconfig.EngineConfigControllerFactory;
import cbdt.control.persistence.parameters.IParametersPersistenceManager;
import cbdt.control.persistence.parameters.ParametersPersistenceManager;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.ParametersFactory;
import cbdt.model.parameters.engineconfig.AbstractEngineConfig;
import cbdt.model.parameters.engineconfig.SimulationConfig;
import cbdt.model.parameters.engineconfig.EngineConfigChoiceFactory;
import cbdt.view.parameters.ParametersPageWrapper;

public class ParametersPageController extends AbstractPageController {

	/* views */
	/**
	 * The wrapper of the associated parameters page.
	 */
	private ParametersPageWrapper parametersPageWrapper;

	/* models */
	/**
	 * The parameters model as currently displayed in the view.
	 */
	private Parameters parametersModel;
	/**
	 * The current choice of engine configuration.
	 */
	private SimulationConfig configChoice;

	/**
	 * The persistence manager to store parameters.
	 */
	private IParametersPersistenceManager parametersPersistenceManager;

	//TODO: wahts this?
	private EngineConfigControllerFactory configControllerFactory;

	/**
	 * Constructor.
	 * TODO' describe the filling of the model references.
	 */
	public ParametersPageController() {
		ParametersFactory factory = new ParametersFactory();
		parametersModel = factory.getDefaultParameters();
		parametersPageWrapper = new ParametersPageWrapper(this);
		parametersPersistenceManager = new ParametersPersistenceManager();

		EngineConfigChoiceFactory configChoiceFactory = new EngineConfigChoiceFactory();
		configChoice = configChoiceFactory.getDefaultConfigChoice();

		configControllerFactory = new EngineConfigControllerFactory();
	}

	@Override
	public ParametersPageWrapper getPageWrapper() {
		return parametersPageWrapper;
	}

	public Parameters getParametersModel() {
		return parametersModel;
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

	public ActorActionOutcome addDefaultActorActionOutcomeToModel(
			ActorAction actorAction) {
		ParametersFactory factory = new ParametersFactory();
		ActorActionOutcome defaultOutcome = factory
				.getDefaultActorActionOutcome();
		actorAction.addActionOutcome(defaultOutcome);
		parametersPageWrapper.getParametersPage().setFocus();
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
		} catch (RuntimeException e){
			getMessageBoxManager().showErrorMessage(
					"Loading parameters failed.");
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

	public SimulationConfig getConfigChoiceModel() {
		return configChoice;
	}

	public EngineConfigControllerFactory getConfigControllerFactory() {
		return configControllerFactory;
	}

	public void startComputation() {
		getMainController().computeCDBTSimulation(parametersModel, configChoice);
	}

	public void setChoosenEngineConfig(AbstractEngineConfig config) {
		configChoice.setCurrentlyChoosenEngineConfig(config);
	}

	public void setCalcAbsActionOccurances(boolean selection) {
		configChoice.getCommonSimulationConfig().setCalculateAbsoluteActionOccurances(selection);
	}

	public void setCalcRelActionOccurances(boolean selection) {
		configChoice.getCommonSimulationConfig().setCalculateRelativeActionOccurances(selection);
	}

	public void setRequestedNumberOfExpectedUtilities(int numOfRequestedValues) {
		configChoice.getCommonSimulationConfig().setNumberOfRequestedExpectedUtilityValues(numOfRequestedValues);
	}

	public void setUsingAspirationLevelIncrement(boolean selection) {
		parametersModel.setUsingAspirationLevelIncrement(selection);
	}

	public void setCalcLowestAspirationLevels(boolean selection) {
		configChoice.getCommonSimulationConfig().setCalculateLowestAspirationLevels(selection);
	}

}
