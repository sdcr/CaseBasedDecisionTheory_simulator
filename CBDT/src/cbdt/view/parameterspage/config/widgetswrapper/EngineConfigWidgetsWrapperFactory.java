package cbdt.view.parameterspage.config.widgetswrapper;

import org.eclipse.swt.widgets.Composite;

import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.DFSkeepTreeEngineConfig;
import cbdt.model.config.DFSmatrixEngineConfig;
import cbdt.model.config.DFSmatrixHighPrecEngineConfig;
import cbdt.view.parameterspage.config.widgetswrapper.dfskeeptree.DFSkeepTreeConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.widgetswrapper.dfsmatrix.DFSmatrixConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.widgetswrapper.dfsmatrixhp.DFSmatrixHighPrecisionConfigWidgetsWrapper;

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
