package cbdt.control.parameterspage.parameters;

//YELLOW
import java.io.FileNotFoundException;

import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.control.persistence.parameters.IParametersPersistenceManager;
import cbdt.control.persistence.parameters.ParametersPersistenceManager;
import cbdt.model.parameters.ActorAction;
import cbdt.model.parameters.ActorActionOutcome;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.ParametersFactory;

/**
 * The ParametersController handles all requests which demand changes in the
 * Parameters model object. It also handles requests to store and load parameter
 * from files.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ParametersController {

	/**
	 * The parameters model as currently displayed in the view.
	 */
	private Parameters parameters;

	/**
	 * The persistence manager to store parameters.
	 */
	private IParametersPersistenceManager parametersPersistenceManager;

	/**
	 * The parametersConfigPageController for which it handles the outsourced
	 * change model requests.
	 */
	private ParametersConfigPageController parametersConfigPageController;

	public ParametersController(
			ParametersConfigPageController parametersConfigPageController) {
		this.parametersConfigPageController = parametersConfigPageController;

		// create parameters persistence manager
		parametersPersistenceManager = new ParametersPersistenceManager();

		// set parameters to default
		ParametersFactory parametersFactory = new ParametersFactory();
		parameters = parametersFactory.getDefaultParameters();
	}

	public Parameters getParametersModel() {
		return parameters;
	}

	public ParametersConfigPageController getParametersConfigPageController() {
		return parametersConfigPageController;
	}

	/**
	 * Uses the ParametersFactory to create a default ActorAction.
	 * 
	 * @return The created ActorAction object.
	 */
	public ActorAction addDefaultActorActionToModel() {
		ParametersFactory factory = new ParametersFactory();
		ActorAction defaultActorAction = factory.getDefaultActorAction();
		parameters.addActorAction(defaultActorAction);
		return defaultActorAction;
	}

	public void removeActorActionFromModel(ActorAction actorAction) {
		parameters.removeActorAction(actorAction);
	}

	/**
	 * Uses the ParametersFactory to create a default ActorActionOutcome object
	 * and adds it to the passed ActorAction. Also calls setFocus() in the
	 * parameters page composite.
	 * 
	 * @param actorAction
	 * @return The created ActorActionOutcome object.
	 */
	public ActorActionOutcome addDefaultActorActionOutcomeToModel(
			ActorAction actorAction) {
		ParametersFactory factory = new ParametersFactory();
		ActorActionOutcome defaultOutcome = factory
				.getDefaultActorActionOutcome();
		actorAction.addActionOutcome(defaultOutcome);

		// maybe do this in some listener or sth.
		parametersConfigPageController.getPageWrapper().getParametersPage()
				.setFocus();
		return defaultOutcome;
	}

	/**
	 * Removes the passed outcome object from the ActorAction it is associated
	 * with.
	 * 
	 * @param outcome
	 */
	public void removeActorActionOutcomeFromModel(ActorActionOutcome outcome) {
		ActorAction actorAction = outcome.getAction();
		actorAction.removeActionOutcome(outcome);
	}

	/**
	 * @param actorAction
	 *            The ActorAction object whose name should be set.
	 * @param newName
	 *            The name the ActorAction should be given.
	 */
	public void setActorActionName(ActorAction actorAction, String newName) {
		actorAction.setActionName(newName);
	}

	/**
	 * @param newInitAspirationLevel
	 *            The initial aspiration level of simulation.
	 */
	public void setInitialAspirationLevel(Double newInitAspirationLevel) {
		parameters.setInitialAspirationLevel(newInitAspirationLevel);
	}

	/**
	 * @param newAspirationLevelIncrement
	 *            The aspiration level increment during the simulation.
	 */
	public void setAspirationLevelIncrement(Double newAspirationLevelIncrement) {
		parameters.setAspirationLevelIncrement(newAspirationLevelIncrement);
	}

	/**
	 * @param newAspirationLevelDiscountFactor
	 *            The factor with which the aspiration level should be
	 *            discounted at each simulation step.
	 */
	public void setAspirationDiscountFactor(
			Double newAspirationLevelDiscountFactor) {
		parameters.setWeightingFactorAlpha(newAspirationLevelDiscountFactor);
	}

	/**
	 * Set whether during the simulation the aspiration level should be
	 * incremented at sparse simulation steps. TODO: more details about when the
	 * level is increased.
	 * 
	 * @param selection
	 */
	public void setUsingAspirationLevelIncrement(boolean selection) {
		parameters.setUsingAspirationLevelIncrement(selection);
	}

	/**
	 * Uses the persistence manager to read parameters from a file and sets the
	 * parameters model accordingly. Puts the parameters config page in
	 * foreground. Sets the page with the new parameters model.
	 * 
	 * @param filepath The filepath under which to find the file with the parameters model.
	 * TODO: more details about whether an absolute path is necessary, or if a relative path is enough.
	 */
	public void loadParametersFromFile(String filepath) {
		try {
			parameters = parametersPersistenceManager
					.getParametersFromFile(filepath);
			parametersConfigPageController.goToForeground();
			parametersConfigPageController.getPageWrapper().getParametersPage()
					.setParametersModel(parameters);
		} catch (FileNotFoundException e) {
			parametersConfigPageController.getMainController()
					.getMessageBoxManager()
					.showErrorMessage("The stated file could not be found.");
		} catch (RuntimeException e) {
			parametersConfigPageController.getMainController()
					.getMessageBoxManager()
					.showErrorMessage("Loading parameters failed.");
		}
	}

	/**
	 * Stores the parameters model with the persistence manager.
	 * @param filepath The filepath under which to store the parameters model.
	 * TODO: more details about whether an absolute path is necessary, or if a relative path is enough.
	 */
	public void saveParametersToFile(String filepath) {
		parametersConfigPageController.goToForeground();
		try {
			parametersPersistenceManager.saveParametersToFile(filepath,
					parameters);
		} catch (Exception e) {
			parametersConfigPageController
					.getMainController()
					.getMessageBoxManager()
					.showErrorMessage(
							"An error occured while saving the parameters.");
			e.printStackTrace();
		}
	}
}
