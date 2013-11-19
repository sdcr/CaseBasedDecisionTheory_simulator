package cbdt.control.parameterspage;

import cbdt.control.AbstractPageController;
import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.control.parameterspage.config.engine.EngineConfigControllerFactory;
import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.SimulationConfig;
import cbdt.model.config.SimulationConfigFactory;
import cbdt.view.parameterspage.parameters.ParametersConfigPageWrapper;

public class ParametersConfigPageController extends AbstractPageController {

	/* views */
	/**
	 * The wrapper of the associated parameters page.
	 */
	private ParametersConfigPageWrapper parametersConfigPageWrapper;

	/* models */
	/**
	 * The current choice of engine configuration.
	 */
	private SimulationConfig simulationConfig;

	private EngineConfigControllerFactory configControllerFactory;

	private CommonConfigController commonConfigController;
	
	private ParametersController parametersController;
	
	/**
	 * Constructor.
	 * Instantiates the ParametersPageWrapper, 
	 */
	public ParametersConfigPageController() {
		//create parameters page wrapper (view)
		parametersConfigPageWrapper = new ParametersConfigPageWrapper(this);

		//create parameters controller
		parametersController = new ParametersController();
		
		//set simulationConfig to default
		SimulationConfigFactory simConfigFactory = new SimulationConfigFactory();
		simulationConfig = simConfigFactory.getDefaultSimulationConfig();

		//a factory to create the controllers for the engineConfigs
		configControllerFactory = new EngineConfigControllerFactory();

		//a controller for the commonConfig
		commonConfigController = new CommonConfigController();
		commonConfigController.setCommonConfig(simulationConfig.getCommonConfig());
	}

	@Override
	public ParametersConfigPageWrapper getPageWrapper() {
		return parametersConfigPageWrapper;
	}

	public SimulationConfig getSimulationConfigModel() {
		return simulationConfig;
	}

	public EngineConfigControllerFactory getConfigControllerFactory() {
		return configControllerFactory;
	}

	public CommonConfigController getCommonConfigController() {
		return commonConfigController;
	}
	
	public ParametersController getParametersController() {
		return parametersController;
	}
	
	public void startComputation() {
		getMainController().computeCDBTSimulation(parametersController.getParametersModel(), simulationConfig);
	}

	public void setChoosenEngineConfig(AbstractEngineConfig config) {
		simulationConfig.setCurrentlyChosenEngineConfig(config);
	}
}
