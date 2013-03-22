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
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
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

	public ConfigWidgetsWrapperManager(Composite parametersPage,
			ParametersPageController controller) {
		this.parametersPage = parametersPage;
		this.controller = controller;

		foregroundManager = new ConfigForegroundManager(parametersPage);

		Label parameterLabel = new Label(parametersPage, SWT.NONE);
		parameterLabel.setText("Algorithm:");
		availableConfigsCombo = new Combo(parametersPage, SWT.READ_ONLY);
		commonWidgets = new CommonConfigWidgetsWrapper(parametersPage);
		commonWidgets.setController(controller);
	}

	public void setConfigChoiceModel(EngineConfigChoice configChoice) {
		configWidgetsFactory = new ConfigWidgetsWrapperFactory();

		EngineConfigSelectionListener comboSelectionListener = new EngineConfigSelectionListener(
				controller);

		commonWidgets.setEngineConfigModel(configChoice.getCommonConfig());
		
		for (AbstractEngineConfiguration config : configChoice
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
		AbstractEngineConfiguration currentlyChoosenConfig = configChoice
				.getCurrentlyChoosenConfig();
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
		if (o instanceof EngineConfigChoice) {
			EngineConfigChoice choice = (EngineConfigChoice) o;
			try {
				foregroundManager.setToForeground(configWidgetsFactory
						.getConfigComposite(choice.getCurrentlyChoosenConfig(),
								parametersPage));
			} catch (NoWidgetWrapperException e) {
				e.printStackTrace();
			}
		}
	}
}
