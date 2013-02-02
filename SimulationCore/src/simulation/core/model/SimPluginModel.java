package simulation.core.model;

import java.util.List;


public class SimPluginModel {

//	private ISimulationPlugin wrappedPlugin;
	
	private String foregroundWidgetName;
	
	List<SimPluginPaneContent> paneContents;

//	public SimPluginModel(ISimulationPlugin wrappedPlugin){
//		wrappedPlugin.getMainPaneContent(parent)
//	}
	
	public String getForegroundWidgetName() {
		return foregroundWidgetName;
	}

	public void setForegroundWidgetName(String foregroundWidgetName) {
		this.foregroundWidgetName = foregroundWidgetName;
	}

//	public SimPluginModel(ISimulationPlugin iSimulationPlugin) {
//		wrappedPlugin = iSimulationPlugin;
//		
//	}
//
//	public ISimulationPlugin getISimulationPlugin(){
//		return wrappedPlugin;
//	}
	
	
}
