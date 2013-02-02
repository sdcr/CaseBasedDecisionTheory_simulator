package simulation.core.view;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class PluginsBarItemContentProvider implements ITreeContentProvider {

	private ISimulationPlugin plugin;
	
	@Override
	public void dispose() {
	}

	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.plugin = (ISimulationPlugin) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
		List<ISimulationPlugin> list = new ArrayList<ISimulationPlugin>();
		list.add(plugin);
		return list.toArray();
	}

	@Override
	public Object[] getChildren(Object parentElement) {
		if (parentElement instanceof ISimulationPlugin){
			return ((ISimulationPlugin) parentElement).getPaneContents().toArray();
		}
		return null;
	}

	@Override
	public Object getParent(Object element) {
		return null;
	}

	@Override
	public boolean hasChildren(Object element) {
		if (element instanceof ISimulationPlugin 
				&& !((ISimulationPlugin) element).getPaneContents().isEmpty())
			return true;
		return false;
	}

	
}
