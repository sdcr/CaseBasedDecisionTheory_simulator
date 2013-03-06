package simulation.core.view;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.control.SimulationPluginManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class MainView implements Observer{

	private PluginsBar pluginsBar;
	private PluginPageManager pluginPane;
	private Controller controller;
	private Shell shell;
	private Menu menuBar;

	public MainView(final Controller controller) {
		this.controller = controller;

		shell = new Shell(new Display());
		initializeView(shell);

		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				controller.shellDisposed();
			}
		});

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
		
		createMenuBar(shell);
	}

	private void createMenuBar(Shell shell) {
		menuBar = new Menu(shell, SWT.BAR);
		
		MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
		fileMenuHeader.setText("&File");
	    Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
	    fileMenuHeader.setMenu(fileMenu);
	    MenuItem fileAddPluginItem = new MenuItem(fileMenu, SWT.PUSH);
	    fileAddPluginItem.setText("&Add a plugin");
	    fileAddPluginItem.addSelectionListener(new AddPluginMenuItemSelectionListener(shell, controller));
		
		MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
	    helpMenuHeader.setText("&Help");
	    Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
	    helpMenuHeader.setMenu(helpMenu);
	    MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
	    helpGetHelpItem.setText("&Get Help");
	    
	    shell.setMenuBar(menuBar);
	    
	    
	}
	
	public void setPluginManager(SimulationPluginManager pluginManager){
		pluginManager.addObserver(this);
		update(pluginManager, null);
		pluginsBar.setPluginManager(pluginManager);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO update the shown menu
		if(arg0 instanceof SimulationPluginManager){
			MenuItem[] currentMenuHeaders = menuBar.getItems();
			SimulationPluginManager pluginManager = (SimulationPluginManager)arg0;
	
			@SuppressWarnings("unused")
			List<ISimulationPlugin> list = pluginManager.getActiveISimulationPlugins();
			
			@SuppressWarnings("unused")
			int i=0;
//			if(currentMenuHeaders)
//			viewer.setInput(((SimulationPluginManager)arg0).getActiveISimulationPlugins());
		}
	}

	public void showMessage(String message) {
		MessageBox messageBox = new MessageBox(shell);
		messageBox.setMessage(message);
		messageBox.open();
	}

	// private void addPluginAdd(Shell shell, final BundleContext context){
	// shell.addMouseListener(new MouseListener() {
	//
	// @Override
	// public void mouseUp(MouseEvent e) {
	//
	// }
	//
	// @Override
	// public void mouseDown(MouseEvent e) {
	// System.out.println("mouse down");
	// File f = new File(
	// "C:/Users/S-lenovo/Documents/Uni/IDP/plugins/CBDT_1.0.0.201302010935.jar");
	//
	// FileInputStream fileStream;
	// Bundle cbdt;
	// try {
	// fileStream = new FileInputStream(f);
	// cbdt = context.installBundle("cbdt", fileStream);
	// cbdt.start();
	// } catch (BundleException e1) {
	// e1.printStackTrace();
	// } catch (FileNotFoundException e1) {
	// e1.printStackTrace();
	// }
	//
	// }
	//
	// @Override
	// public void mouseDoubleClick(MouseEvent e) {
	// try {
	// context.getBundle("cbdt").uninstall();
	// } catch (BundleException e1) {
	// e1.printStackTrace();
	// }
	// }
	// });
	// }
}
