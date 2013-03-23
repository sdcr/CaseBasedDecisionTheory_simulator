package cbdt.control.pages;

import cbdt.control.MainController;
import cbdt.view.MessageBoxManager;
import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;


/**
 * An IPageController is the controller for a specific AbstractPluginPageWrapper. It is the receiver 
 * of all calls initiated by the user via this class's AbstractPluginPageWrapper.
 * @author S-lenovo
 */
public abstract class AbstractPageController {

	private MainController mainController;
	
	/**
	 * @return The plugin page, i.e. the view, for which this class is the controller.
	 */
	public abstract AbstractPluginPageWrapper getPageReference();
	
	public void setMainController(MainController mainController){
		this.mainController = mainController;
	}
	
	public void goToForeground(){
		mainController.setToForeground(this);
	}
	
	public MessageBoxManager getMessageBoxManager(){
		return mainController.getMessageBoxManager();
	}
	
	protected MainController getMainController(){
		return mainController;
	}
}
