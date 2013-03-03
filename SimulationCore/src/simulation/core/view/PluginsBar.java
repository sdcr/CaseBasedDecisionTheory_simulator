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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageContentWrapper;

public class PluginsBar extends Composite {

	private PluginPane pluginPane;
	private TreeViewer viewer;

	public PluginsBar(Composite parent, int style) {
		super(parent, style | SWT.BORDER);
		System.out.println("create the pluginsbar");

		// init coloring
//		Color simulationPluginsBackgroundColor = new Color(parent.getDisplay(),
//				150, 150, 255);
//		setBackground(simulationPluginsBackgroundColor);

		// initialize this objects behaviour within the parent
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		pluginsBarGridData.widthHint = 200;
		setLayoutData(pluginsBarGridData);

		// init layout of objects in this object
		GridLayout gridLayout = new GridLayout(1, false);
		this.setLayout(gridLayout);
		
		Label pluginsBarTitle = new Label(this, SWT.NONE);
		pluginsBarTitle.setText("Simulatios-Modul Explorer");

		Composite viewerComposite = new Composite(this, SWT.NONE);
		GridData gridData = new GridData();
		gridData.verticalAlignment = GridData.FILL;
		gridData.grabExcessVerticalSpace = true;
		gridData.widthHint = 190;
		viewerComposite.setLayoutData(gridData);
		viewerComposite.setLayout(new FillLayout());
		
		viewer = new TreeViewer(viewerComposite, SWT.BORDER_SOLID);
		viewer.setContentProvider(new PluginsBarTreeContentProvider());
		viewer.setLabelProvider(new PluginsBarTreeLabelProvider());
		
		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				if (event.getSelection() instanceof IStructuredSelection) {
					IStructuredSelection selection = (IStructuredSelection) event
							.getSelection();
					if (selection.getFirstElement() instanceof ISimulationPluginPageContentWrapper) {
						ISimulationPluginPageContentWrapper newForegroundPaneContent = ((ISimulationPluginPageContentWrapper) selection
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
