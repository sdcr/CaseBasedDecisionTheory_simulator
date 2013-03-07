package simulation.core.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.control.SimulationPluginManager;
import simulation.core.view.menu.MenuManager;
import simulation.core.view.pluginsbar.PluginsBar;

public class MainViewManager {

	private PluginsBar pluginsBar;
	private ForegroundManager foregroundManager;
	private Controller controller;
	private Shell shell;

	private MenuManager menuManager;

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
		foregroundManager = new ForegroundManager(shell, SWT.NONE);
		pluginsBar.setPluginPane(foregroundManager);
		
		menuManager = new MenuManager(controller);
		menuManager.createMenuBar(shell);
	}
	
	/**
	 * Updates the view elements of the main shell according to the passed pluginManager.
	 * @param pluginManager
	 */
	public void updateView(SimulationPluginManager pluginManager){
		menuManager.update(pluginManager);
		pluginsBar.update(pluginManager);
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
}
