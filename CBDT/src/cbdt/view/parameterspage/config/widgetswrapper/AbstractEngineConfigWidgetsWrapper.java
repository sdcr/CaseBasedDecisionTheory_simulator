package cbdt.view.parameterspage.config.widgetswrapper;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.config.engine.IEngineConfigController;
import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * This class is an abstract wrapper class. Its subclasses are wrappers for
 * EngineConfigWrappers.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public abstract class AbstractEngineConfigWidgetsWrapper implements Observer {

	public abstract void setParent(Composite parent);

	public abstract void setConfigController(
			IEngineConfigController configController);

	public void setEngineConfigModel(AbstractEngineConfig config) {
		config.addObserver(this);
		update(config, null);
	}

	public boolean hasContentToShow() {
		return true;
	}
}
