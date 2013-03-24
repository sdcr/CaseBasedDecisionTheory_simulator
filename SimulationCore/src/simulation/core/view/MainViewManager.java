package simulation.core.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.control.plugins.SimulationPluginManager;
import simulation.core.view.menu.MenuBarWrapper;
import simulation.core.view.pluginsbar.PluginsBar;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class MainViewManager {

	private static final String ICON_LOCATION = "/resources/simulation.ico";
	
	private static final String WINDOW_TITLE = "Simulation Container";
	private static final int DEFAULT_HEIGHT = 800;
	private static final int DEFAULT_WIDTH = 840;
	
	private PluginsBar pluginsBar;
	private ForegroundManager foregroundManager;
	private Controller controller;
	private Shell shell;

	private MenuBarWrapper menuBar;

	public MainViewManager(final Controller controller) {
		this.controller = controller;
		shell = new Shell(new Display());
		shell.setText(WINDOW_TITLE);
		ImageData imageData = new ImageData(getClass().getResourceAsStream(ICON_LOCATION));
		Image iconImage = new Image(shell.getDisplay(),imageData);
		shell.setImage(iconImage);
		shell.setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
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
		shell.setLayout(new FillLayout());
		final ScrolledComposite scrolledComposite = new ScrolledComposite(shell, SWT.V_SCROLL | SWT.H_SCROLL);
		scrolledComposite.setLayout(new FillLayout(SWT.VERTICAL));
		
		final Composite generalWrapper = new Composite(scrolledComposite, SWT.NONE);
		generalWrapper.setLayout(new GridLayout(2, false));
		scrolledComposite.setContent(generalWrapper);
		scrolledComposite.setMinHeight(SWT.DEFAULT);
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.getVerticalBar().setIncrement(33);
		
		shell.addListener(SWT.Resize, new ResizeListener(scrolledComposite, generalWrapper));

		pluginsBar = new PluginsBar(generalWrapper, SWT.PUSH);
		final PluginPane pluginPane = new PluginPane(generalWrapper, SWT.NONE);
		pluginPane.addListener(SWT.Resize, new ResizeListener(scrolledComposite, generalWrapper));
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
		for(ISimulationPlugin plugin : simulationPlugins){
			plugin.setForegroundManager(foregroundManager);
		}
		menuBar.addMenuElements(simulationPlugins);
		pluginsBar.update(simulationPlugins);
	}

	/**
	 * Shows a message box with the passed message String to the user.
	 * @param message
	 */
	public void showInfoMessage(String message) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION);
		messageBox.setMessage(message);
		messageBox.open();
	}
	
	/**
	 * Shows a message box with the passed message String to the user.
	 * @param message
	 */
	public void showErrorMessage(String message) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_ERROR);
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

	public void showApplicationInformationDialog() {
		ApplicationInfoDialog appInfoDialog = new ApplicationInfoDialog(shell);
		appInfoDialog.open();
	}
}
