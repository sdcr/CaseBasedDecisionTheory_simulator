package simulation.core.view;

import java.util.List;

import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPlugin;

public class PluginsBarTreeContentProvider implements ITreeContentProvider {

	private List<ISimulationPlugin> plugins;
	
	@Override
	public void dispose() {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
		this.plugins = (List<ISimulationPlugin>) newInput;
	}

	@Override
	public Object[] getElements(Object inputElement) {
//		List<ISimulationPlugin> list = new ArrayList<ISimulationPlugin>();
//		list.add(plugin);
		return plugins.toArray();
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
