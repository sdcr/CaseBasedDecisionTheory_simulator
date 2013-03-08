package cbdt.controller;

import java.util.ArrayList;
import java.util.List;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;
import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.Parameters;
import cbdt.view.analysis.AnalysisPaneContent;
import cbdt.view.parameters.ParameterPageContentWrapper;

public class Controller {

	private ParameterPageContentWrapper parameterPaneContent;
	private AnalysisPaneContent analysisPaneContent;
	private Parameters parametersModel;

	public Controller(){
		//create the view pane
		parameterPaneContent = new ParameterPageContentWrapper(this);
		analysisPaneContent = new AnalysisPaneContent();
		
		//create the model
		parametersModel = new Parameters();
		addDefaultActorActionToModel();
	}
	
	public List<ISimulationPluginPageFactory> getPaneContents() {
		List<ISimulationPluginPageFactory> retVal = new ArrayList<ISimulationPluginPageFactory>();
		retVal.add(parameterPaneContent);
		retVal.add(analysisPaneContent);
		return retVal;
	}

	public ActorAction addDefaultActorActionToModel(){
		ActorAction defaultActorAction = new ActorAction("");
		ActorActionOutcome defaultActorActionOutcome = createDefaultActorActionOutcome();
		defaultActorAction.addActionOutcome(defaultActorActionOutcome);
		parametersModel.addActorAction(defaultActorAction);//getActorActions().add(defaultActorAction);
		return defaultActorAction;
	}
	
	public void removeActorActionFromModel(ActorAction actorAction){
		parametersModel.removeActorAction(actorAction);//getActorActions().remove(actorAction);
	}
	
	public Parameters getParametersModel(){
		return parametersModel;
	}

	public ActorActionOutcome addDefaultActorActionOutcomeToModel(ActorAction actorAction){
		ActorActionOutcome defaultOutcome = createDefaultActorActionOutcome(); 
		actorAction.addActionOutcome(defaultOutcome);
		return defaultOutcome;
	}
	
	public void removeActorActionOutcomeFromModel(ActorActionOutcome outcome){
		ActorAction actorAction = outcome.getAction();
		actorAction.removeActionOutcome(outcome);
	}
	
	public void setActorActionName(ActorAction actorAction, String newName){
		actorAction.setActionName(newName);
	}
	
	private ActorActionOutcome createDefaultActorActionOutcome(){
		return new ActorActionOutcome(0, 0);
	}
	
	public void setInitialAspirationLevel(Double newInitAspirationLevel) {
		// TODO Auto-generated method stub
		
	}

	public void printModel(){
		System.out.println(parametersModel.getActorActions());
	}

}
