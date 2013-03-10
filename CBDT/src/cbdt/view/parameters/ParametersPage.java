package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.ParametersController;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameters.actoraction.ActorActionsComposite;
import cbdt.view.parameters.aspirationlevel.AspirationLevelDiscountComposite;
import cbdt.view.parameters.aspirationlevel.AspirationLevelIncrementComposite;
import cbdt.view.parameters.aspirationlevel.InitialAspirationLevelComposite;
import cbdt.view.parameters.aspirationlevel.SimpleParameterComposite;

/**
 * This composite contains all view elements that deal with the user's parameter input.
 * @author S-lenovo
 */
public class ParametersPage extends AbstractControllerAccessComposite {

	private ActorActionsComposite actorActionsComposite;
	private SimpleParameterComposite initialAspirationLevelComposite;
	private SimpleParameterComposite aspirationLevelIncrementComposite;
	private SimpleParameterComposite aspirationLevelDiscountComposite;

	public ParametersPage(Composite parent, int style, ParametersController controller) {
		super(parent, style, controller);
		
		GridLayout gridLayout = new GridLayout(2, false);
		this.setLayout(gridLayout);
		
		createParametersTitleLabel();		
		
		createParameterLabel("Actor actions:");
		actorActionsComposite = new ActorActionsComposite(this, SWT.NONE, controller);

		createParameterLabel("Initial aspiration level:");
		initialAspirationLevelComposite = new InitialAspirationLevelComposite(this, getController());
		createParameterLabel("Aspiration level increment:");
		aspirationLevelIncrementComposite = new AspirationLevelIncrementComposite(this, getController());
		createParameterLabel("Aspiration discount factor:");
		aspirationLevelDiscountComposite = new AspirationLevelDiscountComposite(this, getController());
	}

	/**
	 * @return Returns whether the aspiration level parameter fields have a valid value.
	 */
	public boolean hasValidAspirationLevelParameters(){
		if(initialAspirationLevelComposite.hasValidValue()
				&& aspirationLevelIncrementComposite.hasValidValue()
				&& aspirationLevelDiscountComposite.hasValidValue())
			return true;
		return false;
	}
	
	/**
	 * Create a label that is used to denote a parameter which can be entered by the user.
	 */
	private void createParameterLabel(String labelText) {
		Label actorActionsLabel = new Label(this, SWT.NONE);
		actorActionsLabel.setText(labelText);	
		
		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(gridData);
	}
	
	/**
	 * Creates the title label for this composite.
	 */
	private void createParametersTitleLabel() {
		Label parameterLabel = new Label(this, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(this.getDisplay(), labelFontData));
		
		GridData parameterLabelGridData = new GridData();
		parameterLabelGridData.horizontalSpan = 2;
		parameterLabel.setLayoutData(parameterLabelGridData);
	}

	/**
	 * Initializes the parameter view elements with an existing parameters model. 
	 * @param parameters The parameters model from which to initialize this view.
	 */
	public void setParametersModel(Parameters parameters) {
		actorActionsComposite.setParametersModel(parameters);
		initialAspirationLevelComposite.setParametersModel(parameters);
		aspirationLevelIncrementComposite.setParametersModel(parameters);
		aspirationLevelDiscountComposite.setParametersModel(parameters);
	}
	
}
