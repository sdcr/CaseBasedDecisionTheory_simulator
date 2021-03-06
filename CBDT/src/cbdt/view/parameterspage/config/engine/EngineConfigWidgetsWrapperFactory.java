package cbdt.view.parameterspage.config.engine;

import org.eclipse.swt.widgets.Composite;

import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.config.engine.DFSkeepTreeEngineConfig;
import cbdt.model.config.engine.DFSmatrixEngineConfig;
import cbdt.model.config.engine.DFSmatrixHighPrecEngineConfig;
import cbdt.view.parameterspage.config.engine.dfskeeptree.DFSkeepTreeConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.engine.dfsmatrix.DFSmatrixConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.engine.dfsmatrixhp.DFSmatrixHighPrecisionConfigWidgetsWrapper;

/**
 * This class creates wrapper classes for the widgets of the different
 * {@link AbstractEngineConfig}s.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class EngineConfigWidgetsWrapperFactory {

	private DFSkeepTreeConfigWidgetsWrapper naiveConfigWidgetsWrapper;
	private DFSmatrixConfigWidgetsWrapper matrixConfigWidgetsWrapper;
	private DFSmatrixHighPrecisionConfigWidgetsWrapper matrixHighPrecConfigWidgetsWrapper;

	/**
	 * 
	 * @param config
	 * @return The configComposite belonging to the config.
	 * @throws NoWidgetWrapperFoundException
	 */
	public AbstractEngineConfigWidgetsWrapper getEngineConfigWidgetWrapper(
			AbstractEngineConfig config, Composite parent)
			throws NoWidgetWrapperFoundException {
		if (config instanceof DFSkeepTreeEngineConfig) {
			if (naiveConfigWidgetsWrapper == null)
				naiveConfigWidgetsWrapper = new DFSkeepTreeConfigWidgetsWrapper(
						parent);
			// naiveConfigWidgetsWrapper.setParent(parent);
			return naiveConfigWidgetsWrapper;
		}
		if (config instanceof DFSmatrixEngineConfig) {
			if (matrixConfigWidgetsWrapper == null) {
				matrixConfigWidgetsWrapper = new DFSmatrixConfigWidgetsWrapper();
			}
			// matrixConfigWidgetsWrapper.setParent(parent);
			return matrixConfigWidgetsWrapper;
		}
		if (config instanceof DFSmatrixHighPrecEngineConfig) {
			if (matrixHighPrecConfigWidgetsWrapper == null)
				matrixHighPrecConfigWidgetsWrapper = new DFSmatrixHighPrecisionConfigWidgetsWrapper(
						parent);
			// matrixHighPrecConfigWidgetsWrapper.setParent(parent);
			return matrixHighPrecConfigWidgetsWrapper;
		}
		throw new NoWidgetWrapperFoundException(
				"There are no widgets defined for this engine configuration in the widget factory.");
	}
}
