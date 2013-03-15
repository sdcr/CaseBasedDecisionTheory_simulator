package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.ParametersPageController;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
import cbdt.view.parameters.actoraction.ActorActionsComposite;
import cbdt.view.parameters.aspirationlevel.AbstractAspirationLevelParameterComposite;
import cbdt.view.parameters.aspirationlevel.AspirationLevelDiscountComposite;
import cbdt.view.parameters.aspirationlevel.AspirationLevelIncrementComposite;
import cbdt.view.parameters.aspirationlevel.InitialAspirationLevelComposite;
import cbdt.view.parameters.engineconfig.ConfigWidgetsWrapperManager;

/**
 * This composite contains all view elements that deal with the user's parameter
 * input.
 * 
 * @author S-lenovo
 */
public class ParametersPage extends AbstractControllerAccessComposite {

	private ActorActionsComposite actorActionsComposite;
	private AbstractAspirationLevelParameterComposite initialAspirationLevelComposite;
	private AbstractAspirationLevelParameterComposite aspirationLevelIncrementComposite;
	private AbstractAspirationLevelParameterComposite aspirationLevelDiscountComposite;
	private ConfigWidgetsWrapperManager configsComposite;
	private Composite parametersWrapper;

	public ParametersPage(Composite parent, int style,
			ParametersPageController controller) {
		super(parent, style, controller);

		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.spacing = 20;
		this.setLayout(rowLayout);

		parametersWrapper = new Composite(this, SWT.NONE);

		GridLayout gridLayout = new GridLayout(2, false);
		parametersWrapper.setLayout(gridLayout);

		createParametersTitleLabel();

		createParameterLabel("Actor actions:");
		actorActionsComposite = new ActorActionsComposite(parametersWrapper,
				SWT.NONE, controller);

		createParameterLabel("Initial aspiration level:");
		initialAspirationLevelComposite = new InitialAspirationLevelComposite(
				parametersWrapper, getController());
		createParameterLabel("Aspiration level increment:");
		aspirationLevelIncrementComposite = new AspirationLevelIncrementComposite(
				parametersWrapper, getController());
		createParameterLabel("Aspiration discount factor:");
		aspirationLevelDiscountComposite = new AspirationLevelDiscountComposite(
				parametersWrapper, getController());

		Composite spacerComposite = new Composite(parametersWrapper, SWT.NONE);
		GridData spacerGridData = new GridData();
		spacerGridData.heightHint = 25;
		spacerGridData.horizontalSpan = 2;
		spacerComposite.setLayoutData(spacerGridData);

		createEngineConfigTitleLabel();

		configsComposite = new ConfigWidgetsWrapperManager(parametersWrapper,
				getController());

		Button startComputation = new Button(this, SWT.PUSH | SWT.END);
		startComputation.setText("Start computation");
	}

	private void createEngineConfigTitleLabel() {
		Label configLabel = new Label(parametersWrapper, SWT.NONE);
		configLabel.setText("Engine-Configuration");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		configLabel.setFont(new Font(this.getDisplay(), labelFontData));

		GridData parameterLabelGridData = new GridData();
		parameterLabelGridData.horizontalSpan = 2;
		// parameterLabelGridData.heightHint = 50;
		configLabel.setLayoutData(parameterLabelGridData);
	}

	/**
	 * @return Returns whether the aspiration level parameter fields have a
	 *         valid value.
	 */
	public boolean hasValidAspirationLevelParameters() {
		if (initialAspirationLevelComposite.hasValidValue()
				&& aspirationLevelIncrementComposite.hasValidValue()
				&& aspirationLevelDiscountComposite.hasValidValue())
			return true;
		return false;
	}

	/**
	 * Create a label that is used to denote a parameter which can be entered by
	 * the user.
	 */
	private void createParameterLabel(String labelText) {
		Label actorActionsLabel = new Label(parametersWrapper, SWT.NONE);
		actorActionsLabel.setText(labelText);

		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		actorActionsLabel.setLayoutData(gridData);
	}

	/**
	 * Creates the title label for this composite.
	 */
	private void createParametersTitleLabel() {
		Label parameterLabel = new Label(parametersWrapper, SWT.NONE);
		parameterLabel.setText("Parameter-Eingabe");
		FontData labelFontData = new FontData("Arial", 11, SWT.BOLD);
		parameterLabel.setFont(new Font(this.getDisplay(), labelFontData));

		GridData parameterLabelGridData = new GridData();
		parameterLabelGridData.horizontalSpan = 2;
		parameterLabel.setLayoutData(parameterLabelGridData);
	}

	/**
	 * Initializes the parameter view elements with an existing parameters
	 * model.
	 * 
	 * @param parameters
	 *            The parameters model from which to initialize this view.
	 */
	public void setParametersModel(Parameters parameters) {
		actorActionsComposite.setParametersModel(parameters);
		initialAspirationLevelComposite.setParametersModel(parameters);
		aspirationLevelIncrementComposite.setParametersModel(parameters);
		aspirationLevelDiscountComposite.setParametersModel(parameters);
	}

	public void setConfigChoiceModel(EngineConfigChoice configChoice) {
		configsComposite.setConfigChoiceModel(configChoice);
	}
}
