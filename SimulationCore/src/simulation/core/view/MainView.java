package simulation.core.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;
import simulation.core.model.SimPluginStore;

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
//		shell.setLayout(new FillLayout());
//		Composite main = new Composite(shell, SWT.NONE);
//		main.setLayout(new GridLayout(2, false));
//
//		// pluginsBar = new PluginsBar(main, SWT.NONE, controller);
//		ScrolledComposite pluginsBar = new ScrolledComposite(shell,
//				SWT.PUSH | SWT.V_SCROLL);
//		// c1.setBackground(c1.getDisplay().getSystemColor(SWT.COLOR_CYAN));
//
//		final Composite scrollContent = new Composite(pluginsBar, SWT.NONE);
//		for (int i = 0; i <= 50; i++) {
//			Label label = new Label(scrollContent, SWT.NONE);
//			label.setText("jo");
//		}
//
//		RowLayout layout = new RowLayout(SWT.VERTICAL);
//		layout.wrap = false;
//		scrollContent.setLayout(layout);
//		
//		pluginsBar.setContent(scrollContent);
//		pluginsBar.setExpandVertical(true);
//		pluginsBar.setExpandHorizontal(true);
//		scrollContent.setBackground(pluginsBar.getDisplay().getSystemColor(SWT.COLOR_CYAN));
//		
		
		
//		Composite scrollContent = new Composite(scrollComposite, SWT.NONE);
//
//		RowLayout rowLayout = new RowLayout();
//		rowLayout.wrap = true;
//		scrollContent.setLayout(rowLayout);
//
//		Label l = new Label(scrollContent, SWT.NONE);
//		l.setLayoutData(new RowData());
//		l.setText("label");
//
//		scrollComposite.setContent(scrollContent);
//		scrollComposite.setExpandVertical(true);
//
//		Composite c2 = new Composite(main, SWT.NONE);
//		c2.setBackground(c2.getDisplay().getSystemColor(SWT.COLOR_BLACK));
//		c2.setLayout(new GridLayout(2, false));
//
//
//		// pluginsBar.pack();
//		// pluginPane.pack();
		shell.setLayout(new GridLayout(2,false));
		
		pluginsBar = new PluginsBar(shell, SWT.PUSH, controller);
		pluginPane = new PluginPane(shell, SWT.NONE);
		pluginsBar.setPluginPane(pluginPane);
		
//		main.pack();
//		main.setBounds(0, 0, 800, 500);
//		shell.pack();
	}

	public void updateFromModel() {
		System.out.println("updating view");
		// pluginsBar.insertExampleContent();
		// List<ISimulationPlugin> plugins =
		// simPluginStore.getSimulationPlugins();
		// pluginsBar.update(plugins);
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
