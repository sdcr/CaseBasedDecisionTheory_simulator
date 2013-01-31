package simulation.core;

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

public class WindowRunner {
	
	private BundleContext context;
	
	public WindowRunner(BundleContext context) {
		// TODO Auto-generated constructor stub
		this.context = context; 
	}
	
	public void showWindow( ) {
		
		Display display = new Display ();
		Shell shell = new Shell(display);
		
		GridLayout windowLayout = new GridLayout(2,false);
		shell.setLayout(windowLayout);
		
		//create 
		ScrolledComposite pluginsBar = new ScrolledComposite(shell, SWT.PUSH);
		Color simulationPluginsBackgroundColor = new Color(display, 0, 0, 255);
		pluginsBar.setBackground(simulationPluginsBackgroundColor);
		pluginsBar.setExpandVertical(true);
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		pluginsBar.setLayoutData(pluginsBarGridData);
		
		//create the mainPane Composite
		Composite mainPane = new Composite(shell, SWT.PUSH);
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
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent e) {
				
			}
		});
		
		shell.addDisposeListener(new DisposeListener() {
			
			@Override
			public void widgetDisposed(DisposeEvent e) {
				try {
					Bundle systemBundle = context.getBundle(0);
					systemBundle.stop();
				} catch (BundleException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		shell.open ();
		
		while (!shell.isDisposed ()) {
			if (!display.readAndDispatch ()) display.sleep ();
		}
		
//		display.dispose();
	}
}