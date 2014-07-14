package cbdt.view.analysispage.listeners;

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

import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.result.Result;
import cbdt.view.analysispage.tree.TreePApplet;

/**
 * This class inherits from {@link SelectionListener} and is used to listen for
 * the {@link SelectionEvent} to display the tree structure from the computed
 * simulation {@link Result}s.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ShowTreeSelectionListener implements SelectionListener {

	public static final int TREE_ANIMATION_HEIGHT = 800;
	public static final int TREE_ANIMATION_WIDTH = 800;

	private Result simulationResult;
	private Composite parent;
	private DFSkeepTreeEngineConfig config;

	public ShowTreeSelectionListener(Result simulationResult,
			DFSkeepTreeEngineConfig config, Composite parent) {
		this.simulationResult = simulationResult;
		this.config = config;
		this.parent = parent;
	}

	@Override
	public void widgetSelected(SelectionEvent e) {
		System.out
				.println("ShowTreeSelectionListener.widgetSelected(): after entering method");
		showTreeAnimation();
		System.out
				.println("ShowTreeSelectionListener.widgetSelected(): before leaving method");
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}

	/**
	 * Creates a new {@link Shell} and {@link Composite} to hold an animated
	 * tree which represents the {@link Result}.
	 */
	private void showTreeAnimation() {
		Display display = parent.getDisplay();
		Shell shell = new Shell(display);
		shell.setLayout(new RowLayout());
		shell.setSize(TREE_ANIMATION_WIDTH + 30, TREE_ANIMATION_HEIGHT + 50);

		Composite appletComposite = new Composite(shell, SWT.EMBEDDED
				| SWT.NO_BACKGROUND);

		RowData rData = new RowData();
		rData.width = TREE_ANIMATION_WIDTH;
		rData.height = TREE_ANIMATION_HEIGHT;
		appletComposite.setLayoutData(rData);

		Frame frame = SWT_AWT.new_Frame(appletComposite);

		System.out
				.println("ShowTreeSelectionListener.showTreeAnimation(): before treePApplet creation");
		TreePApplet treePApplet = new TreePApplet();
		System.out
				.println("ShowTreeSelectionListener.showTreeAnimation(): after treePApplet creation");

		frame.add(treePApplet);
		treePApplet.setTreeModel(simulationResult.getRootNode(), config);

		System.out
				.println("ShowTreeSelectionListener.showTreeAnimation(): before treePApplet.init() call");
		treePApplet.init();
		System.out
				.println("ShowTreeSelectionListener.showTreeAnimation(): after treePApplet.init() call");

		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		treePApplet.stop();
		shell.dispose();
	}
}
