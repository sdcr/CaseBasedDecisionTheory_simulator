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
import cbdt.view.parameterspage.ParametersConfigPageComposite;

/**
 * The ParametersController handles all requests which demand changes in the
 * {@link Parameters} model object. It also handles requests to store and load
 * parameter from files.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ParametersController {

	/**
	 * The {@link Parameters} model as currently displayed in the view.
	 */
	private Parameters parameters;

	/**
	 * The {@link IParametersPersistenceManager} to store parameters.
	 */
	private IParametersPersistenceManager parametersPersistenceManager;

	/**
	 * The {@link ParametersConfigPageController} for which it handles the
	 * outsourced change model requests. That is, the object referenced here is
	 * one hierarchy level higher in the plugin's controller hierarchy.
	 */
	private ParametersConfigPageController parametersConfigPageController;

	/**
	 * Constructor.
	 * 
	 * @param parametersConfigPageController
	 *            The {@link ParametersConfigPageController} which uses this
	 *            class to outsource request handling.
	 */
	public ParametersController(
			ParametersConfigPageController parametersConfigPageController) {
		this.parametersConfigPageController = parametersConfigPageController;

		// create parameters persistence manager
		parametersPersistenceManager = new ParametersPersistenceManager();

		// set parameters to default
		ParametersFactory parametersFactory = new ParametersFactory();
		parameters = parametersFactory.getDefaultParameters();
	}

	/**
	 * @return The {@link Parameters} object for which this controller is
	 *         responsible.
	 */
	public Parameters getParametersModel() {
		return parameters;
	}

	/**
	 * @return The {@link ParametersConfigPageController} which is one
	 *         controller hierarchy level higher than this controller.
	 */
	public ParametersConfigPageController getParametersConfigPageController() {
		return parametersConfigPageController;
	}

	/**
	 * Uses the {@link ParametersFactory} to create a default
	 * {@link ActorAction}.
	 * 
	 * @return The created {@link ActorAction} object.
	 */
	public ActorAction addDefaultActorActionToModel() {
		ParametersFactory factory = new ParametersFactory();
		ActorAction defaultActorAction = factory.getDefaultActorAction();
		parameters.addActorAction(defaultActorAction);
		return defaultActorAction;
	}

	/**
	 * @param actorAction
	 *            The {@link ActorAction} which is to be removed from the
	 *            {@link Parameters} model.
	 */
	public void removeActorActionFromModel(ActorAction actorAction) {
		parameters.removeActorAction(actorAction);
	}

	/**
	 * Uses the {@link ParametersFactory} to create a default
	 * {@link ActorActionOutcome} object and adds it to the passed
	 * {@link ActorAction}. Also calls setFocus() in the
	 * {@link ParametersConfigPageComposite}.
	 * 
	 * @param actorAction
	 * @return The created {@link ActorActionOutcome} object.
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
	 * Removes the passed {@link ActorActionOutcome} from the
	 * {@link ActorAction} it is associated with.
	 * 
	 * @param outcome
	 */
	public void removeActorActionOutcomeFromModel(ActorActionOutcome outcome) {
		ActorAction actorAction = outcome.getAction();
		actorAction.removeActionOutcome(outcome);
	}

	/**
	 * @param actorAction
	 *            The {@link ActorAction} object whose name should be set.
	 * @param newName
	 *            The name the {@link ActorAction} should be given.
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
		parameters.setAspirationLevelDecrementFactor(newAspirationLevelDiscountFactor);
	}

	/**
	 * Set whether the aspiration level should be incremented at sparse
	 * simulation steps.
	 * 
	 * TODO: more details about when the level is increased.
	 * 
	 * @param selection
	 */
	public void setIncrementAspirationLevelSparsely(boolean selection) {
		parameters.setIncrementAspirationLevelSparsely(selection);
	}

	/**
	 * Uses the {@link ParametersPersistenceManager} to read parameters from a
	 * file and sets the {@link Parameters} model accordingly. Puts the
	 * parameters page in foreground. Sets the page with the new parameters
	 * model.
	 * 
	 * @param filepath
	 *            The filepath under which to find the file with the parameters
	 *            model. TODO: more details about whether an absolute path is
	 *            necessary, or if a relative path is enough.
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
	 * Stores the {@link Parameters} model with the
	 * {@link ParametersPersistenceManager}.
	 * 
	 * @param filepath
	 *            The filepath under which to store the parameters model. TODO:
	 *            more details about whether an absolute path is necessary, or
	 *            if a relative path is enough.
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
