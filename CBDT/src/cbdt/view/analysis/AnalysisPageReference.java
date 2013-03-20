package cbdt.view.analysis;

import java.awt.Frame;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageWrapper;
import cbdt.view.CBDTHeaderComposite;

public class AnalysisPageReference extends AbstractPluginPageWrapper{

	public static final int TREE_ANIMATION_HEIGHT = 800;
	public static final int TREE_ANIMATION_WIDTH = 800;
	private AnalysisPage analysisPage;
	

	@Override
	public String getName() {
		return "Result analysis";
	}
	
	@Override
	public Composite getPageComposite(Composite parent) {
		Composite wrapperComposite = new CBDTHeaderComposite(parent, SWT.NONE);
		analysisPage = new AnalysisPage(wrapperComposite, SWT.NONE);
		wrapperComposite.pack();
		return wrapperComposite;
	}

	public AnalysisPage getAnalysisPage() {
		return analysisPage;
	}

	@SuppressWarnings("unused")
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
		
//		TreePApplet treePApplet = new TreePApplet();
//		frame.add(treePApplet);
//		treePApplet.init();
		shell.open();
		
		while(!shell.isDisposed()) {
			if (!display.readAndDispatch()) 
				display.sleep();
		}
//		treePApplet.stop();
		shell.dispose();
	}

}
