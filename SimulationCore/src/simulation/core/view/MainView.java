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

public class MainView {

	private PluginsBar pluginsBar;
	private PluginPageManager pluginPane;
	private Controller controller;
	private Shell shell;

	private MenuManager menuManager;

	public MainView(final Controller controller) {
		this.controller = controller;

		shell = new Shell(new Display());
		initializeView(shell);

		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				controller.stopApplication();
			}
		});

	}

	public void disposeShell(){
		shell.dispose();
	}
	
	public void startView() {
		shell.open();
		Display display = shell.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private void initializeView(Shell shell) {
		shell.setLayout(new GridLayout(2, false));
		pluginsBar = new PluginsBar(shell, SWT.PUSH);
		pluginPane = new PluginPageManager(shell, SWT.NONE);
		pluginsBar.setPluginPane(pluginPane);
		
		menuManager = new MenuManager(controller);
		menuManager.createMenuBar(shell);
	}
	
	public void setPluginManager(SimulationPluginManager pluginManager){
//		pluginManager.addObserver(menuManager);
		menuManager.update(pluginManager, null);
		pluginsBar.setPluginManager(pluginManager);
	}

	public void showMessage(String message) {
		MessageBox messageBox = new MessageBox(shell);
		messageBox.setMessage(message);
		messageBox.open();
	}
}
