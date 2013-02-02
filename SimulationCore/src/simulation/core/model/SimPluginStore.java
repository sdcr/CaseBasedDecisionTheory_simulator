package simulation.core.model;

import java.util.ArrayList;
import java.util.List;

import simulation.core.view.MainView;
import simulation.extensionpoint.simulationplugin.ISimulationPlugin;

public class SimPluginStore {

	List<SimPluginModel> activeSimPlugins;
	private MainView mainView;
	
	public SimPluginStore() {
		activeSimPlugins = new ArrayList<SimPluginModel>();
	}
	
	public void setMainView(MainView mainView) {
		this.mainView = mainView;
	}

	public void addSimPlugin(ISimulationPlugin newISimulationPlugin){
		activeSimPlugins.add(new SimPluginModel(newISimulationPlugin));
		if(mainView != null)
			mainView.updateFromModel();
	}
	
	public void changeSth(){
		if(mainView != null){
			System.out.println("store tells view to update");
			mainView.updateFromModel();
		}
	}

}
