package cbdt.view.parameters.engineconfig;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.ParametersPageController;
import cbdt.control.pages.engineconfig.AbstractEngineConfigController;
import cbdt.control.pages.engineconfig.NoEngineConfigControllerException;
import cbdt.model.parameters.engineconfig.AbstractEngineConfig;
import cbdt.model.parameters.engineconfig.SimulationConfig;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.CommonConfigWidgetsWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.ConfigWidgetsWrapperFactory;
import cbdt.view.parameters.engineconfig.widgetswrapper.NoWidgetWrapperException;

public class ConfigWidgetsWrapperManager implements Observer {
	
	private Combo availableConfigsCombo;
	private Composite parametersPage;
	private ParametersPageController controller;
	private ConfigForegroundManager foregroundManager;
	private ConfigWidgetsWrapperFactory configWidgetsFactory;
	private CommonConfigWidgetsWrapper commonWidgets;
	private ConfigBlockTitleLabelWrapper algoSpecificTitleWrapper;
	
	public ConfigWidgetsWrapperManager(Composite parametersWrapper,
			ParametersPageController controller) {
		this.parametersPage = parametersWrapper;
		this.controller = controller;


		Label parameterLabel = new Label(parametersWrapper, SWT.NONE);
		parameterLabel.setText("Algorithm:");
		availableConfigsCombo = new Combo(parametersWrapper, SWT.READ_ONLY);
		commonWidgets = new CommonConfigWidgetsWrapper(parametersWrapper);
		commonWidgets.setController(controller);
		algoSpecificTitleWrapper = new ConfigBlockTitleLabelWrapper(parametersWrapper);
		getAlgoSpecificTitleWrapper().getLabel().setText("Algorithm-specific configurations:");

		foregroundManager = new ConfigForegroundManager(parametersWrapper, algoSpecificTitleWrapper.getLabel());
	}

	public void setConfigChoiceModel(SimulationConfig configChoice) {
		configWidgetsFactory = new ConfigWidgetsWrapperFactory();

		EngineConfigSelectionListener comboSelectionListener = new EngineConfigSelectionListener(
				controller);

		commonWidgets.setEngineConfigModel(configChoice.getCommonConfig());
		
		for (AbstractEngineConfig config : configChoice
				.getAvailableEngineConfigs()) {
			try {
				AbstractEngineConfigController configController = controller
						.getConfigControllerFactory().getConfigController(config);
				configController.setEngineConfigModel(config);
				AbstractConfigWidgetsWrapper configComposite = configWidgetsFactory
						.getConfigComposite(config, parametersPage);
				configComposite.setConfigController(configController);
				configComposite.setEngineConfigModel(config);
				availableConfigsCombo.add(config.getName());
				comboSelectionListener.addEngineConfig(config);
				foregroundManager.setToBackground(configComposite);
			} catch (NoWidgetWrapperException | NoEngineConfigControllerException e) {
				e.printStackTrace();
			}
		}
		availableConfigsCombo.addSelectionListener(comboSelectionListener);

		// find out which config is in currently chosen and put it in foreground
		AbstractEngineConfig currentlyChoosenConfig = configChoice
				.getCurrentlyChosenEngineConfig();
		if (currentlyChoosenConfig != null) {
			try {
				foregroundManager.setToForeground(configWidgetsFactory.getConfigComposite(
								currentlyChoosenConfig, parametersPage));
				int indexOfCurrentlyChoosenConfig = configChoice
						.getAvailableEngineConfigs()
						.indexOf(currentlyChoosenConfig);
				availableConfigsCombo.select(indexOfCurrentlyChoosenConfig);
			} catch (NoWidgetWrapperException e) {
				e.printStackTrace();
			}
		}
		configChoice.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// update which config is in foreground.
		if (o instanceof SimulationConfig) {
			SimulationConfig choice = (SimulationConfig) o;
			try {
				foregroundManager.setToForeground(configWidgetsFactory
						.getConfigComposite(choice.getCurrentlyChosenEngineConfig(),
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
