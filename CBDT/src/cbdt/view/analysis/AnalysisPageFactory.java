package cbdt.view.analysis;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import cbdt.view.analysis.tree.TreePApplet;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;

public class AnalysisPageFactory extends AbstractPluginPageWrapper{

	
	private static final int TREE_ANIMATION_HEIGHT = 800;
	private static final int TREE_ANIMATION_WIDTH = 800;

	@Override
	public String getName() {
		return "Ergebnis-Analyse";
	}

	@Override
	public Composite getPageComposite(Composite parent) {
		Composite parameterComposite = new Composite(parent, SWT.NONE);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		parameterComposite.setLayout(rowLayout);
		Label l = new Label(parameterComposite, SWT.NONE);
		l.setText("Analyse");
		
		showTreeAnimation(parent);
		
		return parameterComposite;
	}

	private void showTreeAnimation(Composite parent) {
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
