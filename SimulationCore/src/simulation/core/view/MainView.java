package simulation.core.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;

public class MainView {

	private PluginsBar pluginsBar;
	private MainPane mainPane;
	private Controller controller;
	
	public MainView(final Controller controller) {
		this.controller = controller;

		Display display = new Display();
		Shell shell = new Shell(display);
		initializeContent(shell);

		shell.addDisposeListener(new DisposeListener() {
			@Override
			public void widgetDisposed(DisposeEvent e) {
				controller.shellDisposed();
			}
		});

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	private void initializeContent(Shell shell) {
		GridLayout windowLayout = new GridLayout(2, false);
		shell.setLayout(windowLayout);
		pluginsBar = new PluginsBar(shell, SWT.PUSH);
		mainPane = new MainPane(shell, SWT.PUSH);
	}
	
//	private void addPluginAdd(Shell shell, final BundleContext context){
//		shell.addMouseListener(new MouseListener() {
//
//			@Override
//			public void mouseUp(MouseEvent e) {
//
//			}
//
//			@Override
//			public void mouseDown(MouseEvent e) {
//				System.out.println("mouse down");
//				File f = new File(
//						"C:/Users/S-lenovo/Documents/Uni/IDP/plugins/CBDT_1.0.0.201302010935.jar");
//
//				FileInputStream fileStream;
//				Bundle cbdt;
//				try {
//					fileStream = new FileInputStream(f);
//					cbdt = context.installBundle("cbdt", fileStream);
//					cbdt.start();
//				} catch (BundleException e1) {
//					e1.printStackTrace();
//				} catch (FileNotFoundException e1) {
//					e1.printStackTrace();
//				}
//
//			}
//
//			@Override
//			public void mouseDoubleClick(MouseEvent e) {
//				try {
//					context.getBundle("cbdt").uninstall();
//				} catch (BundleException e1) {
//					e1.printStackTrace();
//				}
//			}
//		});
//	}
}
