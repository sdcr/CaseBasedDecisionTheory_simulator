package cbdt.view.parameters.menu;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

public class ParametersMenuFactory {

	public void createParameterMenuItems(Menu parent){
		ArrayList<MenuItem> parameterMenuItems = new ArrayList<MenuItem>();
		
		MenuItem saveParameters = new MenuItem(parent, SWT.PUSH);
		saveParameters.setText("Save parameters");
		
		MenuItem loadParameters = new MenuItem(parent, SWT.PUSH);
		loadParameters.setText("Open parameters");
		
		parameterMenuItems.add(saveParameters);
		parameterMenuItems.add(loadParameters);
	}
}
