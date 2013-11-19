package cbdt.view.parameters.engineconfig.widgetswrapper;

import org.eclipse.swt.widgets.Composite;

import cbdt.model.parameters.engineconfig.AbstractEngineConfig;
import cbdt.model.parameters.engineconfig.DFSkeepTreeEngineConfig;
import cbdt.model.parameters.engineconfig.DFSmatrixEngineConfig;
import cbdt.model.parameters.engineconfig.DFSmatrixHighPrecEngineConfig;
import cbdt.view.parameters.engineconfig.widgetswrapper.dfskeeptree.DFSkeepTreeConfigWidgetsWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.dfsmatrix.DFSmatrixConfigWidgetsWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.dfsmatrixhp.DFSmatrixHighPrecisionConfigWidgetsWrapper;

public class EngineConfigWidgetsWrapperFactory {

	private DFSkeepTreeConfigWidgetsWrapper naiveConfigWidgetsWrapper;
	private DFSmatrixConfigWidgetsWrapper matrixConfigWidgetsWrapper;
	private DFSmatrixHighPrecisionConfigWidgetsWrapper matrixHighPrecConfigWidgetsWrapper;

	/**
	 * 
	 * @param config
	 * @return The configComposite belonging to the config.
	 * @throws NoWidgetWrapperException 
	 */
	public AbstractEngineConfigWidgetsWrapper getEngineConfigWidgetWrapper(
			AbstractEngineConfig config, Composite parent) throws NoWidgetWrapperException {
		if (config instanceof DFSkeepTreeEngineConfig) {
			if (naiveConfigWidgetsWrapper == null)
				naiveConfigWidgetsWrapper = new DFSkeepTreeConfigWidgetsWrapper(
						parent);
//			naiveConfigWidgetsWrapper.setParent(parent);
			return naiveConfigWidgetsWrapper;
		}
		if (config instanceof DFSmatrixEngineConfig) {
			if (matrixConfigWidgetsWrapper == null) {
				matrixConfigWidgetsWrapper = new DFSmatrixConfigWidgetsWrapper();
			}
//			matrixConfigWidgetsWrapper.setParent(parent);
			return matrixConfigWidgetsWrapper;
		}
		if (config instanceof DFSmatrixHighPrecEngineConfig) {
			if (matrixHighPrecConfigWidgetsWrapper == null)
				matrixHighPrecConfigWidgetsWrapper = new DFSmatrixHighPrecisionConfigWidgetsWrapper(
						parent);
//			matrixHighPrecConfigWidgetsWrapper.setParent(parent);
			return matrixHighPrecConfigWidgetsWrapper;
		}
		throw new NoWidgetWrapperException("There are no widgets defined for this engine configuration in the widget factory.");
	}
}
