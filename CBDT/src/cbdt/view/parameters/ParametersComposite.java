package cbdt.view.parameters;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.controller.Controller;
import cbdt.model.Parameters;
import cbdt.view.AbstractControllerAccessComposite;
import cbdt.view.parameters.actoraction.ActorActionsComposite;

/**
 * This composite contains all view elements that deal with the user's parameter input.
 * @author S-lenovo
 */
public class ParametersComposite extends AbstractControllerAccessComposite implements Observer{

	private ActorActionsComposite actorActionsComposite;

	public ParametersComposite(Composite parent, int style, Controller controller) {
		super(parent, style| SWT.BORDER, controller);

		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		rowLayout.marginTop = 20;
		this.setLayout(rowLayout);	
		
		createParametersTitleLabel();		
		actorActionsComposite = new ActorActionsComposite(this, SWT.NONE, controller);
	}

	/**
	 * Creates the title label for this composite.
	 */
	private void createParametersTitleLabel() {
		Label parameterLabel = new Label(this, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(this.getDisplay(), labelFontData));
	}

	/**
	 * Initializes the parameter view elements with an existing parameters model. 
	 * @param parameters The parameters model from which to initialize this view.
	 */
	public void setParametersModel(Parameters parameters) {
		parameters.addObserver(this);
		actorActionsComposite.setParametersModel(parameters);
	}

	@Override
	public void update(Observable o, Object arg) {
		//TODO implement
	}
	
}
