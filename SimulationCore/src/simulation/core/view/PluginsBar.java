package simulation.core.view;

import java.util.List;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;

public class PluginsBar extends Composite {

	private PluginPane pluginPane;
	private TreeViewer viewer;

	public PluginsBar(Composite parent, int style) {
		super(parent, style);
		System.out.println("create the pluginsbar");

		// init coloring
		Color simulationPluginsBackgroundColor = new Color(parent.getDisplay(),
				150, 150, 255);
		setBackground(simulationPluginsBackgroundColor);

		// initialize this objects behaviour within the parent
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		pluginsBarGridData.widthHint = 200;
		setLayoutData(pluginsBarGridData);

		// // init layout of objects in this object
		this.setLayout(new FillLayout());

		viewer = new TreeViewer(this, SWT.MULTI);
		viewer.setContentProvider(new PluginsBarTreeContentProvider());
		viewer.setLabelProvider(new PluginsBarTreeLabelProvider());

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event
							.getSelection();
					if (selection.getFirstElement() instanceof ISimulationPluginPaneContent) {
						ISimulationPluginPaneContent newForegroundPaneContent = ((ISimulationPluginPaneContent) selection
								.getFirstElement());
						pluginPane.setToForeGround(newForegroundPaneContent);
					}
				}
			}
		});
	}

	public void setPluginPane(PluginPane pluginPane) {
		this.pluginPane = pluginPane;
	}

	public void update(List<ISimulationPlugin> plugins) {
		viewer.setInput(plugins);
	}

}
