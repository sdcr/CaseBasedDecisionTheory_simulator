package cbdt.control.parameterspage;

import cbdt.control.AbstractPageController;
import cbdt.control.MainController;
import cbdt.control.parameterspage.config.common.CommonConfigController;
import cbdt.control.parameterspage.config.engine.EngineConfigControllerFactory;
import cbdt.control.parameterspage.config.engine.IEngineConfigController;
import cbdt.control.parameterspage.parameters.ParametersController;
import cbdt.model.config.SimulationConfig;
import cbdt.model.config.SimulationConfigFactory;
import cbdt.model.config.common.CommonConfig;
import cbdt.model.config.engine.AbstractEngineConfig;
import cbdt.model.parameters.Parameters;
import cbdt.view.parameterspage.ParametersAndConfigPageWrapper;

/**
 * The controller of the parameters-config page. All handling of requests to
 * change the {@link Parameters}, the {@link CommonConfig}, or the
 * {@link AbstractEngineConfig}s are outsourced to respective dedicated
 * controllers. Other requests are handled directly, for example by directly
 * changing the {@link SimulationConfig} object, or by forwarding the requests
 * to the {@link MainController}.
 * 
 * @author Stephan da Costa Ribeiro
 */
public class ParametersConfigPageController extends AbstractPageController {

	/* views */
	/**
	 * The wrapper of the associated parameters-config page.
	 */
	private ParametersAndConfigPageWrapper parametersConfigPageWrapper;

	/* controllers to which request handling is outsourced */
	/**
	 * The controller handling requests to change parameters.
	 */
	private ParametersController parametersController;

	/**
	 * A factory instantiating {@link IEngineConfigController} objects which
	 * handle requests to change their respective {@link AbstractEngineConfig}s.
	 */
	private EngineConfigControllerFactory configControllerFactory;

	/**
	 * The controller which handles requests to change the {@link CommonConfig}.
	 */
	private CommonConfigController commonConfigController;

	/* directly associated models */
	private SimulationConfig simulationConfig;

	/**
	 * Constructor. Instantiates the {@link ParametersAndConfigPageWrapper}, the
	 * controllers to which request handling is outsourced and sets the
	 * {@link SimulationConfig} to default.
	 */
	public ParametersConfigPageController() {
		// create parameters page wrapper (view)
		parametersConfigPageWrapper = new ParametersAndConfigPageWrapper(this);

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
	public ParametersAndConfigPageWrapper getPageWrapper() {
		return parametersConfigPageWrapper;
	}

	/**
	 * @return the {@link EngineConfigControllerFactory}.
	 */
	public EngineConfigControllerFactory getConfigControllerFactory() {
		return configControllerFactory;
	}

	/**
	 * @return the {@link CommonConfigController}
	 */
	public CommonConfigController getCommonConfigController() {
		return commonConfigController;
	}

	/**
	 * @return the {@link ParametersController}
	 */
	public ParametersController getParametersController() {
		return parametersController;
	}

	/**
	 * Initiates a request to the {@link MainController} to start the
	 * computation of the CBDT simulation with the currently entered parameters.
	 */
	public void startComputation() {
		getMainController().computeCDBTSimulation(
				parametersController.getParametersModel(), simulationConfig);
	}

	/**
	 * @return the {@link SimulationConfig}
	 */
	public SimulationConfig getSimulationConfig() {
		return simulationConfig;
	}

	/**
	 * Sets the chosen engine configuration.
	 * 
	 * @param config
	 *            An object extending {@link AbstractEngineConfig}. It must be
	 *            one of the objects set as available
	 *            {@link AbstractEngineConfig}s in the {@link SimulationConfig}.
	 */
	public void setCurrentlyChosenEngineConfig(AbstractEngineConfig config) {
		simulationConfig.setCurrentlyChosenEngineConfig(config);
	}
}
