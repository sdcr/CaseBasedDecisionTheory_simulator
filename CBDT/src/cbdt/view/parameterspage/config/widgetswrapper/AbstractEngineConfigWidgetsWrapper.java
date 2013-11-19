package cbdt.view.parameterspage.config.widgetswrapper;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.config.engine.AbstractEngineConfigController;
import cbdt.model.config.AbstractEngineConfig;

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
