package cbdt.control.parameterspage;

import cbdt.control.AbstractPageController;
import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.control.parameterspage.config.engine.EngineConfigControllerFactory;
import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.model.config.AbstractEngineConfig;
import cbdt.model.config.SimulationConfig;
import cbdt.model.config.SimulationConfigFactory;
import cbdt.view.parameterspage.parameters.ParametersConfigPageWrapper;

//YELLOW
//maybe create a dedicated simulationController.
/**
 * The controller of the parameters/config page. All requests to change the
 * parameters, the common config, or the engine configs are outsourced to
 * respective dedicated controllers. Only the requests to the SimulationConfig
 * model object are handled directly. (Which amounts only to a request to change
 * the currently chosen engine config.)
 * 
 * @author Stephan da Costa Ribeiro
 * 
 */
public class ParametersConfigPageController extends AbstractPageController {

	/* views */
	/**
	 * The wrapper of the associated parameters page.
	 */
	private ParametersConfigPageWrapper parametersConfigPageWrapper;

	/* controllers to which request handling is outsourced */
	/**
	 * The controller handling requests to change parameters.
	 */
	private ParametersController parametersController;

	/**
	 * A factory instantiating controllers which handle the changing of config
	 * parameters which refer to a certain engine config.
	 */
	private EngineConfigControllerFactory configControllerFactory;

	/**
	 * The controller handling request to change config parameters which refer
	 * to the CommonConfig.
	 */
	private CommonConfigController commonConfigController;

	/* directly associated models */
	private SimulationConfig simulationConfig;

	/**
	 * Constructor. Instantiates the ParametersPageWrapper, the controllers to
	 * which request handling is outsourced and sets the simmulationConfig to
	 * default.
	 */
	public ParametersConfigPageController() {
		// create parameters page wrapper (view)
		parametersConfigPageWrapper = new ParametersConfigPageWrapper(this);

		// create parameters controller
		parametersController = new ParametersController(this);

		// set simulationConfig to default
		SimulationConfigFactory simConfigFactory = new SimulationConfigFactory();
		simulationConfig = simConfigFactory.getDefaultSimulationConfig();

		// a factory to create the controllers for the engineConfigs
		configControllerFactory = new EngineConfigControllerFactory();

		// a controller for the commonConfig
		commonConfigController = new CommonConfigController();
		commonConfigController.setCommonConfig(simulationConfig
				.getCommonConfig());
	}

	@Override
	public ParametersConfigPageWrapper getPageWrapper() {
		return parametersConfigPageWrapper;
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
		getMainController().computeCDBTSimulation(
				parametersController.getParametersModel(), simulationConfig);
	}
	
	public SimulationConfig getSimulationConfig() {
		return simulationConfig;
	}
	
	public void setCurrentlyChosenEngineConfig(AbstractEngineConfig config) {
		simulationConfig.setCurrentlyChosenEngineConfig(config);
	}
}
