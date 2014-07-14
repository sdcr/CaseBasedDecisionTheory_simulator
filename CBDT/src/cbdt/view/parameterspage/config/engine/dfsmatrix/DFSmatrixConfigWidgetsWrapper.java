package cbdt.view.parameterspage.config.engine.dfsmatrix;

import java.util.Observable;

import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.config.engine.IEngineConfigController;
import cbdt.view.parameterspage.config.engine.AbstractEngineConfigWidgetsWrapper;

/**
 * The {@link AbstractEngineConfigWidgetsWrapper} subclass for the DFSmatrix
 * algorithm. There are no configurations to set for this algorithm.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class DFSmatrixConfigWidgetsWrapper extends
		AbstractEngineConfigWidgetsWrapper {

	@Override
	public void update(Observable arg0, Object arg1) {
	}

	@Override
	public void setParent(Composite parent) {
	}

	@Override
	public void setEngineConfigController(IEngineConfigController configController) {
	}

	@Override
	public boolean hasContentToShow() {
		return false;
	}
}
