package simulation.core.view.pluginsbar;

import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.core.control.SimulationPluginManager;
import simulation.core.view.ForegroundManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class PluginsBar extends Composite {

	private TreeViewer viewer;

	public PluginsBar(Composite parent, int style) {
		super(parent, style | SWT.BORDER);
		System.out.println("create the pluginsbar");
		
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

		createTreeViewer();
	}

	private void createTreeViewer() {
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
	}

	public void setPluginPane(ForegroundManager pluginPane) {
		viewer.addSelectionChangedListener(
				new PluginsBarSelectionChangeListener(pluginPane));
	}

	public void update(SimulationPluginManager o) {
		viewer.setInput(((SimulationPluginManager)o).getISimulationPlugins());
	}

}
