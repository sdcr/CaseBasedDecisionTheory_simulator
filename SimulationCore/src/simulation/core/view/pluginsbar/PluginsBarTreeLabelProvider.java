package simulation.core.view.pluginsbar;

import org.eclipse.jface.viewers.LabelProvider;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;
import simulation.extensionpoint.simulationplugin.definition.AbstractPluginPageCompositeWrapper;

public class PluginsBarTreeLabelProvider extends LabelProvider{

	@Override
	public String getText(Object element) {
		if(element instanceof ISimulationPlugin)
			return ((ISimulationPlugin)element).getName();
		return ((AbstractPluginPageCompositeWrapper)element).getName();
	}
}
