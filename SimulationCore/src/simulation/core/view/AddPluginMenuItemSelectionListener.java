package simulation.core.view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;

import simulation.core.control.Controller;

public class AddPluginMenuItemSelectionListener implements SelectionListener{

	private Shell shell;
	private Controller controller;

	public AddPluginMenuItemSelectionListener(Shell shell, Controller controller) {
		this.shell = shell;
		this.controller = controller;
	}
	
	@Override
	public void widgetSelected(SelectionEvent e) {
        FileDialog fd = new FileDialog(shell, SWT.OPEN);
        fd.setText("Add a plugin");
        fd.setFilterPath("C:/");
        String[] filterExt = { "*.jar", "*.*" };
        fd.setFilterExtensions(filterExt);
        String selected = fd.open();
        
        if(selected!=null){
        	controller.addISimulationPlugin(selected);
        }
	}

	@Override
	public void widgetDefaultSelected(SelectionEvent e) {
	}
}
