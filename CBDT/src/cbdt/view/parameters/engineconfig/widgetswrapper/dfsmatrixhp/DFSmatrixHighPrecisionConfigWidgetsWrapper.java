package cbdt.view.parameters.engineconfig.widgetswrapper.dfsmatrixhp;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;

public class DFSmatrixHighPrecisionConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	public DFSmatrixHighPrecisionConfigWidgetsWrapper(Composite parent) {
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

	@Override
	public void setEngineConfigModel(AbstractEngineConfiguration config) {
		// TODO Auto-generated method stub
		
	}

}
