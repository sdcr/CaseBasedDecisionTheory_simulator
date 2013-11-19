package cbdt.view.parameterspage.parameters;

import org.eclipse.swt.layout.GridData;

public class SimpleParameterGridDataFactory {

	public SimpleParameterGridDataFactory() {
	}
	
	public GridData getCompositesGridData(){
		GridData gridData = new GridData();
		gridData.widthHint = 200;
		return gridData;
	}
	
	public GridData getTextGridData(){
		GridData textGridData = new GridData();
		textGridData.widthHint = 300;
		textGridData.grabExcessHorizontalSpace = true;
		return textGridData;
	}
	
}
