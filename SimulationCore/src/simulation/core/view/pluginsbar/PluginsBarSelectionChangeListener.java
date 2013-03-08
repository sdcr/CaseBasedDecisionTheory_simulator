package simulation.core.view.pluginsbar;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import simulation.core.view.ForegroundManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;

public class PluginsBarSelectionChangeListener implements ISelectionChangedListener{

	private ForegroundManager foregroundManager;

	public PluginsBarSelectionChangeListener(ForegroundManager foregroundManager) {
		this.foregroundManager = foregroundManager;
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) event.getSelection();
			if (selection.getFirstElement() instanceof ISimulationPluginPageFactory) {
				ISimulationPluginPageFactory newForegroundPaneContent = ((ISimulationPluginPageFactory) selection
						.getFirstElement());
				foregroundManager.setToForeground(newForegroundPaneContent);
			}
		}		
	}

}