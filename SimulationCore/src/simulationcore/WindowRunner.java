package simulationcore;

import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
//		shell.setLayout(new FillLayout());
		
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