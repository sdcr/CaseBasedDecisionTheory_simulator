package cbdt.view.parameters.engineconfig.widgetswrapper;

import org.eclipse.swt.widgets.Composite;

import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.NaiveEngineConfig;
import cbdt.view.parameters.engineconfig.widgetswrapper.naiveconfig.NaiveConfigWidgetsWrapper;


public class ConfigWidgetsWrapperFactory {

	NaiveConfigWidgetsWrapper naiveConfigWidgetsWrapper;
	
	/**
	 * 
	 * @param config
	 * @return The configComposite belonging to the config.
	 */
	public AbstractConfigWidgetsWrapper getConfigComposite(AbstractEngineConfiguration config, Composite parent){
		if(config instanceof NaiveEngineConfig){
			if(naiveConfigWidgetsWrapper == null)
				naiveConfigWidgetsWrapper = new NaiveConfigWidgetsWrapper(parent);
			naiveConfigWidgetsWrapper.setParent(parent);
			return naiveConfigWidgetsWrapper;
		}
		return null;
	}
}
