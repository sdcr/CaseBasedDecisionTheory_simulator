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
import cbdt.view.parameterspage.config.widgetswrapper.AbstractEngineConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.widgetswrapper.CommonConfigWidgetsWrapper;
import cbdt.view.parameterspage.config.widgetswrapper.EngineConfigWidgetsWrapperFactory;
import cbdt.view.parameterspage.config.widgetswrapper.NoWidgetWrapperException;

public class ConfigWidgetsWrapperManager implements Observer {
	
	private Combo availableConfigsCombo;
	private Composite parametersPage;
	private ParametersConfigPageController controller;
	private ConfigForegroundManager foregroundManager;
	private EngineConfigWidgetsWrapperFactory engineConfigWidgestWrapperFactory;
	private CommonConfigWidgetsWrapper commonConfigWidgetsWrapper;
	private ConfigBlockTitleLabelWrapper algoSpecificTitleWrapper;
	
	public ConfigWidgetsWrapperManager(Composite parametersPage,
			ParametersConfigPageController controller) {
		this.parametersPage = parametersPage;
		this.controller = controller;

		Label parameterLabel = new Label(parametersPage, SWT.NONE);
		parameterLabel.setText("Algorithm:");
		availableConfigsCombo = new Combo(parametersPage, SWT.READ_ONLY);
		commonConfigWidgetsWrapper = new CommonConfigWidgetsWrapper(parametersPage);
		commonConfigWidgetsWrapper.setCommonConfigController(controller.getCommonConfigController());
		algoSpecificTitleWrapper = new ConfigBlockTitleLabelWrapper(parametersPage);
		getAlgoSpecificTitleWrapper().getLabel().setText("Algorithm-specific configurations:");

		foregroundManager = new ConfigForegroundManager(parametersPage, algoSpecificTitleWrapper.getLabel());
	}

	public void setSimulationConfigModel(SimulationConfig simConfig) {
		engineConfigWidgestWrapperFactory = new EngineConfigWidgetsWrapperFactory();

		EngineConfigSelectionListener comboSelectionListener = new EngineConfigSelectionListener(
				controller);

		commonConfigWidgetsWrapper.setCommonConfigModel(simConfig.getCommonConfig());
		
		for (AbstractEngineConfig engineConfig : simConfig
				.getAvailableEngineConfigs()) {
			try {
				IEngineConfigController configController = controller
						.getConfigControllerFactory().getConfigController(engineConfig);
				configController.setEngineConfigModel(engineConfig);
				AbstractEngineConfigWidgetsWrapper engineConfigWidgetsWrapper = engineConfigWidgestWrapperFactory
						.getEngineConfigWidgetWrapper(engineConfig, parametersPage);
				engineConfigWidgetsWrapper.setConfigController(configController);
				engineConfigWidgetsWrapper.setEngineConfigModel(engineConfig);
				
				availableConfigsCombo.add(engineConfig.getName());
				comboSelectionListener.addEngineConfig(engineConfig);
				foregroundManager.setToBackground(engineConfigWidgetsWrapper);
			} catch (NoWidgetWrapperException | NoEngineConfigControllerException e) {
				e.printStackTrace();
			}
		}
		availableConfigsCombo.addSelectionListener(comboSelectionListener);

		// find out which config is in currently chosen and put it in foreground
		AbstractEngineConfig currentlyChoosenConfig = simConfig
				.getCurrentlyChosenEngineConfig();
		if (currentlyChoosenConfig != null) {
			try {
				foregroundManager.setToForeground(engineConfigWidgestWrapperFactory.getEngineConfigWidgetWrapper(
								currentlyChoosenConfig, parametersPage));
				int indexOfCurrentlyChoosenConfig = simConfig
						.getAvailableEngineConfigs()
						.indexOf(currentlyChoosenConfig);
				availableConfigsCombo.select(indexOfCurrentlyChoosenConfig);
			} catch (NoWidgetWrapperException e) {
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
				foregroundManager.setToForeground(engineConfigWidgestWrapperFactory
						.getEngineConfigWidgetWrapper(choice.getCurrentlyChosenEngineConfig(),
								parametersPage));
			} catch (NoWidgetWrapperException e) {
				e.printStackTrace();
			}
		}
	}

	public ConfigBlockTitleLabelWrapper getAlgoSpecificTitleWrapper() {
		return algoSpecificTitleWrapper;
	}

}
