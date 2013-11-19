package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfig;

public abstract class AbstractConfigWidgetsWrapper implements Observer {
	
	public void setEngineConfigModel(AbstractEngineConfig config)
	{
		config.addObserver(this);
		update(config, null);
	}

	public abstract void setParent(Composite parent);

	public abstract void setConfigController(
			AbstractEngineConfigController configController);

	public boolean hasContentToShow(){
		return true;
	}
}
