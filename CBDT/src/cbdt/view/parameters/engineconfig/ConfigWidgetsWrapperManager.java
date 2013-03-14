package cbdt.view.parameters.engineconfig;

import java.util.Observable;
import java.util.Observer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Label;

import cbdt.control.ParametersController;
import cbdt.model.parameters.engineconfig.AbstractEngineConfiguration;
import cbdt.model.parameters.engineconfig.EngineConfigChoice;
import cbdt.view.parameters.ParametersPage;
import cbdt.view.parameters.engineconfig.widgetswrapper.AbstractConfigWidgetsWrapper;
import cbdt.view.parameters.engineconfig.widgetswrapper.ConfigWidgetsWrapperFactory;

public class ConfigWidgetsWrapperManager implements Observer {

	private Combo availableConfigsCombo;
	private ParametersPage parametersPage;
	private ParametersController controller;
	private ConfigForegroundManager foregroundManager;
	private ConfigWidgetsWrapperFactory configCompositeFactory;

	public ConfigWidgetsWrapperManager(ParametersPage parametersPage,
			ParametersController controller) {
		this.parametersPage = parametersPage;
		this.controller = controller;
		
		foregroundManager = new ConfigForegroundManager(parametersPage);

		Label parameterLabel = new Label(parametersPage, SWT.NONE);
		parameterLabel.setText("Configuration:");
		
		availableConfigsCombo = new Combo(parametersPage, SWT.READ_ONLY);
	}

	public void setConfigChoiceModel(EngineConfigChoice configChoice){
		configCompositeFactory = new ConfigWidgetsWrapperFactory();
		
		EngineConfigSelectionListener comboSelectionListener = new EngineConfigSelectionListener(controller);
		
		for(AbstractEngineConfiguration config : configChoice.getAvailableEngineConfigs()){
			AbstractConfigWidgetsWrapper configComposite = configCompositeFactory.getConfigComposite(config, parametersPage);
			configComposite.setEngineConfigModel(config);
			configComposite.setConfigController(controller.getConfigControllerFactory().getConfigController(config));
			availableConfigsCombo.add(config.getName());
			comboSelectionListener.addEngineConfig(config);
		}
		availableConfigsCombo.addSelectionListener(comboSelectionListener);
		
		//find out which config is in currently chosen and put it in foreground
		if(configChoice.getCurrentlyChoosenConfig() != null)
			foregroundManager.setToForeground(configCompositeFactory.getConfigComposite(configChoice.getCurrentlyChoosenConfig(), parametersPage));
		
		configChoice.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		//update which config is in foreground.
		if(o instanceof EngineConfigChoice){
			EngineConfigChoice choice = (EngineConfigChoice)o;
			foregroundManager.setToForeground(
					configCompositeFactory.getConfigComposite(
							choice.getCurrentlyChoosenConfig(), parametersPage));
		}
	}
}
