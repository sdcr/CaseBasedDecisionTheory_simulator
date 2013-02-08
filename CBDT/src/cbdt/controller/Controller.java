package cbdt.controller;

import java.util.ArrayList;
import java.util.List;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPaneContent;
import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.CBDTSimulationParameters;
import cbdt.view.AnalysisPaneContent;
import cbdt.view.parameters.ParameterPaneContent;

public class Controller {

	private ParameterPaneContent parameterPaneContent;
	private AnalysisPaneContent analysisPaneContent;
	private CBDTSimulationParameters parametersModel;

	public Controller(){
		//create the view pane
		parameterPaneContent = new ParameterPaneContent(this);
		analysisPaneContent = new AnalysisPaneContent();
		
		//create the model
		parametersModel = new CBDTSimulationParameters();
		addDefaultActorActionToModel();
	}
	
	public List<ISimulationPluginPaneContent> getPaneContents() {
		List<ISimulationPluginPaneContent> retVal = new ArrayList<ISimulationPluginPaneContent>();
		retVal.add(parameterPaneContent);
		retVal.add(analysisPaneContent);
		return retVal;
	}

	public ActorAction addDefaultActorActionToModel(){
		ActorAction defaultActorAction = new ActorAction("");
		ActorActionOutcome defaultActorActionOutcome = createDefaultActorActionOutcome();
		defaultActorAction.addActionOutcome(defaultActorActionOutcome);
		parametersModel.getActorActions().add(defaultActorAction);
		return defaultActorAction;
	}
	
	public void removeActorActionFromModel(ActorAction actorAction){
		parametersModel.getActorActions().remove(actorAction);
	}
	
	public CBDTSimulationParameters getParametersModel(){
		return parametersModel;
	}

	public ActorActionOutcome addDefaultActorActionOutcomeToModel(ActorAction actorAction){
		ActorActionOutcome defaultOutcome = createDefaultActorActionOutcome(); 
		actorAction.addActionOutcome(defaultOutcome);
		return defaultOutcome;
	}
	
	public void removeActorActionOutcomeFromModel(ActorActionOutcome outcome){
		ActorAction actorAction = outcome.getAction();
		actorAction.getActionOutcomes().remove(outcome);
	}
	
	private ActorActionOutcome createDefaultActorActionOutcome(){
		return new ActorActionOutcome(0, 0);
	}
	
	public void printModel(){
		System.out.println(parametersModel.getActorActions());
	}
}
