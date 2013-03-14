package cbdt.view.parameters.engineconfig.widgetswrapper;

import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;


public class NaiveConfigWidgetsWrapper extends AbstractConfigWidgetsWrapper {

	private Label label;

	public NaiveConfigWidgetsWrapper(Composite parent) {
		label = new Label(parent, SWT.NONE);
		label.setText("Naive Computation");
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setParent(Composite parent) {
		label.setParent(parent);
	}

}
