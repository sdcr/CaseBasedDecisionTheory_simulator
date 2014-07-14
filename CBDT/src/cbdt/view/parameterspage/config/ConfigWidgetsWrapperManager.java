package cbdt.view.parameterspage.config;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.parameterspage.ParametersConfigPageController;
import cbdt.control.parameterspage.config.engine.IEngineConfigController;
import cbdt.control.parameterspage.config.engine.NoEngineConfigControllerException;
import cbdt.model.config.SimulationConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.view.parameterspage.config.common.CommonConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.engine.AbstractEngineConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.engine.EngineConfigForegroundManager;
import cbdt.view.parameterspage.config.engine.EngineConfigWidgetsWrapperFactory;
import cbdt.view.parameterspage.config.engine.NoWidgetWrapperFoundException;
import cbdt.view.parameterspage.config.engine.listeners.EngineConfigSelectionListener;

/**
 * This class manages the instantiation of classes which wrap config widgets.
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ConfigWidgetsWrapperManager implements Observer {

	private Combo availableConfigsCombo;
	private Composite parametersPage;
	private ParametersConfigPageController controller;
	private EngineConfigForegroundManager foregroundManager;
	private EngineConfigWidgetsWrapperFactory engineConfigWidgestWrapperFactory;
	private CommonConfigWidgetsWrapper commonConfigWidgetsWrapper;

	/**
	 * The constructor. Creates all configuration widgets.
	 * 
	 * @param parametersPage
	 * @param controller
	 */
	public ConfigWidgetsWrapperManager(Composite parametersPage,
			ParametersConfigPageController controller) {
		this.parametersPage = parametersPage;
		this.controller = controller;

		Label algorithmLabel = new Label(parametersPage, SWT.NONE);
		algorithmLabel.setText("Algorithm:");
		availableConfigsCombo = new Combo(parametersPage, SWT.READ_ONLY);

		addCommonConfigWidgets(parametersPage, controller);
		addEngineSpecWidgets(parametersPage);
	}

	/**
	 * Adds the widgets for algorithm specific configurations.
	 * 
	 * @param parametersPage
	 */
	private void addEngineSpecWidgets(Composite parametersPage) {
		ConfigBlockTitleLabelFactory blockTitleLabelFactory = new ConfigBlockTitleLabelFactory();
		Label algoSpecConfigTitleLabel = blockTitleLabelFactory
				.addConfigBlockTitleLabel(parametersPage);
		algoSpecConfigTitleLabel.setText("Algorithm-specific configurations:");

		foregroundManager = new EngineConfigForegroundManager(parametersPage,
				algoSpecConfigTitleLabel);
	}

	/**
	 * Add the widgets for algorithm independent configurations.
	 * 
	 * @param parametersPage
	 * @param controller
	 */
	private void addCommonConfigWidgets(Composite parametersPage,
			ParametersConfigPageController controller) {
		ConfigBlockTitleLabelFactory blockTitleLabelFactory = new ConfigBlockTitleLabelFactory();

		Label algoIndependentConfigTitleLabel = blockTitleLabelFactory
				.addConfigBlockTitleLabel(parametersPage);
		algoIndependentConfigTitleLabel
				.setText("Algorithm-independent configurations:");
		commonConfigWidgetsWrapper = new CommonConfigWidgetsWrapper(
				parametersPage);
		commonConfigWidgetsWrapper.setCommonConfigController(controller
				.getCommonConfigController());
	}

	/**
	 * Set the shown widgets according to the passed {@link SimulationConfig}.
	 * 
	 * @param simConfig
	 */
	public void setSimulationConfigModel(SimulationConfig simConfig) {
		engineConfigWidgestWrapperFactory = new EngineConfigWidgetsWrapperFactory();

		EngineConfigSelectionListener comboSelectionListener = new EngineConfigSelectionListener(
				controller);

		commonConfigWidgetsWrapper.setCommonConfigModel(simConfig
				.getCommonConfig());

		for (AbstractEngineConfig engineConfig : simConfig
				.getAvailableEngineConfigs()) {
			try {
				IEngineConfigController configController = controller
						.getConfigControllerFactory().getConfigController(
								engineConfig);
				configController.setEngineConfigModel(engineConfig);
				AbstractEngineConfigWidgetsWrapper engineConfigWidgetsWrapper = engineConfigWidgestWrapperFactory
						.getEngineConfigWidgetWrapper(engineConfig,
								parametersPage);
				engineConfigWidgetsWrapper
						.setEngineConfigController(configController);
				engineConfigWidgetsWrapper.setEngineConfigModel(engineConfig);

				availableConfigsCombo.add(engineConfig.getName());
				comboSelectionListener.addEngineConfig(engineConfig);
				foregroundManager.setToBackground(engineConfigWidgetsWrapper);
			} catch (NoWidgetWrapperFoundException
					| NoEngineConfigControllerException e) {
				e.printStackTrace();
			}
		}
		availableConfigsCombo.addSelectionListener(comboSelectionListener);

		// find out which config is in currently chosen and put it in foreground
		AbstractEngineConfig currentlyChoosenConfig = simConfig
				.getCurrentlyChosenEngineConfig();
		if (currentlyChoosenConfig != null) {
			try {
				foregroundManager
						.setToForeground(engineConfigWidgestWrapperFactory
								.getEngineConfigWidgetWrapper(
										currentlyChoosenConfig, parametersPage));
				int indexOfCurrentlyChoosenConfig = simConfig
						.getAvailableEngineConfigs().indexOf(
								currentlyChoosenConfig);
				availableConfigsCombo.select(indexOfCurrentlyChoosenConfig);
			} catch (NoWidgetWrapperFoundException e) {
				e.printStackTrace();
			}
		}
		simConfig.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// update which config is in foreground.
		if (o instanceof SimulationConfig) {
			SimulationConfig choice = (SimulationConfig) o;
			try {
				foregroundManager
						.setToForeground(engineConfigWidgestWrapperFactory.getEngineConfigWidgetWrapper(
								choice.getCurrentlyChosenEngineConfig(),
								parametersPage));
			} catch (NoWidgetWrapperFoundException e) {
				e.printStackTrace();
			}
		}
	}

}
