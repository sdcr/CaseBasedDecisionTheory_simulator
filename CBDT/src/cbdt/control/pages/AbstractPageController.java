package cbdt.control.pages;

import cbdt.control.MainController;
import cbdt.view.MessageBoxManager;
import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;


/**
 * An AbstractPageController is the controller for a specific plugin page. It is the receiver 
 * of all calls initiated by the user via this class's AbstractPluginPageWrapper.
 * @author S-lenovo
 */
public abstract class AbstractPageController {

	private MainController mainController;
	
	/**
	 * @return The plugin page, i.e. the view, for which this class is the controller.
	 */
	public abstract AbstractPluginPageWrapper getPageWrapper();
	
	public void setMainController(MainController mainController){
		this.mainController = mainController;
	}
	
	/**
	 * Puts the page associated with this controller into foreground by 
	 * forward that request to the main controller.
	 */
	public void goToForeground(){
		mainController.setToForeground(this);
	}
	
	/**
	 * @return The MessageBoxManager hold by the MainController.
	 */
	public MessageBoxManager getMessageBoxManager(){
		return mainController.getMessageBoxManager();
	}
	
	/**
	 * @return The MainController this AbstractPageController is associated with.
	 */
	protected MainController getMainController(){
		return mainController;
	}
}
