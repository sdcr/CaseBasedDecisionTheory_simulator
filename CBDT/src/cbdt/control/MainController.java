package cbdt.control;

import java.util.ArrayList;
import java.util.List;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;
import cbdt.view.analysis.AnalysisPageFactory;
import cbdt.view.parameters.ParametersPageFactory;

public class MainController {

	private ParametersPageFactory parameterPageFactory;
	private AnalysisPageFactory analysisPageFactory;

	public MainController(){
		//create the view pane
		ParametersController paramsController = new ParametersController();
		parameterPageFactory = new ParametersPageFactory(paramsController);
		analysisPageFactory = new AnalysisPageFactory();
	}
	
	public List<ISimulationPluginPageFactory> getPaneContents() {
		List<ISimulationPluginPageFactory> retVal = new ArrayList<ISimulationPluginPageFactory>();
		retVal.add(parameterPageFactory);
		retVal.add(analysisPageFactory);
		return retVal;
	}

}
