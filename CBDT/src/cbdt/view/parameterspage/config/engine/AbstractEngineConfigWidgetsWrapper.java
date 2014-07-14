package cbdt.view.parameterspage.config.engine;

import java.util.Observer;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.config.engine.IEngineConfigController;
import cbdt.model.config.engine.AbstractEngineConfig;

/**
 * This class is an abstract wrapper class. Its subclasses are wrappers for the
 * widgets of {@link AbstractEngineConfig}s.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public abstract class AbstractEngineConfigWidgetsWrapper implements Observer {

	public abstract void setParent(Composite parent);

	/**
	 * Set the {@link IEngineConfigController} for the wrapped widgets.
	 * 
	 * @param configController
	 */
	public abstract void setEngineConfigController(
			IEngineConfigController configController);

	/**
	 * Returns whether there is content to show to the user.
	 * 
	 * @return
	 */
	public abstract boolean hasContentToShow();

	/**
	 * Set the {@link AbstractEngineConfig} model.
	 * 
	 * @param config
	 */
	public void setEngineConfigModel(AbstractEngineConfig config) {
		config.addObserver(this);
		update(config, null);
	}

}
