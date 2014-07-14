package cbdt.view.parameterspage;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;

import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.model.config.SimulationConfig;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.config.ConfigWidgetsWrapperManager;
import cbdt.view.parameterspage.parameters.actoraction.ActorActionWrapComposite;
import cbdt.view.parameterspage.parameters.aspirationlevel.AbstractAspirationLevelParameterComposite;
import cbdt.view.parameterspage.parameters.aspirationlevel.AspirationLevelIncrementComposite;

/**
 * This composite contains the view elements that deal with the user's parameter
 * input.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ParametersAndConfigPageComposite extends Composite {

	private ActorActionWrapComposite actorActionsComposite;

	private AbstractAspirationLevelParameterComposite initialAspirationLevelComposite;
	private AspirationLevelIncrementComposite aspirationLevelIncrementComposite;
	private AbstractAspirationLevelParameterComposite aspirationLevelDiscountComposite;

	private ConfigWidgetsWrapperManager configsCompositeWrapperManager;

	/**
	 * Constructor. Creates all the contained view elements with help of the
	 * {@link ParametersAndConfigPageCompositeFactory}.
	 * 
	 * @param parent
	 * @param style
	 * @param pageController
	 */
	public ParametersAndConfigPageComposite(Composite parent, int style,
			ParametersConfigPageController pageController) {
		super(parent, style);
		setRowLayout();

		ParametersAndConfigPageCompositeFactory viewFactory = new ParametersAndConfigPageCompositeFactory(
				this, pageController);

		// add the view elements for parameters
		viewFactory.addParametersTitleLabel();
		actorActionsComposite = viewFactory.addActorActionsView();
		initialAspirationLevelComposite = viewFactory.addInitAspLevelView();
		aspirationLevelDiscountComposite = viewFactory
				.addAspLevelDiscountView();
		aspirationLevelIncrementComposite = viewFactory
				.addAspLevelIncrementView();

		viewFactory.addSpacerComposite();

		// add the view elements for configs
		viewFactory.addConfigTitleLabel();
		configsCompositeWrapperManager = viewFactory
				.createConfigsCompositeWrapperManager();

		viewFactory.addStartComputationButton(this);
	}

	/**
	 * Sets the layout of this composite.
	 */
	private void setRowLayout() {
		RowLayout rowLayout = new RowLayout();
		rowLayout.type = SWT.VERTICAL;
		rowLayout.spacing = 20;
		rowLayout.marginBottom = 50;
		this.setLayout(rowLayout);
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
	 * @return The {@link ConfigWidgetsWrapperManager}.
	 */
	public ConfigWidgetsWrapperManager getConfigsCompositeWrapperManager() {
		return configsCompositeWrapperManager;
	}

	/**
	 * Initializes the parameter view elements with an existing
	 * {@link Parameters} object.
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

	/**
	 * Initializes the config view elements with an existing
	 * {@link SimulationConfig} object.
	 * 
	 * @param configChoice
	 */
	public void setSimulationConfigModel(SimulationConfig configChoice) {
		configsCompositeWrapperManager.setSimulationConfigModel(configChoice);
	}
}
