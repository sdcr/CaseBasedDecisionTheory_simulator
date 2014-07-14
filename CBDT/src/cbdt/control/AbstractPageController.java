package cbdt.control;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;

/**
 * A subclass of the {@link AbstractPageController} is the controller for a
 * specific plugin page. It is the receiver of all requests from the user via
 * this page's view elements. However, the extending class can also in turn
 * instantiate one or more controllers to which it delegates part of the request
 * handling.
 * 
 * @author Stephan da Costa Ribeiro
 */
public abstract class AbstractPageController {

	/**
	 * The {@link MainController} of the application.
	 */
	private MainController mainController;

	/**
	 * @return The plugin page, i.e. the view, for which this class is the
	 *         controller.
	 */
	public abstract AbstractPluginPageWrapper getPageWrapper();

	/**
	 * Sets the {@link MainController} of the plugin.
	 * 
	 * @param mainController
	 */
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	/**
	 * @return The {@link MainController} this AbstractPageController is
	 *         associated with.
	 */
	public MainController getMainController() {
		return mainController;
	}

	/**
	 * Puts the page associated with this {@link AbstractPageController} into
	 * foreground by forwarding that request to the {@link MainController}.
	 */
	public void goToForeground() {
		mainController.setToForeground(this);
	}

}
