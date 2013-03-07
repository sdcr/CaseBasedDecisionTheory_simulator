package simulation.core.view.pluginsbar;

import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import simulation.core.view.ForegroundManager;
import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageWrapper;

public class PluginsBarSelectionChangeListener implements ISelectionChangedListener{

	private ForegroundManager pluginPane;

	public PluginsBarSelectionChangeListener(ForegroundManager pluginPane) {
		this.pluginPane = pluginPane;
	}
	
	@Override
	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelection() instanceof IStructuredSelection) {
			IStructuredSelection selection = (IStructuredSelection) event
					.getSelection();
			if (selection.getFirstElement() instanceof ISimulationPluginPageWrapper) {
				ISimulationPluginPageWrapper newForegroundPaneContent = ((ISimulationPluginPageWrapper) selection
						.getFirstElement());
				pluginPane.setToForeGround(newForegroundPaneContent);
			}
		}		
	}

}
