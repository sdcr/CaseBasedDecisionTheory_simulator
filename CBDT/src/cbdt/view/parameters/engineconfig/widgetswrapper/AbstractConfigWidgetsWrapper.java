package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public abstract class AbstractConfigWidgetsWrapper implements Observer {

	protected static final int MARGIN_TOP = 10;
	
	public void setEngineConfigModel(AbstractEngineConfiguration config)
	{
		config.addObserver(this);
		update(config, null);
	}

	public abstract void setParent(Composite parent);

	public abstract void setConfigController(
			AbstractEngineConfigController configController);

}
