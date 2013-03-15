package cbdt.view.parameters.engineconfig;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import cbdt.control.pages.ParametersController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.ConfigWidgetsWrapperFactory;

public class ConfigWidgetsWrapperManager implements Observer {

	private Combo availableConfigsCombo;
	private Composite parametersPage;
	private ParametersController controller;
	private ConfigForegroundManager foregroundManager;
	private ConfigWidgetsWrapperFactory configWidgetsFactory;

	public ConfigWidgetsWrapperManager(Composite parametersPage,
			ParametersController controller) {
		this.parametersPage = parametersPage;
		this.controller = controller;

		foregroundManager = new ConfigForegroundManager(parametersPage);

		Label parameterLabel = new Label(parametersPage, SWT.NONE);
		parameterLabel.setText("Algorithm:");
		availableConfigsCombo = new Combo(parametersPage, SWT.READ_ONLY);
	}

	public void setConfigChoiceModel(EngineConfigChoice configChoice) {
		configWidgetsFactory = new ConfigWidgetsWrapperFactory();

		EngineConfigSelectionListener comboSelectionListener = new EngineConfigSelectionListener(
				controller);

		for (AbstractEngineConfiguration config : configChoice
				.getAvailableEngineConfigs()) {
			AbstractConfigWidgetsWrapper configComposite = configWidgetsFactory
					.getConfigComposite(config, parametersPage);
			configComposite.setConfigController(controller
					.getConfigControllerFactory().getConfigController(config));
			configComposite.setEngineConfigModel(config);
			availableConfigsCombo.add(config.getName());
			comboSelectionListener.addEngineConfig(config);
		}
		availableConfigsCombo.addSelectionListener(comboSelectionListener);

		// find out which config is in currently chosen and put it in foreground
		AbstractEngineConfiguration currentlyChoosenConfig = configChoice
				.getCurrentlyChoosenConfig();
		if (currentlyChoosenConfig != null) {
			foregroundManager
					.setToForeground(configWidgetsFactory.getConfigComposite(
							currentlyChoosenConfig, parametersPage));
			int indexOfCurrentlyChoosenConfig = configChoice
					.getAvailableEngineConfigs()
					.indexOf(currentlyChoosenConfig);
			availableConfigsCombo.select(indexOfCurrentlyChoosenConfig);
		}
		configChoice.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		// update which config is in foreground.
		if (o instanceof EngineConfigChoice) {
			EngineConfigChoice choice = (EngineConfigChoice) o;
			foregroundManager.setToForeground(configWidgetsFactory
					.getConfigComposite(choice.getCurrentlyChoosenConfig(),
							parametersPage));
		}
	}
}
