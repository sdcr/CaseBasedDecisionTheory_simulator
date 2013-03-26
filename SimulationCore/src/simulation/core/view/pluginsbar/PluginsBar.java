package simulation.core.view.pluginsbar;

import java.util.List;

import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import simulation.core.view.ForegroundManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

/**
 * This class is a Composite which shows the available ISimulationPlugins and their showable
 * page contents in a tree viewer.
 * @author S-lenovo
 */
public class PluginsBar extends Composite {

	private TreeViewer viewer;
	private PluginsBarSelectionChangeListener pluginsBarSelectionChangeListener;
	private ForegroundManager foregroundManager;

	public PluginsBar(Composite parent, int style) {
		super(parent, style | SWT.BORDER);
		
		GridData pluginsBarGridData = new GridData();
		pluginsBarGridData.verticalAlignment = GridData.FILL;
		pluginsBarGridData.grabExcessVerticalSpace = true;
		pluginsBarGridData.widthHint = 200;
		this.setLayoutData(pluginsBarGridData);

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
	
	/**
	 * Set the ForegroundManager which should be used when a plugin page should be put 
	 * in foreground.
	 * @param foregroundManager
	 */
	public void setForegroundManager(ForegroundManager foregroundManager){
		this.foregroundManager = foregroundManager;
		if(pluginsBarSelectionChangeListener!=null)
			viewer.removeSelectionChangedListener(pluginsBarSelectionChangeListener);
		pluginsBarSelectionChangeListener = new PluginsBarSelectionChangeListener(foregroundManager);
		viewer.addSelectionChangedListener(pluginsBarSelectionChangeListener);
		foregroundManager.setTreeViewer(viewer);
	}
	
	/**
	 * Update the treeviewer with the available ISimulationPlugins.
	 * @param pluginManager
	 */
	public void update(List<ISimulationPlugin> plugins) {
		viewer.setInput(plugins);
		viewer.expandAll();
		try{
			foregroundManager.setToForeground(plugins.get(0).getPageFactories().get(0));
		}catch(Exception e){
		}
	}

}
