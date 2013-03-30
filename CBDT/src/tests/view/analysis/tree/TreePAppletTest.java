package tests.view.analysis.tree;

import org.junit.Before;
import org.junit.Test;

import cbdt.control.simulation.algorithm.dfskeeptree.NodeShell;

public class TreePAppletTest {
	
//	private NodeShell rootNode;
	
	@Before
	public void setup(){
//		rootNode = new NodeShell(null);
//		List<NodeShell> children = new ArrayList<NodeShell>();
//		NodeContentKeepTree content = new NodeContentKeepTree();
//		content.setAspirationLevel(12.5);
//		content.setProbabilityProduct(0.0125);
//		content.setLastAction(new ActorAction("A"));
//		NodeShell child = new NodeShell(content);
//		children.add(child);
//
//		content = new NodeContentKeepTree();
//		content.setAspirationLevel(12.5);
//		content.setProbabilityProduct(0.0125);
//		content.setLastAction(new ActorAction("B"));
//		child = new NodeShell(content);
//		children.add(child);
//		
//		content = new NodeContentKeepTree();
//		content.setAspirationLevel(20.5);
//		content.setProbabilityProduct(0.0125);
//		content.setLastAction(new ActorAction("B"));
//		child = new NodeShell(content);
//		children.add(child);
//		
//		rootNode.setChildren(children);
		
//		createChildren(rootNode, 1, 5);
	}
	
	public void createChildren(NodeShell shell, int stage, int maxstage){
//		if(stage>=maxstage)
//			return;
//		List<NodeShell> children = new ArrayList<NodeShell>();
//		for(int i=0; i<3; i++){
//			NodeContentKeepTree content = new NodeContentKeepTree();
//			content.setAspirationLevel(12.5);
//			content.setProbabilityProduct(0.0125);
//			content.setLastAction(new ActorAction("A"));
//			NodeShell child = new NodeShell(content);
//			
//			children.add(child);
//			createChildren(child, stage+1, maxstage);
//		}
//		
//		shell.setChildren(children);
	}
	
	@Test
	public void testView(){
//		Display display = new Display();
//		Shell shell = new Shell(display);
//		shell.setLayout(new RowLayout());
//		shell.setSize(ShowTreeSelectionListener.TREE_ANIMATION_WIDTH + 30, ShowTreeSelectionListener.TREE_ANIMATION_HEIGHT + 50);
//		
//		Composite appletComposite = new Composite(shell, SWT.EMBEDDED | SWT.NO_BACKGROUND);
//		
//		RowData rData = new RowData();
//		rData.width = ShowTreeSelectionListener.TREE_ANIMATION_WIDTH;
//		rData.height = ShowTreeSelectionListener.TREE_ANIMATION_HEIGHT;
//		appletComposite.setLayoutData(rData);
//		
//		Frame frame = SWT_AWT.new_Frame(appletComposite);
//		
//		TreePApplet treePApplet = new TreePApplet();
//		treePApplet.setTreeModel(rootNode);
//		frame.add(treePApplet);
//		treePApplet.init();
//		shell.open();
//		
//		while(!shell.isDisposed()) {
//			if (!display.readAndDispatch()) 
//				display.sleep();
//		}
//		treePApplet.stop();
//		display.dispose();
	}
}
