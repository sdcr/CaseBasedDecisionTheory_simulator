package cbdt.view.parameters.engineconfig.widgetswrapper;

import org.eclipse.swt.widgets.Composite;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptreeconfig.DFSkeepTreeConfigWidgetsWrapper;

public class ConfigWidgetsWrapperFactory {

	DFSkeepTreeConfigWidgetsWrapper naiveConfigWidgetsWrapper;

	/**
	 * 
	 * @param config
	 * @return The configComposite belonging to the config.
	 */
	public AbstractConfigWidgetsWrapper getConfigComposite(
			AbstractEngineConfiguration config, Composite parent) {
		if (config instanceof DFSkeepTreeEngineConfig) {
			if (naiveConfigWidgetsWrapper == null)
				naiveConfigWidgetsWrapper = new DFSkeepTreeConfigWidgetsWrapper(
						parent);
			naiveConfigWidgetsWrapper.setParent(parent);
			return naiveConfigWidgetsWrapper;
		}
		return null;
	}
}
