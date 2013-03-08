package simulation.core.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.control.SimulationPluginManager;
import simulation.core.view.menu.MenuBarWrapper;
import simulation.core.view.pluginsbar.PluginsBar;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class MainViewManager {

	private PluginsBar pluginsBar;
	private ForegroundManager foregroundManager;
	private Controller controller;
	private Shell shell;

	private MenuBarWrapper menuBar;

	public MainViewManager(final Controller controller) {
		this.controller = controller;
		shell = new Shell(new Display());
		instantiateViewElements();

		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				controller.stopApplication();
			}
		});
	}

	/**
	 * Disposes the main shell, if it is not already disposed.
	 */
	public void disposeView(){
		if(!shell.isDisposed())
			shell.dispose();
	}
	
	/**
	 * Starts to show the main shell and the view elements of the SimulationCore application.
	 */
	public void startView() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	/**
	 * Instantiates all view elements of the application, which are to be shown in the main shell. 
	 * @param shell
	 */
	private void instantiateViewElements() {
		shell.setLayout(new GridLayout(2, false));
		
		pluginsBar = new PluginsBar(shell, SWT.PUSH);
		PluginPane pluginPane = new PluginPane(shell, SWT.NONE);
		foregroundManager = new ForegroundManager(pluginPane);
		pluginsBar.setForegroundManager(foregroundManager);
		
		menuBar = new MenuBarWrapper(shell, controller);
	}
	
	/**
	 * Updates the view elements of the main shell according to the passed pluginManager.
	 * @param pluginManager
	 */
	public void updateView(SimulationPluginManager pluginManager){
		List<ISimulationPlugin> simulationPlugins = pluginManager.getISimulationPlugins();
		menuBar.addMenuElements(simulationPlugins);
		pluginsBar.update(simulationPlugins);
	}

	/**
	 * Shows a message box with the passed message String to the user.
	 * @param message
	 */
	public void showMessage(String message) {
		MessageBox messageBox = new MessageBox(shell);
		messageBox.setMessage(message);
		messageBox.open();
	}
	
	public boolean askUser(String question){
	    MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION |SWT.YES | SWT.NO);
	    messageBox.setMessage(question);
	    int selectedButtonID = messageBox.open();
	    
	    if(selectedButtonID == SWT.YES)
	    	return true;
	    else
	    	return false;
	}
}
