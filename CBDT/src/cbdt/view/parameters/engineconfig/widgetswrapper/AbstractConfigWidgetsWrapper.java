package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.engineconfig.AbstractEngineConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;

public abstract class AbstractConfigWidgetsWrapper implements Observer {

	public abstract void update(Observable arg0, Object arg1);

	public abstract void setParent(Composite parent);

	public abstract void setConfigController(
			AbstractEngineConfigController configController);

	public void setEngineConfigModel(AbstractEngineConfiguration config) {
		config.addObserver(this);
		update(config, null);
	}

}
