package cbdt.view.parameterspage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.view.LabelFactory;
import cbdt.view.parameterspage.config.ConfigWidgetsWrapperManager;
import cbdt.view.parameterspage.parameters.StartComputationSelectionListener;
import cbdt.view.parameterspage.parameters.actoraction.ActorActionWrapComposite;
import cbdt.view.parameterspage.parameters.aspirationlevel.AspirationLevelDiscountComposite;
import cbdt.view.parameterspage.parameters.aspirationlevel.AspirationLevelIncrementComposite;
import cbdt.view.parameterspage.parameters.aspirationlevel.InitialAspirationLevelComposite;

/**
 * This class is able to produce the view elements for the
 * {@link ParametersAndConfigPageComposite}.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ParametersAndConfigPageCompositeFactory {

	private Composite parametersParent;
	private ParametersConfigPageController pageController;

	/**
	 * Constructor. A new parent {@link Composite} is created within the given
	 * {@link ParametersAndConfigPageComposite}. For all view elements expect
	 * the Button to start the computation, that {@link Composite} will be used
	 * as a parent.
	 * 
	 * @param parametersAndConfigPageComposite
	 * @param pageController
	 */
	public ParametersAndConfigPageCompositeFactory(
			ParametersAndConfigPageComposite parametersAndConfigPageComposite,
			ParametersConfigPageController pageController) {

		this.pageController = pageController;
		this.parametersParent = new Composite(parametersAndConfigPageComposite,
				SWT.NONE);
		GridLayout gridLayout = new GridLayout(2, false);
		parametersParent.setLayout(gridLayout);
	}

	/**
	 * Adds a {@link Button} with which the computation can be started.
	 * 
	 * @param parametersAndConfigPageComposite
	 *            The parent {@link Composite} object of the {@link Button}.
	 */
	public void addStartComputationButton(
			Composite parametersAndConfigPageComposite) {
		Button startComputationButton = new Button(
				parametersAndConfigPageComposite, SWT.PUSH | SWT.END);
		startComputationButton.setText("Start computation");
		startComputationButton
				.addSelectionListener(new StartComputationSelectionListener(
						pageController));
	}

	/**
	 * Adds a {@link Composite} to create spacing.
	 */
	public void addSpacerComposite() {
		Composite spacerComposite = new Composite(parametersParent, SWT.NONE);
		GridData spacerGridData = new GridData();
		spacerGridData.heightHint = 25;
		spacerGridData.horizontalSpan = 2;
		spacerComposite.setLayoutData(spacerGridData);
	}

	/**
	 * Creates and returns an {@link AspirationLevelIncrementComposite}.
	 * 
	 * @return
	 */
	public AspirationLevelIncrementComposite addAspLevelIncrementView() {
		Label aspirationLevelIncrementLabel = addParametersLabel(
				parametersParent, "Aspiration level increment:");
		aspirationLevelIncrementLabel
				.setToolTipText(AspirationLevelIncrementComposite.TOOL_TIP_TEXT);
		return new AspirationLevelIncrementComposite(parametersParent,
				pageController.getParametersController());
	}

	/**
	 * Creates and returns a {@link AspirationLevelDiscountComposite}.
	 * 
	 * @return
	 */
	public AspirationLevelDiscountComposite addAspLevelDiscountView() {
		Label aspirationLevelDiscountLabel = addParametersLabel(
				parametersParent, "Prev. aspiration weight factor:");
		aspirationLevelDiscountLabel
				.setToolTipText(AspirationLevelDiscountComposite.TOOL_TIP_TEXT);
		return new AspirationLevelDiscountComposite(parametersParent,
				pageController.getParametersController());
	}

	/**
	 * Creates and returns an {@link InitialAspirationLevelComposite}.
	 * 
	 * @return
	 */
	public InitialAspirationLevelComposite addInitAspLevelView() {
		Label initialAspirationLevelLabel = addParametersLabel(
				parametersParent, "Initial aspiration level:");
		initialAspirationLevelLabel
				.setToolTipText(InitialAspirationLevelComposite.TOOL_TIP_TEXT);
		return new InitialAspirationLevelComposite(parametersParent,
				pageController.getParametersController());
	}

	/**
	 * Creates and returns an {@link ActorActionWrapComposite}.
	 * 
	 * @return
	 */
	public ActorActionWrapComposite addActorActionsView() {
		addParametersLabel(parametersParent, "Actor actions:");
		return new ActorActionWrapComposite(parametersParent, SWT.NONE,
				pageController.getParametersController());
	}

	/**
	 * Creates and returns a {@link ConfigWidgetsWrapperManager}.
	 * 
	 * @return
	 */
	public ConfigWidgetsWrapperManager createConfigsCompositeWrapperManager() {
		return new ConfigWidgetsWrapperManager(parametersParent, pageController);
	}

	/**
	 * Create a label that is used to denote a parameter which can be entered by
	 * the user.
	 */
	private Label addParametersLabel(Composite parent, String labelText) {
		Label label = new Label(parent, SWT.NONE);
		label.setText(labelText);

		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.BEGINNING;
		gridData.widthHint = 180;
		label.setLayoutData(gridData);
		return label;
	}

	/**
	 * Creates the title {@link Label} for the parameters view elements.
	 */
	public void addParametersTitleLabel() {
		Label parameterLabel = createTitleLabel();
		parameterLabel.setText("Parameter values:");
	}

	/**
	 * Creates the title {@link Label} for the parameters view elements.
	 */
	public void addConfigTitleLabel() {
		Label configLabel = createTitleLabel();
		configLabel.setText("Engine configuration:");
	}

	/**
	 * @return A {@link Label} to display a title without set text.
	 */
	private Label createTitleLabel() {
		LabelFactory factory = new LabelFactory();
		Label titleLabel = factory.createTitleLabel(parametersParent);

		GridData parameterLabelGridData = new GridData();
		parameterLabelGridData.horizontalSpan = 2;
		titleLabel.setLayoutData(parameterLabelGridData);

		return titleLabel;
	}
}