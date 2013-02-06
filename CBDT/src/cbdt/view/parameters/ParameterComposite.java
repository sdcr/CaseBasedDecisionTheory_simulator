package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.view.parameters.actoraction.ActorActionParametersComposite;

public class ParameterComposite extends Composite {

	public ParameterComposite(Composite parent, int style) {
		super(parent, style);

		RowLayout rowLayout = new RowLayout();
		rowLayout.type=SWT.VERTICAL;
		rowLayout.marginTop = 20;
		this.setLayout(rowLayout);	
		
		createParametersTitleLabel(this);		
		ActorActionParametersComposite actorActionsComposite = new ActorActionParametersComposite(this, SWT.NONE);
	}

	private void createParametersTitleLabel(Composite parameterComposite) {
		Label parameterLabel = new Label(parameterComposite, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(parameterComposite.getDisplay(), labelFontData));
	}
	
}
