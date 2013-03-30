package cbdt.view.parameters;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.ParametersPageController;
import cbdt.model.parameters.Parameters;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
import cbdt.view.LabelFactory;
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
	private AspirationLevelIncrementComposite aspirationLevelIncrementComposite;
	private AbstractAspirationLevelParameterComposite aspirationLevelDiscountComposite;
	private ConfigWidgetsWrapperManager configsCompositeWrapperManager;
	private Composite parametersWrapper;

	public ParametersPage(Composite parent, int style,
			ParametersPageController controller) {
		super(parent, style, controller);
		
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.spacing = 20;
		rowLayout.marginBottom = 50;
		this.setLayout(rowLayout);

		parametersWrapper = new Composite(this, SWT.NONE);

		GridLayout gridLayout = new GridLayout(2, false);
		parametersWrapper.setLayout(gridLayout);

		createParametersTitleLabel();

		createParameterLabel("Actor actions:");
		actorActionsComposite = new ActorActionsComposite(parametersWrapper,
				SWT.NONE, controller);

		
		Label initialAspirationLabel = createParameterLabel("Initial aspiration level:");
		initialAspirationLabel.setToolTipText(InitialAspirationLevelComposite.TOOL_TIP_TEXT);
		initialAspirationLevelComposite = new InitialAspirationLevelComposite(
				parametersWrapper, getController());
		
		Label aspirationDiscuntLabel = createParameterLabel("Prev. aspiration weight factor:");
		aspirationDiscuntLabel.setToolTipText(AspirationLevelDiscountComposite.TOOL_TIP_TEXT);
		aspirationLevelDiscountComposite = new AspirationLevelDiscountComposite(
				parametersWrapper, getController());
		
		Label aspirationLevelIncrementLabel = createParameterLabel("Aspiration level increment:");
		aspirationLevelIncrementLabel.setToolTipText(AspirationLevelIncrementComposite.TOOL_TIP_TEXT);
		aspirationLevelIncrementComposite = new AspirationLevelIncrementComposite(
				parametersWrapper, getController());

		Composite spacerComposite = new Composite(parametersWrapper, SWT.NONE);
		GridData spacerGridData = new GridData();
		spacerGridData.heightHint = 25;
		spacerGridData.horizontalSpan = 2;
		spacerComposite.setLayoutData(spacerGridData);

		createEngineConfigTitleLabel();

		configsCompositeWrapperManager = new ConfigWidgetsWrapperManager(parametersWrapper,
				getController());

		Button startComputationButton = new Button(this, SWT.PUSH | SWT.END);
		startComputationButton.setText("Start computation");
		startComputationButton.addSelectionListener(new StartComputationSelectionListener(controller));
	}

	public ConfigWidgetsWrapperManager getConfigsCompositeWrapperManager() {
		return configsCompositeWrapperManager;
	}

	private void createEngineConfigTitleLabel() {
		LabelFactory factory = new LabelFactory();
		Label configLabel = factory.createTitleLabel(parametersWrapper, "Engine configuration:");

		GridData parameterLabelGridData = new GridData();
		parameterLabelGridData.horizontalSpan = 2;
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
	private Label createParameterLabel(String labelText) {
		Label label = new Label(parametersWrapper, SWT.NONE);
		label.setText(labelText);

		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		gridData.widthHint = 180;
		label.setLayoutData(gridData);
		return label;
	}

	/**
	 * Creates the title label for this composite.
	 */
	private void createParametersTitleLabel() {
		LabelFactory factory = new LabelFactory();
		Label parameterLabel = factory.createTitleLabel(parametersWrapper, "Parameter values:");

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
		configsCompositeWrapperManager.setConfigChoiceModel(configChoice);
	}
}
