package simulation.core.view;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class PluginsBarItem extends Composite{

	public PluginsBarItem(ISimulationPlugin plugin, Composite parent, int style) {
		super(parent, style);

		RowLayout layout = new RowLayout();
		this.setLayout(layout);
		Label l = new Label(this, SWT.PUSH);
		l.setText("hello");
		System.out.println("created hello label");
//		TreeViewer viewer = new TreeViewer(this, SWT.MULTI);
//		viewer.setContentProvider(new PluginsBarItemContentProvider());
//		viewer.setLabelProvider(new PluginsBarItemLabelProvider());
		
//		viewer.setInput(plugin);
	}

	
}
