package simulation.core.view;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.model.SimPluginStore;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class MainView {

	private PluginsBar pluginsBar;
	private PluginPane pluginPane;
	private Controller controller;
	private SimPluginStore simPluginStore;
	private Shell shell;

	public MainView(final Controller controller, SimPluginStore simPluginStore) {
		this.controller = controller;
		this.simPluginStore = simPluginStore;

		shell = new Shell(new Display());
		initializeContent(shell);

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

	private void initializeContent(Shell shell) {
		shell.setLayout(new GridLayout(2, false));
		pluginsBar = new PluginsBar(shell, SWT.PUSH, controller);
		pluginPane = new PluginPane(shell, SWT.NONE);
		pluginsBar.setPluginPane(pluginPane);
	}

	public void updateFromModel() {
		System.out.println("updating view");
		// pluginsBar.insertExampleContent();
		List<ISimulationPlugin> plugins = simPluginStore.getSimulationPlugins();
		pluginsBar.update(plugins);
		// pluginPane.update(plugins);
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
