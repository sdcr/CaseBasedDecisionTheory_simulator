package cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;

public class DFSkeepTreeConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	public DFSkeepTreeConfigWidgetsWrapper(Composite parent) {
		super(parent);
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		super.update(arg0, arg1);
	}

	@Override
	public void setParent(Composite parent) {
		super.setParent(parent);
	}

	@Override
	public void setConfigController(
			AbstractEngineConfigController configController) {
		super.setConfigController(configController);
	}

}
