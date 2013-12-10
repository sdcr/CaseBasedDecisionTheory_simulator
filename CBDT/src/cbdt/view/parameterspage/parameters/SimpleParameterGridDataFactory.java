package cbdt.view.parameterspage.parameters;

import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Text;

/**
 * A factory class which produces {@link GridData} objects for
 * {@link SimpleParameterComposite}s.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class SimpleParameterGridDataFactory {

	/**
	 * @return The {@link GridData} object for the
	 *         {@link SimpleParameterComposite}.
	 */
	public GridData getCompositesGridData() {
		GridData gridData = new GridData();
		gridData.widthHint = 200;
		return gridData;
	}

	/**
	 * @return The {@link GridData} object for the {@link Text} object of the
	 *         {@link SimpleParameterComposite}.
	 */
	public GridData getTextGridData() {
		GridData textGridData = new GridData();
		textGridData.widthHint = 300;
		textGridData.grabExcessHorizontalSpace = true;
		return textGridData;
	}

}
