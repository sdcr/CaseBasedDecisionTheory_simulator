package cbdt.view.analysis;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import cbdt.model.result.Result;
import cbdt.view.analysis.tree.TreePApplet;

public class ShowTreeSelectionListener implements SelectionListener {

	public static final int TREE_ANIMATION_HEIGHT = 800;
	public static final int TREE_ANIMATION_WIDTH = 800;
	
	private Result simulationResult;
	private Composite parent;

	public ShowTreeSelectionListener(Result simulationResult, Composite parent) {
		this.simulationResult = simulationResult;
		this.parent = parent;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		showTreeAnimation();
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}
	
	private void showTreeAnimation() {
		Display display = parent.getDisplay();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());
		shell.setSize(TREE_ANIMATION_WIDTH + 30, TREE_ANIMATION_HEIGHT + 50);
		
		Composite appletComposite = new Composite(shell, SWT.EMBEDDED | SWT.NO_BACKGROUND);
		
		RowData rData = new RowData();
		rData.width = TREE_ANIMATION_WIDTH;
		rData.height = TREE_ANIMATION_HEIGHT;
		appletComposite.setLayoutData(rData);
		
		Frame frame = SWT_AWT.new_Frame(appletComposite);
		
		TreePApplet treePApplet = new TreePApplet();
		frame.add(treePApplet);
		treePApplet.setTreeModel(simulationResult.getRootNode());
		treePApplet.init();
		shell.open();
		
		while(!shell.isDisposed()) {
			if (!display.readAndDispatch()) 
				display.sleep();
		}
		treePApplet.stop();
		shell.dispose();
	}
}
