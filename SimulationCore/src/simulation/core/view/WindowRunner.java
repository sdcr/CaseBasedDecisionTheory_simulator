package simulation.core.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.RegistryFactory;
import org.eclipse.core.runtime.dynamichelpers.ExtensionTracker;
import org.eclipse.core.runtime.dynamichelpers.IFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;

import simulation.extensionpoint.simulationplugin.SimulationPluginExtensionHandler;
import simulation.extensionpoint.simulationplugin.handlers.SimulationPluginViewIntegrator;

public class WindowRunner {

	private BundleContext context;
	

	public WindowRunner(BundleContext context) {
		this.context = context;
	}

	public void showWindow() {

		Display display = new Display();
		Shell shell = new Shell(display);
		buildGUI(display, shell);

		//handle closeing of window
		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				try {
					Bundle systemBundle = context.getBundle(0);
					systemBundle.stop();
					System.out.println("stopped system bundle");
				} catch (BundleException e1) {
					e1.printStackTrace();
				}
			}
		});

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}

		display.dispose();
	}

	private void buildGUI(Display display, Shell shell) {
		GridLayout windowLayout = new GridLayout(2, false);
		shell.setLayout(windowLayout);

		// create pluginsBar
		final ScrolledComposite pluginsBar = new ScrolledComposite(shell,
				SWT.PUSH);
		Color simulationPluginsBackgroundColor = new Color(display, 0, 0, 255);
		pluginsBar.setBackground(simulationPluginsBackgroundColor);
		pluginsBar.setExpandVertical(true);
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		pluginsBar.setLayoutData(pluginsBarGridData);

		// create the mainPane Composite
		final Composite mainPane = new Composite(shell, SWT.PUSH);
		Color mainPaneColor = new Color(display, 200, 200, 200);
		mainPane.setBackground(mainPaneColor);
		GridData mainPaneGridData = new GridData();
		mainPaneGridData.verticalAlignment = GridData.FILL;
		mainPaneGridData.horizontalAlignment = GridData.FILL;
		mainPaneGridData.grabExcessHorizontalSpace = true;
		mainPane.setLayoutData(mainPaneGridData);

		shell.addMouseListener(new MouseListener() {

			@Override
			public void mouseUp(MouseEvent e) {

			}

			@Override
			public void mouseDown(MouseEvent e) {
				System.out.println("mouse down");
				File f = new File(
						"C:/Users/S-lenovo/Documents/Uni/IDP/plugins/CBDT_1.0.0.201302010935.jar");

				FileInputStream fileStream;
				Bundle cbdt;
				try {
					fileStream = new FileInputStream(f);
					cbdt = context.installBundle("cbdt", fileStream);
					cbdt.start();
				} catch (BundleException e1) {
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}

			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				try {
					context.getBundle("cbdt").uninstall();
				} catch (BundleException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}