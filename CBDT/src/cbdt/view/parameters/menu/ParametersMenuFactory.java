package cbdt.view.parameters.menu;

import java.util.ArrayList;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;

import cbdt.control.ParametersController;

public class ParametersMenuFactory {

	public void createParameterMenuItems(Shell shell, Menu parent, ParametersController controller){
		ArrayList<MenuItem> parameterMenuItems = new ArrayList<MenuItem>();
		
		MenuItem saveParameters = new MenuItem(parent, SWT.PUSH);
		saveParameters.setText("&Save parameters");
		saveParameters.addSelectionListener(new SaveParametersSelectionListener(shell, controller));
		
		MenuItem openParameters = new MenuItem(parent, SWT.PUSH);
		openParameters.setText("&Open parameters");
		openParameters.addSelectionListener(new OpenParametersSelectionListener(shell, controller));
		
		parameterMenuItems.add(saveParameters);
		parameterMenuItems.add(openParameters);
	}
}
