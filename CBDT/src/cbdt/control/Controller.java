package cbdt.control;

import java.util.ArrayList;
import java.util.List;

import simulation.extensionpoint.simulationplugin.definition.ISimulationPluginPageFactory;
import cbdt.model.ActorAction;
import cbdt.model.ActorActionOutcome;
import cbdt.model.Parameters;
import cbdt.view.analysis.AnalysisPageFactory;
import cbdt.view.parameters.ParametersPageFactory;

public class Controller {

	private ParametersPageFactory parameterPaneContent;
	private AnalysisPageFactory analysisPaneContent;
	private Parameters parametersModel;

	public Controller(){
		//create the view pane
		parameterPaneContent = new ParametersPageFactory(this);
		analysisPaneContent = new AnalysisPageFactory();
		
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
		parametersModel.setInitialAspirationLevel(newInitAspirationLevel);
	}
	
	public void setAspirationLevelIncrement(Double newAspirationLevelIncrement) {
		parametersModel.setAspirationLevelIncrement(newAspirationLevelIncrement);
	}
	
	public void setAspirationDiscountFactor(Double newAspirationLevelDiscountFactor) {
		parametersModel.setWeightingFactorAlpha(newAspirationLevelDiscountFactor);
	}
	
	
	

	public void printModel(){
		System.out.println(parametersModel.getActorActions());
	}

}
