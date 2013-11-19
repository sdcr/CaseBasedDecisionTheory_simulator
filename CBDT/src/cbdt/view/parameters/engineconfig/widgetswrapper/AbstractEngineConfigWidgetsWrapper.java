package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameters.config.engine.AbstractEngineConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfig;

public abstract class AbstractEngineConfigWidgetsWrapper implements Observer {

	public abstract void setParent(Composite parent);

	public abstract void setConfigController(
			AbstractEngineConfigController configController);
	
	public void setEngineConfigModel(AbstractEngineConfig config)
	{
		config.addObserver(this);
		update(config, null);
	}
	
	public boolean hasContentToShow(){
		return true;
	}
}
