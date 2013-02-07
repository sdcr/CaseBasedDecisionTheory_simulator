package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.controller.Controller;
import cbdt.model.CBDTSimulationParameters;
import cbdt.view.parameters.actoraction.ActorActionsComposite;

public class ParameterComposite extends AbstractControllerAccessComposite {

	private ActorActionsComposite actorActionsComposite;

	public ParameterComposite(Composite parent, int style, Controller controller) {
		super(parent, style, controller);

		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		rowLayout.marginTop = 20;
		this.setLayout(rowLayout);	
		
		createParametersTitleLabel(this);		
		actorActionsComposite = new ActorActionsComposite(this, SWT.NONE, controller);
	}

	private void createParametersTitleLabel(Composite parameterComposite) {
		Label parameterLabel = new Label(parameterComposite, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(parameterComposite.getDisplay(), labelFontData));
	}

	public void initialize(CBDTSimulationParameters parameters) {
		actorActionsComposite.initialize(parameters.getActorActions());
	}
	
}
